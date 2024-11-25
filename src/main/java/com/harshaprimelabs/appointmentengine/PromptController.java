package com.harshaprimelabs.appointmentengine;

import com.harshaprimelabs.appointmentengine.request.PromptRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200/**")
@RestController
public class PromptController {

    public static final String AI_API = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=AIzaSyASeGyNv_JiC_5hacAB6edXm77_9i4aqm8";

    @Autowired
    RestTemplate restTemplate;

    @PostMapping("/appoinment")
    public AiResponse makeAppointment(@RequestBody String query){
        PromptRequest request = new PromptRequest();
        PromptRequest.Content.Part part = new PromptRequest.Content.Part();
        part.setText(query);

        List<PromptRequest.Content.Part> parts = new ArrayList<>();
        parts.add(part);

        PromptRequest.Content content = new PromptRequest.Content();
        content.setParts(parts);

        ArrayList<PromptRequest.Content> contents = new ArrayList<>();
        contents.add(content);
        request.setContents(contents);

        request.getContents().get(0).getParts().get(0).setText(query);
        PromptResponse response =  restTemplate.postForObject(AI_API, request, PromptResponse.class);
       String result = response.candidates().get(0).content().parts().get(0).text();
        AiResponse aiResponse = new AiResponse();
        aiResponse.setResult(result);
        return aiResponse;
    }
}
