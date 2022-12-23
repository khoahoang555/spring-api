package com.example.springcodeapi.repository;

import com.example.springcodeapi.domain.HedgeGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IHedgeGroupRepository extends JpaRepository<HedgeGroup, UUID> {
    HedgeGroup findOneByNameContaining(String name);
}
