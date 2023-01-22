package org.example.dao;

import org.example.config.DataBase;
import org.example.models.Employee;
import org.example.models.Job;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class EmployeeDaoImpl implements EmployeeDao {
    private Connection connection;
    public EmployeeDaoImpl(){
        this.connection= DataBase.getConnection();
    }
    public void createEmployee() {
        String code="create table employees(" +
                "id serial primary key, " +
                "first_name varchar not null," +
                "last_name varchar," +
                "age int," +
                "email varchar unique," +
                "job_id int references jobs(id));";
        try(Statement statement=connection.createStatement()){
            statement.executeQuery(code);

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }


    }

    public void addEmployee(Employee employee) {
        String sql = "insert into employees(" +
                "first_name,last_name,age,email,job_id)" +
                "values(?,?,?,?,?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setInt(3, employee.getAge());
            preparedStatement.setString(4, employee.getEmail());
            preparedStatement.setInt(5,employee.getJob_id());
            preparedStatement.executeUpdate();
            System.out.println(employee + "Successful added");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void dropTable() {
        String sql = "Drop table employees;";
        try (Statement statement = connection.createStatement()) {
             statement.executeUpdate(sql);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void cleanTable() {
        String sql="Truncate table employees;";
        try(Statement statement=connection.createStatement()) {
            statement.executeUpdate(sql);

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    public void updateEmployee(Long id, Employee employee) {
        String sql = "Update employees set first_name=?,last_name=?,age=?,email=?,job_id=? where id =?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1,employee.getFirstName());
            preparedStatement.setString(2,employee.getLastName());
            preparedStatement.setInt(3,employee.getAge());
            preparedStatement.setString(4,employee.getEmail());
            preparedStatement.setInt(5,employee.getJob_id());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Employee> getAllEmployees() {
        List<Employee>result=new ArrayList<>();
        String sql="Select * from employees;";
        try(Statement statement=connection.createStatement()){
            ResultSet resultSet=statement.executeQuery(sql);
            while (resultSet.next()){
                Employee employee1=new Employee();
                employee1.setId(resultSet.getLong("id"));
                employee1.setFirstName(resultSet.getString(2));
                employee1.setLastName(resultSet.getString(3));
                employee1.setAge(resultSet.getInt(4));
                employee1.setEmail(resultSet.getString(5));
                employee1.setJob_id(resultSet.getInt(6));
            result.add(employee1);}
            return result;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Employee findByEmail(String email) {
        String sql="Select * from employees  email=?;";
        try(PreparedStatement preparedStatement=connection.prepareStatement(sql)){
            preparedStatement.setString(1,email);
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                Employee employee=new Employee();
                System.out.println("Id: "+resultSet.getLong("id"));
                System.out.println("FirstName: "+resultSet.getString("first_name"));
                System.out.println("LastName: "+resultSet.getString("last_name"));
                System.out.println("Age: "+resultSet.getInt("age"));
                System.out.println("Email: "+resultSet.getString("email"));
                System.out.println("Job_id: "+resultSet.getInt("job_id"));
                System.out.println();
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Map<Employee, Job> getEmployeeById(Long employeeId) {
        Map<Employee,Job> result =new LinkedHashMap<>();
        String sql="Select * from employees join jobs j on employees.job_id=j.id where employees.id=?";
        try(PreparedStatement preparedStatement=connection.prepareStatement(sql)){
             preparedStatement.setLong(1,employeeId);
             ResultSet resultSet=preparedStatement.executeQuery();
             while (resultSet.next()){
                 Employee employee=new Employee();
                 Job job=new Job();
                employee= new Employee(resultSet.getLong(1), resultSet.getString(2),
                         resultSet.getString(3), resultSet.getInt(4),
                         resultSet.getString(5), resultSet.getInt(6),
                        job= new Job(resultSet.getLong(7), resultSet.getString(8),
                                 resultSet.getString(9), resultSet.getInt(10)));
                result.put(employee,job);
             }
             return result;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<Employee> getEmployeeByPosition(String position) {
        List<Employee>result=new ArrayList<>();
        String sql="Select position from employees join jobs j on employees.job_id=j.id where j.position=?;";
        try(PreparedStatement preparedStatement=connection.prepareStatement(sql)){
            preparedStatement.setString(1,position);
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                Employee employee=new Employee();
                employee.setId(resultSet.getLong("id"));
                employee.setFirstName(resultSet.getString(2));
                employee.setLastName(resultSet.getString(3));
                employee.setAge(resultSet.getInt(4));
                employee.setEmail(resultSet.getString(5));
                employee.setJob_id(resultSet.getInt(6));

                result.add(employee);
                return result;
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
