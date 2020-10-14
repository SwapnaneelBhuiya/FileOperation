package EmployeePayroll;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

import org.junit.Test;

import junit.framework.*;

public class NIOFileAPITest {


	private static String HOME=System.getProperty("user.home");
	private static String PLAY_WITH_NIO="TempPlayGround";
	@SuppressWarnings("deprecation")
	@Test
	public void givenPathWhenCheckedThenConfirm()throws IOException
	{
		Path homePath=Paths.get(HOME);
		Assert.assertTrue(Files.exists(homePath));
		Path playPath = Paths.get(HOME+"/"+PLAY_WITH_NIO);
		//if(Files.exists(playPath))FileUtils.deleteFiles(playPath.toFile());
		Assert.assertTrue(Files.notExists(playPath));
		Files.createDirectory(playPath);
		Assert.assertTrue(Files.exists(playPath));
		
		//create file
		IntStream.range(1,10).forEach(cntr->{
			Path tempFile=Paths.get(playPath+ "/temp"+cntr);
			Assert.assertTrue(Files.notExists(tempFile));
			try {
				Files.createFile(tempFile);}
			catch(IOException e)
			{}
			Assert.assertTrue(Files.exists(tempFile));
		});
		Files.list(playPath).filter(Files::isRegularFile).forEach(System.out::println);
		Files.newDirectoryStream(playPath).forEach(System.out::println);
		Files.newDirectoryStream(playPath, path -> path.toFile().isFile()&&path.toString().startsWith("temp")).forEach(System.out::println);;
		}
@Test
public void given3EmployeesWhenWrittentoFileShouldMatchEmployeeEntries()
{
	ArrayList<EmployeePayrollData> ar=new ArrayList<>();
			ar.add(new EmployeePayrollData(1,"Jeff",10000.0));
			ar.add(new EmployeePayrollData(2,"Bill",200000));
			ar.add(new EmployeePayrollData(3,"Sam",311111));
	EmployeePayrollService emp;
	emp=new EmployeePayrollService(ar);
	emp.writeEmployeePayrollData(EmployeePayrollService.IOService.FILE_IO);	
	emp.printData(EmployeePayrollService.IOService.FILE_IO);
	long entries=emp.countEntries(EmployeePayrollService.IOService.FILE_IO);
	Assert.assertEquals(3, entries);
	}
}
