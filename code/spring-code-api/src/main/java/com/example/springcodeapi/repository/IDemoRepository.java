package com.example.springcodeapi.repository;

import com.example.springcodeapi.domain.Demo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IDemoRepository extends JpaRepository<Demo, UUID> {
    @Query(value = "SELECT nextval('seq_number_id')", nativeQuery = true)
    Long getNextSeqId();
}
