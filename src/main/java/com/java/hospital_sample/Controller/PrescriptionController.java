package com.java.hospital_sample.Controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.java.hospital_sample.DTO.PrescriptionDTO;
import com.java.hospital_sample.Repositary.AppointmentRepository;
import com.java.hospital_sample.Repositary.PrescriptionRepository;
import com.java.hospital_sample.user.Appointment;
import com.java.hospital_sample.user.Prescription;

@RestController
@RequestMapping("/prescriptions")
public class PrescriptionController {

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @PostMapping("/write")
    public ResponseEntity<Map<String, Object>> createPrescription(@RequestBody PrescriptionDTO request) {

        Appointment appointment = appointmentRepository.findById(request.getAppointmentId())
            .orElseThrow(() -> new RuntimeException("Appointment not found"));

        Prescription prescription = new Prescription();
        prescription.setDosage(request.getDosage());
        prescription.setInstructions(request.getInstructions());
        prescription.setMedication(request.getMedication());
        prescription.setAppointment(appointment);
        prescription.setUserId(request.getUserId());
        prescription.setDocId(request.getDocId());
        prescription.setCreatedDate(LocalDate.now());
        prescription.setMedicines(request.getMedicines());
        prescription.setDetails(request.getDetails());

        Prescription savedPrescription = prescriptionRepository.save(prescription);

        Map<String, Object> response = new HashMap<>();
        response.put("prescription", savedPrescription);
        response.put("appointmentDate", appointment.getAppointmentDate());

        return ResponseEntity.ok(response);
    }
}
