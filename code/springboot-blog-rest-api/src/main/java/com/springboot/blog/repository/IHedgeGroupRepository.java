package com.springboot.blog.repository;

import com.springboot.blog.entity.HedgeGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IHedgeGroupRepository extends JpaRepository<HedgeGroup, UUID> {

}
