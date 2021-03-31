package com.springboot.lombok.model;

import lombok.*; 
import javax.persistence.*;


@Data
@AllArgsConstructor 
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name="member")
public class MemberVo {
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GeneratedValue(strategy = GenerationType.SEQUENCE) // MariaDB
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_generator")  // Oracle DB
	//@SequenceGenerator(name="member_generator", sequenceName = "member_seq", allocationSize=50)	 // Oracle DB
	private Long mbrNo;
	private String id;
	private String name;
	
	@Builder
	public MemberVo(String id, String name) {
		this.id = id;
		this.name = name;
	}

	
}
