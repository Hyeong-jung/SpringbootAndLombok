package com.springboot.lombok.bootstrap;


import com.github.javafaker.Faker;
import com.springboot.lombok.model.Employee;
import com.springboot.lombok.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.Random;



// lombok 로거 필드 생성
@Slf4j
// lombok 처리가 필요한 각 필드에 1 개 매개 변수 생성자 생성
@RequiredArgsConstructor
@Component
public class DefaultEmployeesLoader implements CommandLineRunner {
	
    private static final String[] GENDER = {"Male", "Female", "Transgender", "Not to specify"};
    private static final Random RANDOM = new Random();
 
    private final EmployeeService service;
    private final Faker faker;
    
    @Override
    public void run(String... args) throws Exception {
        loadEmployees();
    }    
    
    private void loadEmployees() {
        int count = 0;
        if (service.getTotalEmployees() == 0) {
            for (int x = 0; x < 100; x++) {
                count = count + 1;
                service.save(createNewEmployee());
            }
            log.info("총 {} 명의 직원 DB 저장", count);
        } else {
        	log.info("기본 데이터 존재");
        }
    }
    
    private Employee createNewEmployee() {
        final String firstName = faker.name().firstName();
        final String lastName = faker.name().lastName();
        final String gender = GENDER[RANDOM.nextInt(GENDER.length)];
        final String emailAddress = firstName.toLowerCase() + "." + lastName.toLowerCase() + "@somecompany.com";
 
        return Employee.builder()
                .firstName(firstName)
                .lastName(lastName)
                .gender(gender)
                .email(emailAddress)
                .phoneNumber(faker.phoneNumber().cellPhone())
                .homeAddress(faker.address().fullAddress())
                .build();
    }    
	

}
