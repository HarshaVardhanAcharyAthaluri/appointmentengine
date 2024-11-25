package com.harshaprimelabs.appointmentengine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class AppointmentController {

    @Autowired
    AppointmentRepository repository;

@PostMapping("/shedule")
public AppointMentResponse sheduleAppointment(@RequestBody Appointment appointment){
    Appointment appointmentdeatails = repository.save(appointment);
    AppointMentResponse appointMentResponse = new AppointMentResponse();
    appointMentResponse.setCatogory(appointmentdeatails.getAppointmentCategory());
    appointMentResponse.setStatus("suuccess");
    appointMentResponse.setUsername(appointmentdeatails.getUsername());
    return appointMentResponse;
}

}
