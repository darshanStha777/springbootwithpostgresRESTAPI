package net.darshan.springbakcend.controller;

import net.darshan.springbakcend.exception.ResourceNotFoundException;
import net.darshan.springbakcend.model.Employee;
import net.darshan.springbakcend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    public EmployeeRepository employeeRepository;

    @GetMapping("/employees")
    public List<Employee> getAllEmployees () {
        return employeeRepository.findAll();
    }

    @PostMapping("/employees")
    public  Employee createEmployee(@RequestBody Employee employee){
        return  employeeRepository.save(employee);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found with id"+id));
        return ResponseEntity.ok(employee);
    }

    //put update reques
    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployeeDetail(@PathVariable long id, @RequestBody Employee employee) {
        Employee updateemployee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found with id"+id));

        updateemployee.setFirstName(employee.getFirstName());
        updateemployee.setLastName(employee.getLastName());
        updateemployee.setEmailId(employee.getEmailId());
         employeeRepository.save(updateemployee);
         return  ResponseEntity.ok(updateemployee);
    }

    //delete data

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable long id) {
        Employee deletedata = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not existed with id ="+id));

        employeeRepository.delete(deletedata);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
