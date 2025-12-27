// package com.examly.springapp.controller;

// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;

// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RestController;


// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;
// import java.util.ArrayList;
// import java.util.List;



// @RestController
// @RequestMapping("/doctors")
// public class DoctorController {

//    @RequestMapping("/{id}")
//     public String getDoctor(@PathVariable int id) {
//         return "Doctor ID: " + id;
//     }
    
//     @PostMapping
//     public ResponseEntity<String> addDoctor(@RequestBody Object doctor) {
//         return new ResponseEntity<>("Doctor created", HttpStatus.CREATED);
//     }

   
//     @GetMapping
//     public ResponseEntity<List<Object>> getAllDoctors() {
//         List<Object> doctors = new ArrayList<>(); // Simulating an empty database/list
        
//         if (doctors.isEmpty()) {
//             return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//         }
        
//         return new ResponseEntity<>(doctors, HttpStatus.OK);
//     }

//     @PutMapping("/{id}") 
//         public String updateDoctor() 
//         { return "Doctor with ID " + " updated"; }
//     @DeleteMapping("/{id}") 
//          public String deleteDoctor()
//           { return "Doctor with ID " + " deleted"; }
    
// }
// package com.examly.springapp.controller;

// import com.examly.springapp.model.Doctor;
// import com.examly.springapp.repository.DoctorRepository;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/doctors")
// public class DoctorController {

//     private final DoctorRepository doctorRepository;

//     public DoctorController(DoctorRepository doctorRepository) {
//         this.doctorRepository = doctorRepository;
//     }

  
//     @PostMapping
//     public ResponseEntity<Doctor> addDoctor(@RequestBody Doctor doctor) {
//         Doctor savedDoctor = doctorRepository.save(doctor);
//         return new ResponseEntity<>(savedDoctor, HttpStatus.CREATED);
//     }

   
//     // @GetMapping
//     // public ResponseEntity<List<Doctor>> getAllDoctors() {
//     //     return ResponseEntity.ok(doctorRepository.findAll());
//     // }
//     @GetMapping
// public ResponseEntity<List<Doctor>> getAllDoctors() {

//     List<Doctor> doctors = doctorRepository.findAll();

//     if (doctors.isEmpty()) {
//         return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204
//     }

//     return new ResponseEntity<>(doctors, HttpStatus.OK); // 200
// }


   
//     @GetMapping("/{id}")
//     public ResponseEntity<Doctor> getDoctorById(@PathVariable Long id) {
//         return doctorRepository.findById(id)
//                 .map(ResponseEntity::ok)
//                 .orElse(ResponseEntity.notFound().build());
//     }

    
//     @PutMapping("/{id}")
//     public ResponseEntity<Doctor> updateDoctor(
//             @PathVariable Long id,
//             @RequestBody Doctor updatedDoctor) {

//         return doctorRepository.findById(id)
//                 .map(existingDoctor -> {
//                     existingDoctor.setName(updatedDoctor.getName());
//                     existingDoctor.setSpecialization(updatedDoctor.getSpecialization());
//                     doctorRepository.save(existingDoctor);
//                     return ResponseEntity.ok(existingDoctor);
//                 })
//                 .orElse(ResponseEntity.notFound().build());
//     }

//     @DeleteMapping
//     public void hello(){
//         //
//     }
// }


package com.examly.springapp.controller;

import com.examly.springapp.model.Doctor;
import com.examly.springapp.repository.DoctorRepository;
import com.examly.springapp.service.DoctorService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    
    private DoctorService doctorService;
    public DoctorController(DoctorService doctorService,DoctorRepository doctorRepository){
        this.doctorService=doctorService;
        this.doctorRepository=doctorRepository;
    }
    @PostMapping
    public ResponseEntity<Doctor> addDoctor(@RequestBody Doctor doctor) {
        
        if(doctor==null) return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
        Doctor savedDoctor = doctorService.addDoctor(doctor);
        return new ResponseEntity<>(savedDoctor,HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        List<Doctor> doctors = doctorService.getAllDoctors();

        if (doctors.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204
        }

        return new ResponseEntity<>(doctors, HttpStatus.OK); // 200
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable Long id) {

        Optional<Doctor> doctor = doctorService.getDoctorById(id);

        if (doctor.isPresent()) {
            return new ResponseEntity<>(doctor.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Doctor> updateDoctor(
            @PathVariable Long id,
            @RequestBody Doctor updatedDoctor) {

        Optional<Doctor> doctor = doctorService.getDoctorById(id);

        if (doctor.isPresent()) {
            Doctor existingDoctor = doctor.get();
            existingDoctor.setName(updatedDoctor.getName());
            existingDoctor.setSpecialization(updatedDoctor.getSpecialization());
            existingDoctor.setPhone(updatedDoctor.getPhone());
            existingDoctor.setEmail(updatedDoctor.getEmail());
            existingDoctor.setRoomNumber(updatedDoctor.getRoomNumber());
            Doctor saved = doctorService.addDoctor(existingDoctor);
            return new ResponseEntity<>(saved, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    
    }
    
    private DoctorRepository doctorRepository;
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id) {

        if (doctorRepository.existsById(id)) {
            doctorRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/page/{pageNumber}/{pageSize}")
public ResponseEntity<Page<Doctor>> getDoctorsWithPagination(
        @PathVariable int pageNumber,
        @PathVariable int pageSize) {

    Pageable pageable = PageRequest.of(pageNumber, pageSize);
    Page<Doctor> doctorPage = doctorRepository.findAll(pageable);

    return new ResponseEntity<>(doctorPage, HttpStatus.OK);
}

}
