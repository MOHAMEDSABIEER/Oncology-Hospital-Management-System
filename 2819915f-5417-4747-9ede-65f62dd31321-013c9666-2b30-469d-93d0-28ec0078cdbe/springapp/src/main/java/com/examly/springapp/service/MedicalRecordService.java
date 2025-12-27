package com.examly.springapp.service;

import com.examly.springapp.model.MedicalRecord;
import com.examly.springapp.model.Patient;
import com.examly.springapp.repository.MedicalRecordRepository;
import com.examly.springapp.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalRecordService {

    private final MedicalRecordRepository repo;
    private final PatientRepository patientRepository;

    public MedicalRecordService(MedicalRecordRepository repo, PatientRepository patientRepository) {
        this.repo = repo;
        this.patientRepository = patientRepository;
    }

    public MedicalRecord save(MedicalRecord record) {
        // If patient is provided with just ID, fetch the full patient entity
        if (record.getPatientId() != null && record.getPatientId().getPatientId() != null) {
            Patient patient = patientRepository.findById(record.getPatientId().getPatientId()).orElse(null);
            record.setPatientId(patient);
        }
        return repo.save(record);
    }

    public List<MedicalRecord> getByPatientId(Long patientId) {
        return repo.findByPatientId_PatientId(patientId);
    }

    public MedicalRecord getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public MedicalRecord update(Long id, MedicalRecord record) {
        record.setRecordId(id);
        return save(record);
    }
}
