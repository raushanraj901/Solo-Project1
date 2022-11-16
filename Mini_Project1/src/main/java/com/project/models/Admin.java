package com.project.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Admin {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer adminId;
	@Size(min = 3,max = 20,message = "Name length shoud be minimum 3 and maximum 10")
	private String adminName;
	@Email
	private String email;
	@Size(min=4,message = "Password length shoud be minimum 4")
	private String password;
	@Size(min = 10,max = 10,message = "Mobile length should be minimum and maximum 10")
	private String mobile;

}
