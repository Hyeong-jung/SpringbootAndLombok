package com.springboot.lombok.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
 
import javax.persistence.*;



@Entity
@Table(name = "employees")
// Lombok이 toString (), equals (), hashCode (), getter () & setter () 및 필수 인수 생성자를 한 번에 생성.
@Data
// Lombok이 POJO 클래스에 대한 빌더 디자인 패턴을 구현.
// 사용법은 DefaultEmployeesLoader.java-> createNewEmployee () 메소드
@Builder
// Lombok이 매개 변수없이 생성자를 생성
@NoArgsConstructor
// Lombok이 클래스의 각 필드에 대해 1 개의 매개 변수가있는 생성자를 생성
@AllArgsConstructor
@Component
public class Employee {

	
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // MariaDB
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_generator") // Oracle DB
    //@SequenceGenerator(name="employee_generator", sequenceName = "employee_seq", allocationSize=50) // Oracle DB
    long id;
    @Column(name = "first_name", nullable = false)
    String firstName;
    @Column(name = "last_name", nullable = false)
    String lastName;
    @Column(name = "gender")
    String gender;
    @Column(name = "email", nullable = false)
    String email;
    //@Column(name = "phone_number", unique = true)
    @Column(name = "phone_number")
    String phoneNumber;
    @Column(name = "home_address")
    String homeAddress;	
	
}
