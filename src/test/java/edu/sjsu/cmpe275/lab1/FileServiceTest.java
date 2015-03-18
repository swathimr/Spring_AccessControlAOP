package edu.sjsu.cmpe275.lab1;

import org.aspectj.lang.annotation.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

@TestExecutionListeners( { DependencyInjectionTestExecutionListener.class })
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:beans.xml"})
public class FileServiceTest {
	
	@Autowired
	IFileService fileService = new FileShare();
	@Before(value = "")
	public void setUp() throws Exception 
	{
		//fileService = new FileShare();
	}
	
	@Test(expected = UnauthorizedException.class)
	public void testA() 
	{
		System.out.println("testA");
		fileService.readFile("Bob", "/home/Alice/shared/alicefile1.txt");
	}
	
	@Test
	public void testB()
	{
		System.out.println("testB");
		fileService.shareFile("Alice", "Bob", "/home/Alice/shared/alicefile1.txt");
		fileService.readFile("Bob", "/home/Alice/shared/alicefile1.txt");
	}
	
	
	@Test
	public void testC()
	{
		System.out.println("testC");
		fileService.shareFile("Alice", "Bob", "/home/Alice/shared/alicefile1.txt");
		fileService.readFile("Bob", "/home/Alice/shared/alicefile1.txt");
		fileService.shareFile("Bob", "Carl", "/home/Alice/shared/alicefile1.txt");
		fileService.readFile("Carl", "/home/Alice/shared/alicefile1.txt");
	}

	
	@Test(expected = UnauthorizedException.class)
	public void testD() 
	{
		System.out.println("testD");
		fileService.shareFile("Alice", "Bob", "/home/Alice/shared/alicefile1.txt");
		fileService.shareFile("Bob", "Alice", "/home/Carl/shared/carlfile1.txt");
		
	}
	
	@Test(expected = UnauthorizedException.class)
	public void testE()
	{
		System.out.println("testE");
		fileService.shareFile("Alice", "Bob", "/home/Alice/shared/alicefile1.txt");
		fileService.shareFile("Bob", "Carl", "/home/Alice/shared/alicefile1.txt");
		fileService.unshareFile("Alice", "Carl", "/home/Alice/shared/alicefile1.txt");
		fileService.readFile("Carl", "/home/Alice/shared/alicefile1.txt");
	}
	
	@Test(expected = UnauthorizedException.class)
	public void testF() 
	{
		System.out.println("testF");
		fileService.shareFile("Alice", "Bob", "/home/Alice/shared/alicefile1.txt");
		fileService.shareFile("Alice", "Carl", "/home/Alice/shared/alicefile1.txt");
		fileService.shareFile("Carl", "Bob", "/home/Alice/shared/alicefile1.txt");
		fileService.unshareFile("Alice", "Bob", "/home/Alice/shared/alicefile1.txt");
		fileService.readFile("Bob", "/home/Alice/shared/alicefile1.txt");
	}
	
	@Test(expected = UnauthorizedException.class)
	public void testG()
	{
		System.out.println("testG");
		fileService.shareFile("Alice", "Bob", "/home/Alice/shared/alicefile1.txt");
		fileService.shareFile("Bob", "Carl", "/home/Alice/shared/alicefile1.txt");
		fileService.unshareFile("Alice", "Bob", "/home/Alice/shared/alicefile1.txt");
		fileService.shareFile("Bob", "Carl", "/home/Alice/shared/alicefile1.txt");
		fileService.readFile("Carl", "/home/Alice/shared/alicefile1.txt");
	}
	
	@Test(expected = UnauthorizedException.class)
	public void testH()
	{
		System.out.println("testH");
		fileService.shareFile("Alice", "Bob", "/home/Alice/shared/file1.txt");
		fileService.readFile("Bob", "/home/Alice/shared/file2.txt");
	}
}
