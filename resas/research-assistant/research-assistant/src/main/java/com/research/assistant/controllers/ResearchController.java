package com.research.assistant.controllers;


import com.research.assistant.beans.ResearchRequestBean;
import com.research.assistant.service.ResearchService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/research")
@CrossOrigin("*")
@AllArgsConstructor



public class ResearchController {

    @Autowired
    ResearchService researchService;

    @PostMapping("/process")
    public ResponseEntity<String> processContent(@RequestBody ResearchRequestBean request){

        String result = researchService.processContent(request);

        return ResponseEntity.ok(result);

    }
}
