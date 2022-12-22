package com.example.springcodeapi.service;

import com.example.springcodeapi.domain.HedgeGroup;
import com.example.springcodeapi.repository.IHedgeGroupRepository;
import com.example.springcodeapi.service.dto.HedgeGroupDTO;
import com.example.springcodeapi.service.dto.SaveHedgeGroupDTO;
import com.example.springcodeapi.service.mapper.HedgeGroupMapper;
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

    public HedgeGroupService(IHedgeGroupRepository hedgeGroupRepository, HedgeGroupMapper hedgeGroupMapper) {
        this.hedgeGroupRepository = hedgeGroupRepository;
        this.hedgeGroupMapper = hedgeGroupMapper;
    }

    public HedgeGroupDTO createHedgeGroup(SaveHedgeGroupDTO saveHedgeGroupDTO) {
        HedgeGroup hedgeGroup = new HedgeGroup();
        hedgeGroup.setName(saveHedgeGroupDTO.getName());
        HedgeGroup hedgeGroupSaved = hedgeGroupRepository.save(hedgeGroup);
        return hedgeGroupMapper.toDto(hedgeGroupSaved);
    }
}
