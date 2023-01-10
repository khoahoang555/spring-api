package com.springboot.blog.repository;

import com.smart.exchange.orderservice.domain.HedgeGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IHedgeGroupRepository extends JpaRepository<HedgeGroup, UUID> {

    Optional<HedgeGroup> findFirstByNameAndOrganizationIdAndIdIsNot(String name, UUID organizationId, UUID hedgeGroupId);

    Optional<HedgeGroup> findFirstByNameAndOrganizationId(String name, UUID organizationId);

    List<HedgeGroup> findByIdOrOrganizationIdOrderByCreatedAtAsc(UUID groupUnassignedId, UUID organizationId);

}
