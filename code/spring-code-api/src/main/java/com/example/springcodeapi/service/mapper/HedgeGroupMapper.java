package com.example.springcodeapi.service.mapper;

import com.example.springcodeapi.domain.HedgeGroup;
import com.example.springcodeapi.service.dto.HedgeGroupDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HedgeGroupMapper implements EntityMapper<HedgeGroupDTO, HedgeGroup>{

    @Override
    public HedgeGroup toEntity(HedgeGroupDTO dto) {
        HedgeGroup hedgeGroup = new HedgeGroup();
        hedgeGroup.setId(dto.getId());
        hedgeGroup.setName(dto.getName());
        return hedgeGroup;
    }

    @Override
    public HedgeGroupDTO toDto(HedgeGroup entity) {
        HedgeGroupDTO hedgeGroupDTO = new HedgeGroupDTO();
        hedgeGroupDTO.setName(entity.getName());
        hedgeGroupDTO.setId(entity.getId());
        return hedgeGroupDTO;
    }

    @Override
    public List<HedgeGroup> toEntity(List<HedgeGroupDTO> dtoList) {
        return null;
    }

    @Override
    public List<HedgeGroupDTO> toDto(List<HedgeGroup> entityList) {
        return null;
    }
}
