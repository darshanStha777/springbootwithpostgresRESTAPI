package net.darshan.springbakcend.repository;

import net.darshan.springbakcend.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository  extends JpaRepository<Employee,Long> {

}
