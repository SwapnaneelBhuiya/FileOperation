package EmployeePayroll;

	import java.awt.List;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.*;
	import java.util.Scanner;
	public class EmployeePayrollService {
		public enum IOService{CONSOLE_IO,FILE_IO, DB_IO,REST_IO}
		private ArrayList<EmployeePayrollData> employeePayrollList;
		public static String PAYROLL_FILE_NAME="payroll-file.txt";
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
			if(ioService.equals(IOService.CONSOLE_IO))
			{
			System.out.println("Writing Employee payroll roaster to console"+employeePayrollList);
			}
			else if(ioService.equals(IOService.FILE_IO))
				writeData(employeePayrollList);
		}
		public void printData(IOService fileIo) {
			try {
				Files.lines(new File("payroll-file.txt").toPath()).forEach(System.out::println);
			}
			catch(IOException e) {}
		}
		public long countEntries(IOService fileIo) {
			long entries=0;
			try {
				entries=Files.lines(new File("payroll-file.txt").toPath()).count();
				
			}
			catch(Exception e) {}
			return entries;
		}
		public void writeData(ArrayList<EmployeePayrollData> employeePayrollList)
		{
			StringBuffer empBuffer=new StringBuffer();
			employeePayrollList.forEach(employee-> {
				String employeeDataString=employee.toString().concat("\n");
				empBuffer.append(employeeDataString);
			});
			try {
				Files.write(Paths.get(PAYROLL_FILE_NAME),empBuffer.toString().getBytes());
			}
			catch(IOException e) {}
		}
	}