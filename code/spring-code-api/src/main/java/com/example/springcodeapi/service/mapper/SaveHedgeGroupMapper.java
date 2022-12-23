package com.example.springcodeapi.service.mapper;

import com.example.springcodeapi.domain.HedgeGroup;
import com.example.springcodeapi.service.dto.SaveHedgeGroupDTO;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SaveHedgeGroupMapper implements EntityMapper<SaveHedgeGroupDTO, HedgeGroup>{
    @Override
    public HedgeGroup toEntity(SaveHedgeGroupDTO dto) {
        HedgeGroup hedgeGroup = new HedgeGroup();
        hedgeGroup.setName(dto.getName());
        return hedgeGroup;
    }

    @Override
    public SaveHedgeGroupDTO toDto(HedgeGroup entity) {
        SaveHedgeGroupDTO saveHedgeGroupDTO = new SaveHedgeGroupDTO();
        saveHedgeGroupDTO.setName(entity.getName());
        return saveHedgeGroupDTO;
    }

    @Override
    public List<HedgeGroup> toListEntity(List<SaveHedgeGroupDTO> dtoList) {
        return null;
    }

    @Override
    public List<SaveHedgeGroupDTO> toListDto(List<HedgeGroup> entityList) {
        return null;
    }
}
