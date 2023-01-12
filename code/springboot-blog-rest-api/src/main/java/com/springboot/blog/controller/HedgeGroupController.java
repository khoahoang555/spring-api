package com.springboot.blog.controller;

import com.springboot.blog.dto.HedgeGroupDTO;
import com.springboot.blog.entity.Hedge;
import com.springboot.blog.entity.HedgeGroup;
import com.springboot.blog.service.HedgeGroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.springboot.blog.dto.HedgeByGroupCriteriaDTO;

import java.util.List;

@RestController
@RequestMapping("/api/hedge-groups")
public class HedgeGroupController {

    private final HedgeGroupService hedgeGroupService;

    public HedgeGroupController(HedgeGroupService hedgeGroupService) {
        this.hedgeGroupService = hedgeGroupService;
    }

    private static final Logger logger = LoggerFactory.getLogger(HedgeGroupController.class);

    @GetMapping("/get-hedge-order-and-groups")
    public ResponseEntity<List<HedgeGroupDTO>> findHedgeOrderAndGroupByHedgeGroup(@ModelAttribute HedgeByGroupCriteriaDTO hedgeCommonCriteriaDTO) {
        return new ResponseEntity<>(hedgeGroupService.findHedgeOrderAndHedgeGroup(hedgeCommonCriteriaDTO), HttpStatus.OK);
    }

}

