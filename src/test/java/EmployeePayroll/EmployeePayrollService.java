package EmployeePayroll;

	import java.awt.List;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.*;
	import java.util.Scanner;
	public class EmployeePayrollService {
		public static String PAYROLL_FILE_NAME="user.home";
		public enum IOService{CONSOLE_IO,FILE_IO, DB_IO,REST_IO}
		private ArrayList<EmployeePayrollData> employeePayrollList;
		public EmployeePayrollService() {}
		public EmployeePayrollService(ArrayList<EmployeePayrollData> employeePayrollList)
		{this.employeePayrollList=employeePayrollList;
		}
		public static void main(String args[])
		{
			ArrayList<EmployeePayrollData> employee=new ArrayList<>();
			EmployeePayrollService employeePayrollService=new EmployeePayrollService(employee);
			Scanner sc=new Scanner(System.in);
			employeePayrollService.readEmployeePayrollData(sc);
			//employeePayrollService.writeEmployeePayrollData();
		}
		public void readEmployeePayrollData(Scanner sc)
		{
			System.out.println("Enter employee ID");
			int id=sc.nextInt();
			System.out.println("Enter employee name");
			String name=sc.nextLine();
			System.out.println("Enter salary");
			double salary=sc.nextDouble();
			employeePayrollList.add(new EmployeePayrollData(id,name,salary));
		}
		public void writeEmployeePayrollData(EmployeePayroll.EmployeePayrollService.IOService ioService)
		{
			System.out.println("Writing Employee payroll roaster to console"+employeePayrollList);
		}
		public void printData(IOService fileIo) {
			try {
				Files.lines(new File("user.home").toPath()).forEach(System.out::println);
			}
			catch(IOException e) {}
		}
		public long countEntries(IOService fileIo) {
			long entries=0;
			try {
				entries=Files.lines(new File("user.home").toPath()).count();
				
			}
			catch(Exception e) {}
			return entries;
		}
		
	}
	