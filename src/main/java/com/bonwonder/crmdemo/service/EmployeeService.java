package com.bonwonder.crmdemo.service;

import com.bonwonder.crmdemo.entity.Employee;

import java.util.List;

public interface EmployeeService{
    List<Employee> findAll();

    Employee findById(int id);

    void save(Employee employee);

    void deleteById(int id);

    List<Employee> search(String keyword);

    List<Employee> sort(int sort);
}
