package com.springboot.blog.controller;

import com.smart.exchange.orderservice.domain.HedgeGroup;
import com.smart.exchange.orderservice.service.HedgeGroupService;
import com.smart.exchange.orderservice.service.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/hedge-groups")
public class HedgeGroupController {

    private final HedgeGroupService hedgeGroupService;

    public HedgeGroupController(HedgeGroupService hedgeGroupService) {
        this.hedgeGroupService = hedgeGroupService;
    }

    private static final Logger logger = LoggerFactory.getLogger(HedgeGroupController.class);

    @PostMapping
    public ResponseEntity<HedgeGroupDTO> createHedgeGroup(@Valid @RequestBody SaveHedgeGroupDTO saveHedgeGroupDTO) {
        return new ResponseEntity<>(hedgeGroupService.createHedgeGroup(saveHedgeGroupDTO), HttpStatus.CREATED);
    }

    @GetMapping("/get-hedge-order-and-groups")
    public ResponseEntity<List<HedgeGroup>> findHedgeOrderAndGroupByHedgeGroup(@Valid @ModelAttribute HedgeByGroupCriteriaDTO hedgeCommonCriteriaDTO) {
        return new ResponseEntity<>(hedgeGroupService.findHedgeOrderAndHedgeGroup(hedgeCommonCriteriaDTO), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<HedgeGroupDTO> updateHedgeGroup(@PathVariable("id") UUID id, @Valid @RequestBody SaveHedgeGroupDTO saveHedgeGroupDTO) {
        return new ResponseEntity<>(hedgeGroupService.updateHedgeGroup(id, saveHedgeGroupDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteHedgeGroup(@PathVariable("id") UUID id) {
        hedgeGroupService.deleteHedgeGroup(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

