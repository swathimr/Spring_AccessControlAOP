package edu.sjsu.cmpe275.lab1;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class LoggingAspect {

	@Before("execution(* FileShare.shareFile(..))")
	public void logBeforShare(JoinPoint joinpt)
	{
		Object[] params = joinpt.getArgs();
		if(params.length != 0 )
		{	
			System.out.println(params[0].toString()+ " tries to share the file "+params[2].toString()
				+" with "+params[1].toString());	
		}
		
	}
	
	@Before("execution(* FileShare.unshareFile(..))")
	public void logBeforeUnshare(JoinPoint joinpt)
	{
		Object[] params = joinpt.getArgs();
		if(params.length!= 0 )
		{
			System.out.println(params[0].toString()+ " tries to unshare the file "+params[2].toString()
				+" with "+params[1].toString());	
		}	
	}
	
	@Before("execution(* FileShare.readFile(..))")
	public void logBeforeRead(JoinPoint joinpt)
	{
		Object[] params = joinpt.getArgs();
		if(params.length!= 0 )
		{	
			System.out.println(params[0].toString()+ " tries to read the file "+params[1].toString());
		}
		
	}
	
}