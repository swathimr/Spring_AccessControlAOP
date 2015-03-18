package edu.sjsu.cmpe275.lab1;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class AccessControlAspect {
	
	String[] fileLocation=null;
	String newValue = "" ;
	String sharedValue="";
	
	/*
	 * Aspect advice around has been added for readfile access control 
	 */
	@Around("execution(* FileShare.readFile(..))")
 	public void aroundReadAccessControl(ProceedingJoinPoint joinPoint) throws Throwable 
 	{ 
 	Object[] params = joinPoint.getArgs();
 	 	
 	if(params.length != 0 )
 	{
 		String userId =params[0].toString();
 		getStoredFilePath(userId);
 	}
 	
	if(sharedValue.trim().equals("1") && newValue.trim().equals(params[1].toString()))
	{
		joinPoint.proceed();
	}
	 else {
			try {
				throw new UnauthorizedException();
				} 
			catch (UnauthorizedException unauthExcep)
			{
				System.err.println("UnAuthorised Access Exception");
				}
	 }
 	}	

	/*
	 * Aspect to unshare access
	 */
	@Around("execution(* FileShare.unshareFile(..))")
 	public void aroundUnshareAccessControl(ProceedingJoinPoint joinPoint) throws Throwable 
 	{
		Object[] params = joinPoint.getArgs();
	 	if(params.length != 0 )
	 	{
			String userId =params[0].toString();
			String targetUserId =params[1].toString();
	 		if(!userId.equals(targetUserId))
			{
				if(FileShare.multiMap.containsKey(userId) && FileShare.multiMap.containsKey(targetUserId))
				{
					boolean flag =FileShare.multiMap.get(userId).toString().contains("1") && 
							FileShare.multiMap.get(targetUserId).toString().contains("1");
					if(flag)
					{
						joinPoint.proceed();
					}
				}
			}
	 	}
 	}
	
	/*
	 * Aspect to share access
	 */
	@Around("execution(* FileShare.shareFile(..))")
 	public void aroundShareAccessControl(ProceedingJoinPoint joinPoint) throws Throwable 
 	{ 
		Object[] params = joinPoint.getArgs();
		if(params.length != 0 )
	 	{
	 		String userId =params[0].toString();
			String targetUserId =params[1].toString();
			String filePath = params[2].toString();
			if (!userId.equals(targetUserId)) {
				getStoredFilePath(userId);
				boolean flag=FileShare.multiMap.containsKey(userId) && FileShare.multiMap.get(userId).toString().contains("1") 
						&& newValue.trim().equals(filePath);
				if(flag)
				{
					FileShare.multiMap.remove(targetUserId);
					joinPoint.proceed();
				}
				else if(FileShare.multiMap.isEmpty())
				{
					FileShare.multiMap.put(userId, 1);
					FileShare.multiMap.put(userId, filePath);
					joinPoint.proceed();
				}
				else {
					try {
						throw new UnauthorizedException();
					} catch (UnauthorizedException unauthExcep) {
						//unauthExcep.printStackTrace();
						System.err.println("UnAuthorised Access Exception");
					}
				}
			}
	 	}
	 	
 	}
	
	
	/*
	 * Method to get actual filepath that is shared with particular user
	 * @params userId
	 */
	public void getStoredFilePath(String userId)
	{
		if(FileShare.multiMap.get(userId)!=null)
		{
			fileLocation= FileShare.multiMap.get(userId).toString().split(",");
			if(fileLocation.length>1)
			{
			 newValue =fileLocation[1].replace("]","");
			 sharedValue = fileLocation[0].replace("[","");
			}
			else
			{
				newValue="";
			}
		}
	}

}
