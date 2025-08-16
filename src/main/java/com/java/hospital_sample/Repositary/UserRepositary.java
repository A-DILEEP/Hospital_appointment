package com.java.hospital_sample.Repositary;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.hospital_sample.user.User;



public interface UserRepositary extends JpaRepository<User,Integer>{
	User findByName(String name);

}
