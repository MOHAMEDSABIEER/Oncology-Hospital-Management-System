// package com.examly.springapp.model;



// import jakarta.persistence.*;
// import java.time.LocalDateTime;
// import com.fasterxml.jackson.annotation.JsonProperty;

// @Entity
// public class Appointment {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @ManyToOne(cascade = CascadeType.MERGE)
//     @JsonProperty("patient")
//     private Patient patient;

//     @ManyToOne(cascade = CascadeType.MERGE)
//     @JsonProperty("doctor")
//     private Doctor doctor;

//     private LocalDateTime appointmentTime;

//     private String status;
//     private String notes;

//     public Appointment() {
//         //
//     }

//     // Getters and Setters
//     public Long getAppointmentId() {
//         return id;
//     }

//     public void setAppointmentId(Long id) {
//         this.id = id;
//     }

//     public Patient getPatientId() {
//         return patient;
//     }

//     public void setPatientId(Patient patient) {
//         this.patient = patient;
//     }

//     public Doctor getDoctorId() {
//         return doctor;
//     }

//     public void setDoctorId(Doctor doctor) {
//         this.doctor = doctor;
//     }

//     public LocalDateTime getAppointmentTime() {
//         return appointmentTime;
//     }

//     public void setAppointmentTime(LocalDateTime appointmentTime) {
//         this.appointmentTime = appointmentTime;
//     }

//     public String getStatus() {
//         return status;
//     }

//     public void setStatus(String status) {
//         this.status = status;
//     }

//     public String getNotes() {
//         return notes;
//     }

//     public void setNotes(String notes) {
//         this.notes = notes;
//     }
// }
package com.examly.springapp.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Patient patient;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Doctor doctor;

    private LocalDateTime appointmentTime;
    private String status;
    private String notes;

    public Appointment() {}

    // getters & setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public LocalDateTime getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(LocalDateTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
