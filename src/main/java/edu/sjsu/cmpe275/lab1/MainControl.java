package edu.sjsu.cmpe275.lab1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainControl {

	public static void main(String[] args) {

				//Reads beans.xml
				ApplicationContext appCtxt = new ClassPathXmlApplicationContext("beans.xml");
				
				IFileService fileshare = (IFileService)appCtxt.getBean("fileShare");
									
				System.out.println("testA");
				fileshare.readFile("Bob", "/home/Alice/shared/alicefile1.txt");
				System.out.println("************************");
				
				System.out.println("testB");
				fileshare.shareFile("Alice", "Bob", "/home/Alice/shared/alicefile1.txt");
				fileshare.readFile("Bob", "/home/Alice/shared/alicefile1.txt");
				System.out.println("************************");
				
				System.out.println("testC");
				fileshare.shareFile("Alice", "Bob", "/home/Alice/shared/alicefile1.txt");
				fileshare.readFile("Bob", "/home/Alice/shared/alicefile1.txt");
				fileshare.shareFile("Bob", "Carl", "/home/Alice/shared/alicefile1.txt");
				fileshare.readFile("Carl", "/home/Alice/shared/alicefile1.txt");
				System.out.println("************************");
				
				System.out.println("testD");
				fileshare.shareFile("Alice", "Bob", "/home/Alice/shared/alicefile1.txt");
				fileshare.shareFile("Bob", "Alice", "/home/Carl/shared/carlfile1.txt");
				System.out.println("************************");
				
				System.out.println("testE");
				fileshare.shareFile("Alice", "Bob", "/home/Alice/shared/alicefile1.txt");
				fileshare.shareFile("Bob", "Carl", "/home/Alice/shared/alicefile1.txt");
				fileshare.unshareFile("Alice", "Carl", "/home/Alice/shared/alicefile1.txt");
				fileshare.readFile("Carl", "/home/Alice/shared/alicefile1.txt");
				System.out.println("************************");
				
				System.out.println("testF");
				fileshare.shareFile("Alice", "Bob", "/home/Alice/shared/alicefile1.txt");
				fileshare.shareFile("Alice", "Carl", "/home/Alice/shared/alicefile1.txt");
				fileshare.shareFile("Carl", "Bob", "/home/Alice/shared/alicefile1.txt");
				fileshare.unshareFile("Alice", "Bob", "/home/Alice/shared/alicefile1.txt");
				fileshare.readFile("Bob", "/home/Alice/shared/alicefile1.txt");
				System.out.println("************************");
				
				System.out.println("testG");
				fileshare.shareFile("Alice", "Bob", "/home/Alice/shared/alicefile1.txt");
				fileshare.shareFile("Bob", "Carl", "/home/Alice/shared/alicefile1.txt");
				fileshare.unshareFile("Alice", "Bob", "/home/Alice/shared/alicefile1.txt");
				fileshare.shareFile("Bob", "Carl", "/home/Alice/shared/alicefile1.txt");
				fileshare.readFile("Carl", "/home/Alice/shared/alicefile1.txt");
				System.out.println("************************");
				
				System.out.println("testH");
				fileshare.shareFile("Alice", "Bob", "/home/Alice/shared/file1.txt");
				fileshare.readFile("Bob", "/home/Alice/shared/file2.txt");
				System.out.println("************************");
				
	}

}
