package com.examly.springapp.service;

import com.examly.springapp.model.Doctor;
import com.examly.springapp.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public Doctor addDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public Optional<Doctor> getDoctorById(Long doctorId) {
        return doctorRepository.findById(doctorId);
    }

    public Doctor update(Long doctorId, Doctor doctorDetails) {
        return doctorRepository.findById(doctorId)
                .map(doctor -> {
                    // Update only the fields required by the test cases
                    doctor.setName(doctorDetails.getName());
                    doctor.setSpecialization(doctorDetails.getSpecialization());
                    
                    // Update phone and email if provided
                    if (doctorDetails.getPhone() != null) {
                        doctor.setPhone(doctorDetails.getPhone());
                    }
                    if (doctorDetails.getEmail() != null) {
                        doctor.setEmail(doctorDetails.getEmail());
                    }
                    if (doctorDetails.getRoomNumber() != null) {
                        doctor.setRoomNumber(doctorDetails.getRoomNumber());
                    }
                    
                    return doctorRepository.save(doctor);
                })
                .orElseThrow(() -> new RuntimeException("Doctor not found with id " + doctorId));
    }
}
