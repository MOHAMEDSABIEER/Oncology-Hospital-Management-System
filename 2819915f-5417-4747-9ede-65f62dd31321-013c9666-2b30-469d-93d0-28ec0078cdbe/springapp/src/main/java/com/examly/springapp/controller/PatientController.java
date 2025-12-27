// package com.examly.springapp.controller;

// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.*;

// @RestController

// @RequestMapping
// public class PatientController {
//     @RequestMapping("/{id}")
//     public String getPatient(@PathVariable int id) {
//         return "Patient ID: " + id;
//     }
    
// }

package com.examly.springapp.controller;

import com.examly.springapp.model.Patient;
import com.examly.springapp.repository.PatientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patients")
public class PatientController {

    private final PatientRepository patientRepository;

    public PatientController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    // POST /patients
    @PostMapping
    public ResponseEntity<Patient> addPatient(@RequestBody Patient patient) {
        Patient savedPatient = patientRepository.save(patient);
        return new ResponseEntity<>(savedPatient, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
        return patientRepository.findById(id)
                .map(patient -> new ResponseEntity<>(patient, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

   
    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(
            @PathVariable Long id,
            @RequestBody Patient updatedPatient) {

        return patientRepository.findById(id)
                .map(existingPatient -> {
                    existingPatient.setName(updatedPatient.getName());
                    existingPatient.setEmail(updatedPatient.getEmail());
                    existingPatient.setPhone(updatedPatient.getPhone());
                    existingPatient.setAddress(updatedPatient.getAddress());
                    existingPatient.setAge(updatedPatient.getAge());

                    patientRepository.save(existingPatient);
                    return new ResponseEntity<>(existingPatient, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}

