package com.java.hospital_sample.user;

import jakarta.persistence.*;

@Entity
@Table(name = "prescriptions")

public class Prescription {
	
	

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String medication;
	    private String dosage;
	    private String instructions;

	    @ManyToOne
	    @JoinColumn(name = "user_id", nullable = false)
	    private User user;

	    public Prescription() {}

	    public Prescription(String medication, String dosage, String instructions, User user) {
	        this.medication = medication;
	        this.dosage = dosage;
	        this.instructions = instructions;
	        this.user = user;
	    }

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getMedication() {
			return medication;
		}

		public void setMedication(String medication) {
			this.medication = medication;
		}

		public String getDosage() {
			return dosage;
		}

		public void setDosage(String dosage) {
			this.dosage = dosage;
		}

		public String getInstructions() {
			return instructions;
		}

		public void setInstructions(String instructions) {
			this.instructions = instructions;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

	    // Getters and setters
	}


	


