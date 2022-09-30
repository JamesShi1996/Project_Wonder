package com.bonwonder.crmdemo.dao;

import com.bonwonder.crmdemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "members")
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // to be implemented

    /*
        select * from employee where first_name = 'abc';
        select * from employee where last_name = 'abc';
        join
     */

    List<Employee> findDistinctEmployeeByFirstNameOrLastName(String firstname, String lastName);

    List<Employee> findAllByOrderByFirstNameAsc();

    List<Employee> findAllByOrderByLastNameAsc();

    List<Employee> findAllByOrderByEmailAsc();
}
