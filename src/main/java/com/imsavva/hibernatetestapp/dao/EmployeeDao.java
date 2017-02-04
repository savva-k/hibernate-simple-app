package com.imsavva.hibernatetestapp.dao;

import java.util.List;

import com.imsavva.hibernatetestapp.entities.Employee;

/**
 * Employee DAO interface.
 * 
 * @author Savva Kodeikin
 *
 */
public interface EmployeeDao {

    /**
     * Save entity to the database.
     * 
     * @param employee entity
     */
    void save(Employee employee);

    /**
     * Update existing entity.
     * 
     * @param employee entity
     */
    void update(Employee employee);

    /**
     * Delete entry from the database.
     * 
     * @param id
     */
    void delete(long id);

    /**
     * Find employee by id.
     * 
     * @param id to find
     * @return employee entity
     */
    Employee findById(long id);

    /**
     * Find employee by name.
     * 
     * @param name
     * @return employee entity
     */
    List<Employee> findByName(String name);

    /**
     * Get all employees.
     * 
     * @return list of employee entities
     */
    List<Employee> getAll();
}
