package edu.sjsu.cmpe275.lab1;

import java.util.Set;

import org.apache.commons.collections.MultiMap;
import org.apache.commons.collections.map.MultiValueMap;



public class FileShare implements IFileService {
	
	//MultiMap used to store one key and multiple values in map
	static MultiMap multiMap = new MultiValueMap();
		
	
	/* Share owned file with another user
	 * @param userId the ID of the current user
	 * @param targetUserID the ID of the user, to whom the file needs to be shared.
	 * @param filePath location of the file to be shared.
	 * @throws unauthorised exception if user tries to share file which they 
	 * Don't have or do not have access to  
	 */ 
    public void shareFile(String userId, String targetUserID, String filePath) {
    	multiMap.put(targetUserID, 1);
		multiMap.put(targetUserID, filePath);
		System.out.println(userId + " has successfully shared " + filePath
				+ " with " + targetUserID);
	}

    
    /* Unshare a file with another user
     * Once unshared reset multimap value of targetuser to 0
     * @param userId the ID of the current user
     * @param targetUserID the ID of the user, to whom the sharing needs to be stopped.
     * @param filePath location of the file to be shared.
     */
	public void unshareFile(String userId, String targetUserID, String filePath) 
	{
		multiMap.remove(targetUserID);
		multiMap.put(targetUserID, 0);
		System.out.println(userId + " has unshared " + filePath	+ " with " + targetUserID);
	}
	

	/* Read file method checks for if user has share access and if shared file and file trying to be read are same
	 * if true returns message bytes else throws unauthorized exception
	 * @param userId the ID of the current user
	 * @param filePath the path of the file for which the user is requesting access.
	 * @return Returns the contents of the file, or null if the file does not exist.
	 */
	public byte[] readFile(String userId, String filePath) {
	
		String msg="";
		msg = userId+ " has successfully read the file "+filePath;
		System.out.println(msg);
		return msg.getBytes();
			
	}
	
	/* 
	 * Method to print values populated in the multimap
	 * Prints key and values in map to console
	 */
	public void printMultiMap() 
	{
		Set<String> keys = multiMap.keySet();
		for (String key : keys) {
			System.out.println("Key = " + key + ",Values = "
					+ multiMap.get(key) + "\n");
		}
	}
	
	
}
