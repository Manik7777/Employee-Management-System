package com.manik.RESTAPI;

import com.manik.RESTAPI.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.manik.RESTAPI.model.Employee;

@SpringBootApplication
public class RestApiApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(RestApiApplication.class, args);
	}
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public void run(String... args) throws Exception {
		Employee employee = new Employee();
		employee.setFirstName("Manik");
		employee.setLastName("Manchanda");
		employee.setEmailId("manik@manik.com");
		employeeRepository.save(employee);

		Employee employee1 = new Employee();
		employee1.setFirstName("Manish");
		employee1.setLastName("Manchanda");
		employee1.setEmailId("manish@manik.com");
		employeeRepository.save(employee1);

		Employee employee2 = new Employee();
		employee2.setFirstName("Umesh");
		employee2.setLastName("Manchanda");
		employee2.setEmailId("umesh@manik.com");
		employeeRepository.save(employee2);
	}
}
