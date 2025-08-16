package com.java.hospital_sample.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.java.hospital_sample.Repositary.AppointmentRepository;
import com.java.hospital_sample.Repositary.DoctorRepository;
import com.java.hospital_sample.Repositary.UserRepositary;
import com.java.hospital_sample.user.Appointment;
import com.java.hospital_sample.user.Doctor;
import com.java.hospital_sample.user.User;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentRepository appointmentRepo;

    @Autowired
    private UserRepositary userRepo;

    @Autowired
    private DoctorRepository doctorRepo;

    // ✅ Book appointment and return full details
    @PostMapping("/book")
    public ResponseEntity<Appointment> bookAppointment(@RequestParam int userid,
                                                       @RequestParam Long docid,
                                                       @RequestParam(required = false) String date) {
        User user = userRepo.findById(userid).orElse(null);
        Doctor doctor = doctorRepo.findById(docid).orElse(null);

        if (user == null || doctor == null) {
            return ResponseEntity.badRequest().build();
            
        }

        LocalDate appointmentDate = (date != null) ? LocalDate.parse(date) : LocalDate.now();

        Appointment appointment = new Appointment();
        appointment.setUser(user);
        appointment.setDoctor(doctor);
        appointment.setAppointmentDate(appointmentDate);

        Appointment savedAppointment = appointmentRepo.save(appointment);

        return ResponseEntity.ok(savedAppointment);
    }

    // Doctor views all appointments
    @GetMapping("/doctor/{docid}")
    public List<Appointment> getDoctorAppointments(@PathVariable Long docid) {
        return appointmentRepo.findByDoctor_Docid(docid);
    }
}
