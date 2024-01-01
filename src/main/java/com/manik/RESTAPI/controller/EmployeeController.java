package com.manik.RESTAPI.controller;

import com.manik.RESTAPI.exception.ResourceNotFoundException;
import com.manik.RESTAPI.model.Employee;
import com.manik.RESTAPI.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("api/v1/employees")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    //get list of employees
    @GetMapping
    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    // create/save an employee
    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee){
    return employeeRepository.save(employee);
    }

    // get employee by employeeId
    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable long id){
        Employee employee = employeeRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Employee does not exist with id" + id));
        return ResponseEntity.ok(employee);
    }

    //update employee details by employeeId
    @PutMapping("{id}")
    public ResponseEntity<Employee> updateEmployeeDetails(@PathVariable long id, @RequestBody Employee employeeDetails)
    {
        Employee employeeToBeUpdated = employeeRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Employee does not exist with id" + id));

        employeeToBeUpdated.setFirstName(employeeDetails.getFirstName());
        employeeToBeUpdated.setLastName(employeeDetails.getLastName());
        employeeToBeUpdated.setEmailId(employeeDetails.getEmailId());

        employeeRepository.save(employeeToBeUpdated);
        return ResponseEntity.ok(employeeToBeUpdated);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable long id){
        Employee employee = employeeRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Employee does not exist with id" + id));

        employeeRepository.deleteById(id);
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }
}
