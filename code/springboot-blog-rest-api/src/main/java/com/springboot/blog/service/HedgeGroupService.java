package com.smart.exchange.orderservice.service;

import com.smart.exchange.orderservice.domain.HedgeGroup;
import com.smart.exchange.orderservice.repository.IHedgeGroupRepository;
import com.smart.exchange.orderservice.service.dto.ErrorNotFound;
import com.smart.exchange.orderservice.service.dto.HedgeGroupDTO;
import com.smart.exchange.orderservice.service.dto.PagingHedgeGroupDTO;
import com.smart.exchange.orderservice.service.dto.SaveHedgeGroupDTO;
import com.smart.exchange.orderservice.service.errors.HedgeGroupNotFound;
import com.smart.exchange.orderservice.service.mapper.HedgeGroupMapper;
import com.smart.exchange.orderservice.service.mapper.SaveHedgeGroupMapper;
import com.smart.exchange.orderservice.service.validation.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class HedgeGroupService {
    private final Logger log = LoggerFactory.getLogger(HedgeGroupService.class);

    private final IHedgeGroupRepository hedgeGroupRepository;

    private final HedgeGroupMapper hedgeGroupMapper;
    private final SaveHedgeGroupMapper saveHedgeGroupMapper;

    public HedgeGroupService(
            IHedgeGroupRepository hedgeGroupRepository,
            HedgeGroupMapper hedgeGroupMapper,
            SaveHedgeGroupMapper saveHedgeGroupMapper
    ) {
        this.hedgeGroupRepository = hedgeGroupRepository;
        this.hedgeGroupMapper = hedgeGroupMapper;
        this.saveHedgeGroupMapper = saveHedgeGroupMapper;
    }

    public HedgeGroupDTO createHedgeGroup(SaveHedgeGroupDTO saveHedgeGroupDTO) {
        HedgeGroup hedgeGroup = saveHedgeGroupMapper.toEntity(saveHedgeGroupDTO);
        HedgeGroup hedgeGroupSaved = hedgeGroupRepository.save(hedgeGroup);
        return hedgeGroupMapper.toDto(hedgeGroupSaved);
    }

    public HedgeGroupDTO getOneHedgeGroupByName(String name) {
        HedgeGroupDTO hedgeGroupDTO = hedgeGroupMapper.toDto(hedgeGroupRepository.findOneByName(name));
        return hedgeGroupDTO;
    }

    public PagingHedgeGroupDTO getAllHedgeGroupsOrBySearch(int page, int size, String search) {
        int realPage = page - 1;
        Pageable pageable = PageRequest.of(realPage, size);

        Page<HedgeGroup> hedgeGroupPage = search != null ?
                hedgeGroupRepository.findByNameIsContainingIgnoreCase(search, pageable) :
                hedgeGroupRepository.findAll(pageable);

        PagingHedgeGroupDTO pagingHedgeGroupDTO = new PagingHedgeGroupDTO();

        pagingHedgeGroupDTO.setResults(hedgeGroupMapper.toDto(hedgeGroupPage.getContent()));
        pagingHedgeGroupDTO.setTotalPages(hedgeGroupPage.getTotalPages());
        pagingHedgeGroupDTO.setTotalItems(hedgeGroupPage.getTotalElements());
        pagingHedgeGroupDTO.setCurrentPage(page);

        return pagingHedgeGroupDTO;
    }

    public Optional<HedgeGroup> findHedgeGroupById(UUID id) {
        return hedgeGroupRepository.findById(id);
    }

    public HedgeGroupDTO updateHedgeGroup(UUID id, SaveHedgeGroupDTO saveHedgeGroupDTO) {
        HedgeGroup hedgeGroup = this.checkHedgeGroupExist(id);
        hedgeGroup.setName(saveHedgeGroupDTO.getName());
        HedgeGroup hedgeGroupSaved = hedgeGroupRepository.save(hedgeGroup);
        return hedgeGroupMapper.toDto(hedgeGroupSaved);
    }

    public void deleteHedgeGroup(UUID id) {
        HedgeGroup hedgeGroup = this.checkHedgeGroupExist(id);
        hedgeGroupRepository.delete(hedgeGroup);
    }

    public HedgeGroup checkHedgeGroupExist(UUID id) {
//        return hedgeGroupRepository.findById(id).orElseThrow(() -> {
//            new ResourceNotFoundException("HedgeGroup", "id", id.toString());
//        });
        return hedgeGroupRepository.findById(id).orElseThrow(() -> {
            ErrorNotFound notFoundId = new ErrorNotFound("HedgeGroup", "id", id.toString());
            ErrorNotFound notFoundName = new ErrorNotFound("HedgeGroup", "name", id.toString());
            List<ErrorNotFound> listErrorsMessage = new ArrayList<>();
            listErrorsMessage.add(notFoundId);
            listErrorsMessage.add(notFoundName);
            return new ResourceNotFoundException(listErrorsMessage);
        });
    }
}
