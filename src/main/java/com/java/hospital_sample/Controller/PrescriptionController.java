package com.java.hospital_sample.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.hospital_sample.Repositary.AppointmentRepository;
import com.java.hospital_sample.Repositary.PrescriptionRepository;
import com.java.hospital_sample.Repositary.UserRepositary;
import com.java.hospital_sample.user.Appointment;
import com.java.hospital_sample.user.Prescription;
import com.java.hospital_sample.user.User;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
@RestController
@RequestMapping("/users/{userId}/prescriptions")
public class PrescriptionController {

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @Autowired
    private UserRepositary userRepository;

    @PostMapping
    public ResponseEntity<?> addPrescription(
            @PathVariable Long userId,
            @RequestBody Prescription prescriptionRequest) {

        // Fetch user by ID
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Link user to prescription
        prescriptionRequest.setUser(user);

        // Save prescription
        Prescription savedPrescription = prescriptionRepository.save(prescriptionRequest);

        return ResponseEntity.ok(savedPrescription);
    }
}

