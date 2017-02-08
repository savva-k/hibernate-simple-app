package com.imsavva.hibernatetestapp;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.imsavva.hibernatetestapp.dao.EmployeeDao;
import com.imsavva.hibernatetestapp.dao.impl.HibernateEmployeeDaoImpl;
import com.imsavva.hibernatetestapp.entities.Employee;

/**
 * HibernateEmployeeDaoImpl test case.
 * 
 * @author Savva Kodeikin
 *
 */
public class HibernateEmployeeDaoTest {
    
    private static EmployeeDao employeeDao;
    private Employee employee;

    @BeforeClass
    public static void createDao() throws Exception {
        employeeDao = new HibernateEmployeeDaoImpl();
    }

    @AfterClass
    public static void closeDao() throws Exception {
        ((HibernateEmployeeDaoImpl) employeeDao).shutdown();
    }
    
    @Before
    public void setUp() {
        employee = new Employee();
        
        employee.setName("Ivan");
        employee.setSurname("Petrov");
        employee.setAddedAt(Calendar.getInstance());
        
        employeeDao.save(employee);
    }
    
    @After
    public void tearDown() {
        if (employee != null) {
            employeeDao.delete(employee.getId());
        }
    }

    @Test
    public void testCreateEmployee() {
        Employee employeeFromDB = employeeDao.findById(employee.getId());
        assertNotNull("Employee instance was created", employeeFromDB);
    }
    
    @Test
    public void testUpdateEmployee() {
        Calendar newDate = getDifferentCalendarInstance();
        
        employee.setName("Petr");
        employee.setSurname("Ivanov");
        employee.setAddedAt(newDate);
        
        employeeDao.update(employee);
        employee = employeeDao.findById(employee.getId());

        assertEquals("Employee's name is Petr", "Petr", employee.getName());
        assertEquals("Employee's surname is Ivanov", "Ivanov", employee.getSurname());
        assertEquals("Employee's added time is equal to newDate", newDate, employee.getAddedAt());
    }
    
    @Test
    public void testDeleteEmployee() {
        long id = employee.getId();
        employee = null;
        employeeDao.delete(id);
        
        assertNull("Employee was deleted", employeeDao.findById(id));
    }
    
    @Test
    public void testFindById() {
        assertNotNull("Employee was found by id", employeeDao.findById(employee.getId()));
    }
    
    @Test
    public void testFindByName() {
        assertNotNull("Employee was found by name", employeeDao.findByName(employee.getName()));
    }
    
    @Test
    public void testGetAll() {
        List<Employee> employees = employeeDao.getAll();
        
        assertNotNull("List of employees is not null", employees);
        assertTrue("List of employees contain entities", !employees.isEmpty());
    }

    private Calendar getDifferentCalendarInstance() {
        Calendar newDate = Calendar.getInstance();
        newDate.set(Calendar.MILLISECOND, 0);
        newDate.add(Calendar.MONTH, -5);
        
        return newDate;
    }

}
