package org.example;

import org.example.models.Employee;
import org.example.models.Job;
import org.example.services.EmployeeService;
import org.example.services.EmployeeServiceImpl;
import org.example.services.JobService;
import org.example.services.JobServiceImpl;

import java.util.Scanner;



/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        JobService jobService=new JobServiceImpl();
        EmployeeService employeeService=new EmployeeServiceImpl();
        while (true){
            byte sell=new Scanner(System.in).nextByte();
            switch (sell){
                case 1->   jobService.createJobTable();
                case 2-> employeeService.createEmployee();
                case 3-> jobService.addJob(new Job("Mentor","Java",5,"Backend_developer"));
                case 4->{
                    System.out.println("Enter the job id: ");
                    long id=new Scanner(System.in).nextLong();
                    System.out.println(jobService.getJobById(id));
                }
                case 5->{
                    System.out.println("Enter the word ");
                    String word=new Scanner(System.in).nextLine();
                    System.out.println(jobService.sortByExperience(word));
                }
                case 6->jobService.deleteDescriptionColumn();
                case 7->employeeService.addEmployee(new Employee("Aijamal","Asangazieva",24,"aijamal@gmail.com",5));
                case 8->{
                    System.out.println("Enter the employee id ");
                    long id=new Scanner(System.in).nextLong();
                    System.out.println(employeeService.getEmployeeById(id));}
                case 9-> System.out.println(employeeService.getAllEmployees());
                case 10->{
                    System.out.println("Enter the email");
                    String email=new Scanner(System.in).nextLine();
                    System.out.println(employeeService.findByEmail(email));}
                case 11->{
                    System.out.println("The id you want to change: ");
                    long id = new Scanner(System.in).nextLong();
                    employeeService.updateEmployee(id, new Scanner(System.in).nextLine());
                }
                case 12->employeeService.cleanTable();
                case 13->employeeService.dropTable();
                case 14->{
                    System.out.println("Enter the position ");
                    String position=new Scanner(System.in).nextLine();
                    employeeService.getEmployeeByPosition(position);}




            }
        }


    }
}
