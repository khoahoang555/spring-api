package com.example.springcodeapi.service.mapper;

import com.example.springcodeapi.domain.HedgeGroup;
import com.example.springcodeapi.service.dto.HedgeGroupDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<HedgeGroup> toListEntity(List<HedgeGroupDTO> dtoList) {
        return dtoList.stream().map(hedgeGroupDTO -> this.toEntity(hedgeGroupDTO)).collect(Collectors.toList());
    }

    @Override
    public List<HedgeGroupDTO> toListDto(List<HedgeGroup> entityList) {
        return entityList.stream().map(entity -> this.toDto(entity)).collect(Collectors.toList());
    }
}
