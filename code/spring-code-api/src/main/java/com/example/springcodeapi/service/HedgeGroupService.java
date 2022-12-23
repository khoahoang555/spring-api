package com.example.springcodeapi.service;

import com.example.springcodeapi.domain.HedgeGroup;
import com.example.springcodeapi.repository.IHedgeGroupRepository;
import com.example.springcodeapi.service.dto.HedgeGroupDTO;
import com.example.springcodeapi.service.dto.PagingHedgeGroupDTO;
import com.example.springcodeapi.service.dto.SaveHedgeGroupDTO;
import com.example.springcodeapi.service.mapper.HedgeGroupMapper;
import com.example.springcodeapi.service.mapper.SaveHedgeGroupMapper;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

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
        HedgeGroup hedgeGroup = saveHedgeGroupMapper.toEntity(saveHedgeGroupDTO);
        //HedgeGroup hedgeGroup = modelMapper.map(saveHedgeGroupDTO, HedgeGroup.class);
        HedgeGroup hedgeGroupSaved = hedgeGroupRepository.save(hedgeGroup);
        return hedgeGroupMapper.toDto(hedgeGroupSaved);
    }

    public HedgeGroupDTO getOneHedgeGroupByName(String name) {
//        HedgeGroupDTO hedgeGroupDTO = modelMapper.map(hedgeGroupRepository.findOneByName(name), HedgeGroupDTO.class);
        HedgeGroupDTO hedgeGroupDTO = hedgeGroupMapper.toDto(hedgeGroupRepository.findOneByName(name));
        return hedgeGroupDTO;
    }

    public PagingHedgeGroupDTO getAllHedgeGroupsOrBySearch(int page, int size, String search) {
        int realPage = page - 1;
        Pageable pageable = PageRequest.of(realPage, size);

        Page<HedgeGroup> hedgeGroupPage = null;

        if (search != null) {
            hedgeGroupPage = hedgeGroupRepository.findByNameIsContainingIgnoreCase(search, pageable);
        } else {
            hedgeGroupPage = hedgeGroupRepository.findAll(pageable);
        }

        PagingHedgeGroupDTO pagingHedgeGroupDTO = new PagingHedgeGroupDTO();

        pagingHedgeGroupDTO.setResults(hedgeGroupMapper.toListDto(hedgeGroupPage.getContent()));
        pagingHedgeGroupDTO.setTotalPages(hedgeGroupPage.getTotalPages());
        pagingHedgeGroupDTO.setTotalItems(hedgeGroupPage.getTotalElements());
        pagingHedgeGroupDTO.setCurrentPage(page);

        return pagingHedgeGroupDTO;
    }

    public Optional<HedgeGroup> findHedgeGroupById(UUID id) {
        return hedgeGroupRepository.findById(id);
    }

    public HedgeGroupDTO updateHedgeGroup(HedgeGroup hedgeGroup, SaveHedgeGroupDTO saveHedgeGroupDTO) {
        hedgeGroup.setName(saveHedgeGroupDTO.getName());
        HedgeGroup hedgeGroupSaved = hedgeGroupRepository.save(hedgeGroup);
        return hedgeGroupMapper.toDto(hedgeGroupSaved);
    }

    public void deleteHedgeGroup(HedgeGroup hedgeGroup) {
        hedgeGroupRepository.delete(hedgeGroup);
    }
}
