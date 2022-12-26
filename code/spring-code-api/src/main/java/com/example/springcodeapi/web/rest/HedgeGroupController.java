package com.example.springcodeapi.web.rest;

import com.example.springcodeapi.domain.HedgeGroup;
import com.example.springcodeapi.service.HedgeGroupService;
import com.example.springcodeapi.service.dto.HedgeGroupDTO;
import com.example.springcodeapi.service.dto.PagingHedgeGroupDTO;
import com.example.springcodeapi.service.dto.SaveHedgeGroupDTO;
import com.example.springcodeapi.service.errors.HedgeGroupNotFound;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("/hedge-groups")
public class HedgeGroupController {
    private final HedgeGroupService hedgeGroupService;

    public HedgeGroupController(HedgeGroupService hedgeGroupService) {
        this.hedgeGroupService = hedgeGroupService;
    }

    private static final Logger logger = LoggerFactory.getLogger(HedgeGroupController.class);
    @PostMapping
    public ResponseEntity<HedgeGroupDTO> createNewHedgeGroup(@Valid @RequestBody SaveHedgeGroupDTO saveHedgeGroupDTO) {
//        HedgeGroupDTO checkHedgeGroupName = hedgeGroupService.getOneHedgeGroupByName(saveHedgeGroupDTO.getName());
//        if (checkHedgeGroupName != null) {
//            throw new HedgeGroupNameExistException();
//        }

        return new ResponseEntity<>(hedgeGroupService.createHedgeGroup(saveHedgeGroupDTO), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<PagingHedgeGroupDTO> findAllHedgeGroupOrBySearch(
        @RequestParam(name = "page", defaultValue = "1", required = false) int page,
        @RequestParam(name = "size", defaultValue = "1000", required = false) int size,
        @RequestParam(name = "search", required = false) String search
    ) {
        PagingHedgeGroupDTO pagingHedgeGroupDTO = hedgeGroupService.getAllHedgeGroupsOrBySearch(page, size, search);

        if (pagingHedgeGroupDTO.getResults().isEmpty()) {
            throw new HedgeGroupNotFound();
        }

        return new ResponseEntity<>(pagingHedgeGroupDTO, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HedgeGroupDTO> updateHedgeGroup(@PathVariable("id") UUID id, @Valid @RequestBody SaveHedgeGroupDTO saveHedgeGroupDTO) {
        Optional<HedgeGroup> hedgeGroup = hedgeGroupService.findHedgeGroupById(id);
        if (!hedgeGroup.isPresent()) {
            throw new HedgeGroupNotFound();
        }

        return new ResponseEntity<>(hedgeGroupService.updateHedgeGroup(hedgeGroup.get(), saveHedgeGroupDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteHedgeGroup(@PathVariable("id") UUID id) {
        Optional<HedgeGroup> hedgeGroup = hedgeGroupService.findHedgeGroupById(id);
        if (!hedgeGroup.isPresent()) {
            throw new HedgeGroupNotFound();
        }
        hedgeGroupService.deleteHedgeGroup(hedgeGroup.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
