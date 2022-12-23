package com.example.springcodeapi.service.mapper;

import java.util.List;

public interface EntityMapper<D, E> {
    E toEntity(D dto);

    D toDto(E entity);

    List<E> toListEntity(List<D> dtoList);

    List<D> toListDto(List<E> entityList);

}