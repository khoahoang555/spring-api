package com.springboot.blog.mapper;

import com.springboot.blog.dto.SaveHedgeGroupDTO;
import com.springboot.blog.entity.HedgeGroup;
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
    public List<HedgeGroup> toEntity(List<SaveHedgeGroupDTO> dtoList) {
        return null;
    }

    @Override
    public List<SaveHedgeGroupDTO> toDto(List<HedgeGroup> entityList) {
        return null;
    }

}
