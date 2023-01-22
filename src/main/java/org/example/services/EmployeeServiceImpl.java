package org.example.services;

import org.example.dao.EmployeeDao;
import org.example.dao.EmployeeDaoImpl;
import org.example.models.Employee;
import org.example.models.Job;

import java.util.List;
import java.util.Map;

public class EmployeeServiceImpl  implements EmployeeService{
    EmployeeDao employeeDao=new EmployeeDaoImpl();
    @Override
    public void createEmployee() {
      employeeDao.createEmployee();
    }

    @Override
    public void addEmployee(Employee employee) {
        employeeDao.addEmployee(employee);

    }

    @Override
    public void dropTable() {
        employeeDao.dropTable();

    }

    @Override
    public void cleanTable() {
        employeeDao.cleanTable();

    }

    @Override
    public void updateEmployee(Long id, String employee) {
        employeeDao.updateEmployee(id, employee);

    }

    @Override
    public List<Employee> getAllEmployees() {
        System.out.println(employeeDao.getAllEmployees());
        return null ;
    }

    @Override
    public Employee findByEmail(String email) {
        System.out.println(employeeDao.findByEmail(email));

        return null;
    }

    @Override
    public Map<Employee, Job> getEmployeeById(Long employeeId) {
        System.out.println(employeeDao.getEmployeeById(employeeId));
        return null;
    }

    @Override
    public List<Employee> getEmployeeByPosition(String position) {
        System.out.println(employeeDao.getEmployeeByPosition(position));

        return null;
    }
}
