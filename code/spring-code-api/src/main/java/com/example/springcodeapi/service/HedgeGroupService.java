package com.example.springcodeapi.service;

import com.example.springcodeapi.domain.HedgeGroup;
import com.example.springcodeapi.repository.IHedgeGroupRepository;
import com.example.springcodeapi.service.dto.HedgeGroupDTO;
import com.example.springcodeapi.service.dto.SaveHedgeGroupDTO;
import com.example.springcodeapi.service.mapper.HedgeGroupMapper;
import com.example.springcodeapi.service.mapper.SaveHedgeGroupMapper;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HedgeGroupService {
    private final Logger log = LoggerFactory.getLogger(HedgeGroupService.class);

    private final IHedgeGroupRepository hedgeGroupRepository;

    private final HedgeGroupMapper hedgeGroupMapper;
    private final SaveHedgeGroupMapper saveHedgeGroupMapper;

    private final ModelMapper modelMapper;

    public HedgeGroupService(
            IHedgeGroupRepository hedgeGroupRepository,
            HedgeGroupMapper hedgeGroupMapper,
            SaveHedgeGroupMapper saveHedgeGroupMapper,
            ModelMapper modelMapper
    ) {
        this.hedgeGroupRepository = hedgeGroupRepository;
        this.hedgeGroupMapper = hedgeGroupMapper;
        this.saveHedgeGroupMapper = saveHedgeGroupMapper;
        this.modelMapper = modelMapper;
    }

    public HedgeGroupDTO createHedgeGroup(SaveHedgeGroupDTO saveHedgeGroupDTO) {
        //HedgeGroup hedgeGroup = saveHedgeGroupMapper.toEntity(saveHedgeGroupDTO);
        HedgeGroup hedgeGroup = modelMapper.map(saveHedgeGroupDTO, HedgeGroup.class);
        HedgeGroup hedgeGroupSaved = hedgeGroupRepository.save(hedgeGroup);
        return hedgeGroupMapper.toDto(hedgeGroupSaved);
    }
}
