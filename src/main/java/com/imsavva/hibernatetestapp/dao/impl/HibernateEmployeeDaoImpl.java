package com.imsavva.hibernatetestapp.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.imsavva.hibernatetestapp.dao.EmployeeDao;
import com.imsavva.hibernatetestapp.entities.Employee;

/**
 * Hibernate EmployeeDao implementation.
 * 
 * @author Savva Kodeikin
 *
 */
public class HibernateEmployeeDaoImpl implements EmployeeDao {

    private static final String PERSISTENCE_UNIT = "hibernate_test";
    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);

    public void save(Employee employee) {
        EntityManager em = startTransaction();
        em.persist(employee);
        commitTransaction(em);
    }

    public void update(Employee employee) {
        EntityManager em = startTransaction();
        em.merge(employee);
        commitTransaction(em);
    }

    public void delete(long id) {
        EntityManager em = startTransaction();
        Employee employee = em.find(Employee.class, id);
        em.remove(employee);
        commitTransaction(em);
    }

    public Employee findById(long id) {
        EntityManager em = factory.createEntityManager();
        Employee employee = em.find(Employee.class, id);
        em.close();

        return employee;
    }

    public List<Employee> findByName(String name) {
        EntityManager em = factory.createEntityManager();
        List<Employee> employees = em.createQuery("SELECT e FROM Employee e WHERE e.name = :name", Employee.class)
                .setParameter("name", name).getResultList();
        em.close();
        
        return employees;
    }

    public List<Employee> getAll() {
        EntityManager em = factory.createEntityManager();
        List<Employee> emploees = em.createQuery("from Employee", Employee.class).getResultList();
        em.close();
        
        return emploees;
    }
    
    public void shutdown() {
        factory.close();
    }

    private EntityManager startTransaction() {
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();

        return em;
    }

    private void commitTransaction(EntityManager em) {
        em.getTransaction().commit();
        em.close();
    }

}
