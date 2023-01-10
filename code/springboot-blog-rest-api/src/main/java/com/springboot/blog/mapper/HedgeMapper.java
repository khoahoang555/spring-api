package com.springboot.blog.mapper;

import com.smart.exchange.orderservice.domain.Hedge;
import com.smart.exchange.orderservice.service.dto.HedgeDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HedgeMapper implements EntityMapper<HedgeDTO, Hedge> {
    @Override
    public Hedge toEntity(HedgeDTO dto) {
        Hedge hedge = new Hedge();
        hedge.setId(dto.getId());
        hedge.setCurrencyPair(dto.getCurrencyPair());
        hedge.setBuyingAndSelling(dto.getBuyingAndSelling());
        hedge.setTransactionAmount(dto.getTransactionAmount());
        hedge.setPercentage(dto.getPercentage());
        hedge.setAmount(dto.getAmount());
        hedge.setHedgeName(dto.getHedgeOrderName());
        hedge.setStatus(dto.getStatus());
        hedge.setPeriod(dto.getPeriod());
        hedge.setStartDate(dto.getStartDate());
        hedge.setEndDate(dto.getEndDate());
        return hedge;
    }

    @Override
    public HedgeDTO toDto(Hedge entity) {
        HedgeDTO hedgeDTO = new HedgeDTO();
        hedgeDTO.setId(entity.getId());
        if (entity.getHedgeGroup() != null) hedgeDTO.setHedgeGroupId(entity.getHedgeGroup().getId());
        hedgeDTO.setCurrencyPair(entity.getCurrencyPair());
        hedgeDTO.setBuyingAndSelling(entity.getBuyingAndSelling());
        hedgeDTO.setTransactionAmount(entity.getTransactionAmount());
        hedgeDTO.setPercentage(entity.getPercentage());
        hedgeDTO.setAmount(entity.getAmount());
        hedgeDTO.setHedgeOrderName(entity.getHedgeName());
        hedgeDTO.setStatus(entity.getStatus());
        hedgeDTO.setPeriod(entity.getPeriod());
        hedgeDTO.setStartDate(entity.getStartDate());
        hedgeDTO.setEndDate(entity.getEndDate());
        return hedgeDTO;
    }

    @Override
    public List<Hedge> toEntity(List<HedgeDTO> dtoList) {
        return null;
    }

    @Override
    public List<HedgeDTO> toDto(List<Hedge> entityList) {
        return entityList.stream().map(hedge -> this.toDto(hedge)).collect(Collectors.toList());
    }

    @Override
    public void partialUpdate(Hedge entity, HedgeDTO dto) {

    }
}
