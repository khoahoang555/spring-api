package com.springboot.blog.repository;

import com.springboot.blog.entity.Hedge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IHedgeRepository extends JpaRepository<Hedge, UUID> {

}
