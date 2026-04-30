package com.example.AI_summarizer.Service;

import com.example.AI_summarizer.Model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AISummarizerService {
    public Response summarizeText(String text) {
        String summary ="";
        List<String> actionItems = new ArrayList<>();
        return new Response(summary,actionItems);

    }
}
