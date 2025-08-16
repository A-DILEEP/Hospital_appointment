package com.java.hospital_sample.Repositary;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;


import com.java.hospital_sample.user.Appointment;



public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    List<Appointment> findByDoctor_Docid(Long docid);
    List<Appointment> findByUser_Userid(Long userid);
}
