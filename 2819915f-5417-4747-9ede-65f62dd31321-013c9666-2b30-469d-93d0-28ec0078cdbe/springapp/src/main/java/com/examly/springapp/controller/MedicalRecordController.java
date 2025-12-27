package com.examly.springapp.controller;

import com.examly.springapp.model.MedicalRecord;
import com.examly.springapp.service.MedicalRecordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicalrecords")
public class MedicalRecordController {

    private final MedicalRecordService service;

    public MedicalRecordController(MedicalRecordService service) {
        this.service = service;
    }

    // POST /medicalrecords
    @PostMapping
    public ResponseEntity<MedicalRecord> addMedicalRecord(@RequestBody MedicalRecord record) {
        MedicalRecord saved = service.save(record);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/patient/{id}")
    public ResponseEntity<?> getMedicalRecordsByPatient(@PathVariable Long id) {

        List<MedicalRecord> records = service.getByPatientId(id);

        if (records.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body("No medical records found");
        }

        return ResponseEntity.ok(records);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicalRecord> getMedicalRecordById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicalRecord> updateMedicalRecord(@PathVariable Long id,
                                                             @RequestBody MedicalRecord record) {
        return ResponseEntity.ok(service.update(id, record));
    }
}
