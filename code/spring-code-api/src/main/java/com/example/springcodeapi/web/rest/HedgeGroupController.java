package com.example.springcodeapi.web.rest;

import com.example.springcodeapi.service.HedgeGroupService;
import com.example.springcodeapi.service.dto.HedgeGroupDTO;
import com.example.springcodeapi.service.dto.SaveHedgeGroupDTO;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("hedge-group")
public class HedgeGroupController {
    private final HedgeGroupService hedgeGroupService;

    public HedgeGroupController(HedgeGroupService hedgeGroupService) {
        this.hedgeGroupService = hedgeGroupService;
    }

    private static final Logger logger = LoggerFactory.getLogger(HedgeGroupController.class);
    @PostMapping
    public ResponseEntity<HedgeGroupDTO> createNewHedgeGroup(@Valid @RequestBody SaveHedgeGroupDTO saveHedgeGroupDTO) {
        return new ResponseEntity<HedgeGroupDTO>(hedgeGroupService.createHedgeGroup(saveHedgeGroupDTO), HttpStatus.OK);
    }
}
