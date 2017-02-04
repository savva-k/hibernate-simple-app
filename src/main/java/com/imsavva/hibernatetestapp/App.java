package com.imsavva.hibernatetestapp;

import java.util.Date;

import org.hibernate.Session;

import com.imsavva.hibernatetestapp.entities.Employee;
import com.imsavva.hibernatetestapp.utils.HibernateUtils;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Employee employee = new Employee();
        employee.setName("Ivan");
        employee.setSurname("Petrov");
        employee.setAddedAt(new Date());
        
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(employee);
        session.getTransaction().commit();
        System.out.println(employee);
        HibernateUtils.getSessionFactory().close();
    }
}
