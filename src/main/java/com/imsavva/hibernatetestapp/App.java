package com.imsavva.hibernatetestapp;

import java.util.Date;
import java.util.List;

import com.imsavva.hibernatetestapp.dao.EmployeeDao;
import com.imsavva.hibernatetestapp.dao.impl.HibernateEmployeeDaoImpl;
import com.imsavva.hibernatetestapp.entities.Employee;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        EmployeeDao employeeDao = new HibernateEmployeeDaoImpl();
        
        Employee employee1 = new Employee();
        employee1.setName("Ivan");
        employee1.setSurname("Petrov");
        employee1.setAddedAt(new Date());
        
        Employee employee2 = new Employee();
        employee2.setName("John");
        employee2.setSurname("Smith");
        employee2.setAddedAt(new Date());

        employeeDao.save(employee1);
        employeeDao.save(employee2);
        
        employee1.setName("Petr");
        
        employeeDao.update(employee1);

        System.out.println("Listing employees with name Petr");
        
        List<Employee> emploees = employeeDao.findByName("petr");
        
        for (Employee employee : emploees) {
            System.out.println(employee);
            employeeDao.delete(employee.getId());
        }       
        
        System.out.println("Listing and deleting all employees");
        
        emploees = employeeDao.getAll();
        
        for (Employee employee : emploees) {
            System.out.println(employee);
            employeeDao.delete(employee.getId());
        }
        
        ((HibernateEmployeeDaoImpl) employeeDao).shutdown();
    }
}
