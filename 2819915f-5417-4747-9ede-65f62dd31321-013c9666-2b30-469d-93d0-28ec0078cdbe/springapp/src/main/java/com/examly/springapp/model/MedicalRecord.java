package com.examly.springapp.model;
import com.fasterxml.jackson.annotation.JsonProperty;


import jakarta.persistence.*;

@Entity
public class MedicalRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recordId;

    private String diagnosis;
    private String prescription;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JsonProperty("patient") 
    private Patient patientId;

    public MedicalRecord() {
        //
    }

    // Getters and Setters
    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    public Patient getPatientId() {
        return patientId;
    }

    public void setPatientId(Patient patientId) {
        this.patientId = patientId;
    }
}
