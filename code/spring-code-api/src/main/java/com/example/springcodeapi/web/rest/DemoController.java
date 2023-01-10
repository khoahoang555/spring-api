package com.example.springcodeapi.web.rest;

import com.example.springcodeapi.domain.Demo;
import com.example.springcodeapi.service.DemoService;
import com.example.springcodeapi.service.dto.DemoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/demos")
public class DemoController {
    private DemoService demoService;

    public DemoController(DemoService demoService) {
        this.demoService = demoService;
    }

    @PostMapping
    public ResponseEntity<Demo> createDemo(@RequestBody DemoDTO demoDTO) {
        return new ResponseEntity<>(demoService.createDemo(demoDTO), HttpStatus.OK);
    }
}
