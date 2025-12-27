package com.examly.springapp.service;

import com.examly.springapp.model.Appointment;
import com.examly.springapp.model.Doctor;
import com.examly.springapp.model.Patient;
import com.examly.springapp.repository.AppointmentRepository;
import com.examly.springapp.repository.DoctorRepository;
import com.examly.springapp.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    
    @Autowired
    private AppointmentRepository appointmentRepository;
    
    @Autowired
    private PatientRepository patientRepository;
    
    @Autowired
    private DoctorRepository doctorRepository;
    
    // @Override
    // public Appointment saveAppointment(Appointment appointment) {
    //     // Fetch and set the patient entity if only ID is provided
    //     if (appointment.getPatientId() != null) {
    //         Long patientId = appointment.getPatientId().getPatientId();
    //         if (patientId != null) {
    //             Patient patient = patientRepository.findById(patientId).orElse(null);
    //             appointment.setPatientId(patient);
    //         }
    //     }
        
    //     // Fetch and set the doctor entity if only ID is provided
    //     if (appointment.getDoctorId() != null) {
    //         Long doctorId = appointment.getDoctorId().getDoctorId();
    //         if (doctorId != null) {
    //             Doctor doctor = doctorRepository.findById(doctorId).orElse(null);
    //             appointment.setDoctorId(doctor);
    //         }
    //     }
        
    //     return appointmentRepository.save(appointment);
    // }

    @Override
public Appointment saveAppointment(Appointment appointment) {

    if (appointment.getPatient() != null &&
        appointment.getPatient().getPatientId() != null) {

        appointment.setPatient(
            patientRepository
                .findById(appointment.getPatient().getPatientId())
                .orElse(null)
        );
    }

    if (appointment.getDoctor() != null &&
        appointment.getDoctor().getDoctorId() != null) {

        appointment.setDoctor(
            doctorRepository
                .findById(appointment.getDoctor().getDoctorId())
                .orElse(null)
        );
    }

    return appointmentRepository.save(appointment);
}

    
    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }
    
    @Override
    public Appointment getAppointmentById(Long id) {
        return appointmentRepository.findById(id).orElse(null);
    }
    
    // @Override
    // public Appointment updateAppointment(Long id, Appointment appointment) {
    //     Appointment existing = appointmentRepository.findById(id).orElse(null);
    //     if (existing != null) {
    //         existing.setAppointmentTime(appointment.getAppointmentTime());
    //         existing.setStatus(appointment.getStatus());
    //         existing.setNotes(appointment.getNotes());
            
    //         // Update patient if provided
    //         if (appointment.getPatientId() != null) {
    //             Long patientId = appointment.getPatientId().getPatientId();
    //             if (patientId != null) {
    //                 Patient patient = patientRepository.findById(patientId).orElse(null);
    //                 existing.setPatientId(patient);
    //             }
    //         }
            
    //         // Update doctor if provided
    //         if (appointment.getDoctorId() != null) {
    //             Long doctorId = appointment.getDoctorId().getDoctorId();
    //             if (doctorId != null) {
    //                 Doctor doctor = doctorRepository.findById(doctorId).orElse(null);
    //                 existing.setDoctorId(doctor);
    //             }
    //         }
            
    //         return appointmentRepository.save(existing);
    //     }
    //     return null;
    // }

    @Override
public Appointment updateAppointment(Long id, Appointment appointment) {

    Appointment existing = appointmentRepository.findById(id).orElse(null);

    if (existing != null) {
        existing.setAppointmentTime(appointment.getAppointmentTime());
        existing.setStatus(appointment.getStatus());
        existing.setNotes(appointment.getNotes());

        if (appointment.getPatient() != null &&
            appointment.getPatient().getPatientId() != null) {

            existing.setPatient(
                patientRepository
                    .findById(appointment.getPatient().getPatientId())
                    .orElse(null)
            );
        }

        if (appointment.getDoctor() != null &&
            appointment.getDoctor().getDoctorId() != null) {

            existing.setDoctor(
                doctorRepository
                    .findById(appointment.getDoctor().getDoctorId())
                    .orElse(null)
            );
        }

        return appointmentRepository.save(existing);
    }

    return null;
}

    
    @Override
    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }
    
    @Override
    public List<Appointment> getAppointmentsByStatus(String status) {
        return appointmentRepository.findByStatus(status);
    }
}
