package com.example.AI_summarizer.Controller;

import com.example.AI_summarizer.Model.Request;
import com.example.AI_summarizer.Model.Response;
import com.example.AI_summarizer.Service.AISummarizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AISummarizerController {
    @Autowired
    private AISummarizerService service;
    @PostMapping("/summarize")
    public ResponseEntity<Response> summarize(@RequestBody Request request)
    {
        Response response = service.summarizeText(request.getText());
        return ResponseEntity.ok(response);
    }
}
