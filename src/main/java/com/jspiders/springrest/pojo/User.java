package com.jspiders.springrest.pojo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.web.servlet.FlashMap;

import lombok.Data;

@Data
@Entity
@Table(name="user3")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private String userName;
	@Column(nullable = false,unique = false)
	private String password;
	@Column(nullable = false,unique = true)
	private String email;
	@Column(nullable = false,unique = true)
	private long mobile;
	@OneToMany
	@Cascade(CascadeType.ALL)
	private List<Car> cars;

}
