package com.springboot.blog.service.impl;

import com.smart.exchange.orderservice.constant.SystemConst;
import com.smart.exchange.orderservice.domain.HedgeGroup;
import com.smart.exchange.orderservice.repository.IHedgeGroupRepository;
import com.smart.exchange.orderservice.repository.IHedgeRepository;
import com.smart.exchange.orderservice.security.SecurityUtils;
import com.smart.exchange.orderservice.service.dto.*;
import com.smart.exchange.orderservice.service.mapper.HedgeGroupMapper;
import com.smart.exchange.orderservice.service.mapper.HedgeMapper;
import com.smart.exchange.orderservice.service.mapper.SaveHedgeGroupMapper;
import com.smart.exchange.sharedlibrary.error.rest.BadRequestException;
import com.smart.exchange.sharedlibrary.error.rest.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class HedgeGroupService {

    private final Logger log = LoggerFactory.getLogger(HedgeGroupService.class);

    @PersistenceContext
    private EntityManager entityManager;

    private final IHedgeGroupRepository hedgeGroupRepository;

    private final IHedgeRepository hedgeRepository;

    private final HedgeGroupMapper hedgeGroupMapper;

    private final SaveHedgeGroupMapper saveHedgeGroupMapper;

    private final HedgeMapper hedgeMapper;

    public HedgeGroupService(
            IHedgeGroupRepository hedgeGroupRepository,
            HedgeGroupMapper hedgeGroupMapper,
            SaveHedgeGroupMapper saveHedgeGroupMapper,
            HedgeMapper hedgeMapper,
            IHedgeRepository hedgeRepository
    ) {
        this.hedgeGroupRepository = hedgeGroupRepository;
        this.hedgeGroupMapper = hedgeGroupMapper;
        this.saveHedgeGroupMapper = saveHedgeGroupMapper;
        this.hedgeMapper = hedgeMapper;
        this.hedgeRepository = hedgeRepository;
    }

    public HedgeGroupDTO createHedgeGroup(SaveHedgeGroupDTO saveHedgeGroupDTO) {
        UUID organizationId = SecurityUtils.getUserDetail().getOrgId();
        UUID orgMemberId = SecurityUtils.getUserDetail().getOrgMemberId();

        Optional<HedgeGroup> hedgeGroupChecked = hedgeGroupRepository.findFirstByNameAndOrganizationId(saveHedgeGroupDTO.getName(), organizationId);

        if (hedgeGroupChecked.isPresent()) {
            throw new BadRequestException("name", "このグループは既に登録済みです。");
        }

        HedgeGroup hedgeGroup = saveHedgeGroupMapper.toEntity(saveHedgeGroupDTO);
        hedgeGroup.setOrgMemberId(orgMemberId);
        hedgeGroup.setOrganizationId(organizationId);
        HedgeGroup hedgeGroupSaved = hedgeGroupRepository.save(hedgeGroup);
        return hedgeGroupMapper.toDto(hedgeGroupSaved);
    }

//    public List<HedgeGroup> findHedgeGroups(HedgeGroupCriteriaDTO hedgeGroupCriteriaDTO) {
//        UUID organizationId = SecurityUtils.getUserDetail().getOrgId();
//        String search = hedgeGroupCriteriaDTO.getSearch();
//
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<HedgeGroup> criteriaQuery = criteriaBuilder.createQuery(HedgeGroup.class);
//        Root<HedgeGroup> hedgeGroupRoot = criteriaQuery.from(HedgeGroup.class);
//
//        List<Predicate> hedgeGroupPredicates = new ArrayList<>();
//
//        Predicate predicateOrganizationId = criteriaBuilder.equal(hedgeGroupRoot.get("organizationId"), organizationId);
//        Predicate predicateId = criteriaBuilder.equal(hedgeGroupRoot.get("id"), SystemConst.HEDGE_GROUP_UNASSIGNED_ID);
//        hedgeGroupPredicates.add(criteriaBuilder.or(predicateOrganizationId, predicateId));
//
//        if (search != null) hedgeGroupPredicates.add(criteriaBuilder.like(criteriaBuilder.upper(hedgeGroupRoot.get("name")), "%" + search.toUpperCase() + "%"));
//
//        criteriaQuery.where(hedgeGroupPredicates.toArray(new Predicate[hedgeGroupPredicates.size()]));
//
//        criteriaQuery.orderBy(criteriaBuilder.asc(hedgeGroupRoot.get("createdAt")));
//
//        TypedQuery<HedgeGroup> hedgeGroupTypedQuery = entityManager.createQuery(criteriaQuery);
//
//        List<HedgeGroup> hedgeGroups = hedgeGroupTypedQuery.getResultList();
//
//        return hedgeGroups;
//    }
//
//    public List<Hedge> findHedgeOrders(HedgeByGroupCriteriaDTO hedgeCommonCriteriaDTO) {
//        UUID organizationId = SecurityUtils.getUserDetail().getOrgId();
//        String search = hedgeCommonCriteriaDTO.getSearch();
//        String buyAndSell = hedgeCommonCriteriaDTO.getBuyOrSell();
//        boolean checkDone = hedgeCommonCriteriaDTO.isCheckDone();
//
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Hedge> hedgeCriteriaQuery = criteriaBuilder.createQuery(Hedge.class);
//        Root<Hedge> hedgeRoot = hedgeCriteriaQuery.from(Hedge.class);
//
//        List<Predicate> predicates = new ArrayList<>();
//
//        predicates.add(criteriaBuilder.equal(hedgeRoot.get("organizationId"), organizationId));
//
//        if (search != null) {
//            Expression<String> expression = criteriaBuilder.function("CONCAT_WS", String.class,
//                    criteriaBuilder.literal(" "), hedgeRoot.<String>get("hedgeName"), hedgeRoot.<String>get("transactionAmount"),
//                    hedgeRoot.<String>get("currencyPair"), hedgeRoot.<String>get("percentage"));
//            predicates.add(criteriaBuilder.like(expression, "%" + search +"%"));
//        }
//
//        if (buyAndSell.equals("外貨受取")) {
//            predicates.add(criteriaBuilder.like(hedgeRoot.get("buyingAndSelling"), "外貨受取"));
//        } else {
//            predicates.add(criteriaBuilder.like(hedgeRoot.get("buyingAndSelling"), "外貨支払"));
//        }
//
//        List<String> listStatus;
//
//        if (!checkDone) {
//            listStatus = Arrays.asList(new String[]{
//                    Choices.HEDGE_ORDER_STATUS.NOT_RUN.getKey(),
//                    Choices.HEDGE_ORDER_STATUS.RUNNING.getKey(),
//                    Choices.HEDGE_ORDER_STATUS.PLANNING.getKey()
//            });
//        } else {
//            listStatus = Arrays.asList(new String[]{
//                    Choices.HEDGE_ORDER_STATUS.DONE.getKey()
//            });
//        }
//
//        Expression inExpression = hedgeRoot.get("status").as(String.class);
//        predicates.add(inExpression.in(listStatus));
//
//        hedgeCriteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
//
//        hedgeCriteriaQuery.orderBy(criteriaBuilder.asc(hedgeRoot.get("createdAt")));
//
//        TypedQuery<Hedge> hedgeTypedQuery = entityManager.createQuery(hedgeCriteriaQuery);
//
//        List<Hedge> hedges = hedgeTypedQuery.getResultList();
//
//        return hedges;
//    }

    public List<HedgeGroup> findHedgeOrderAndHedgeGroup(HedgeByGroupCriteriaDTO hedgeByGroupCriteriaDTO) {
        UUID organizationId = SecurityUtils.getUserDetail().getOrgId();
        String search = hedgeByGroupCriteriaDTO.getSearch();
        String buyOrSell = hedgeByGroupCriteriaDTO.getBuyOrSell();
        boolean checkDone = hedgeByGroupCriteriaDTO.isCheckDone();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<HedgeGroup> criteriaQuery = criteriaBuilder.createQuery(HedgeGroup.class);
        Root<HedgeGroup> hedgeGroupRoot = criteriaQuery.from(HedgeGroup.class);

        List<Predicate> hedgeGroupPredicates = new ArrayList<>();

        Predicate predicateOrganizationId = criteriaBuilder.equal(hedgeGroupRoot.get("organizationId"), organizationId);
        Predicate predicateId = criteriaBuilder.equal(hedgeGroupRoot.get("id"), SystemConst.HEDGE_GROUP_UNASSIGNED_ID);
        hedgeGroupPredicates.add(criteriaBuilder.or(predicateOrganizationId, predicateId));

        if (search != null) {
            //hedgeGroupPredicates.add(criteriaBuilder.like(criteriaBuilder.upper(hedgeGroupRoot.get("name")), "%" + search.toUpperCase() + "%"));
            Predicate predicateHedgeGroupName = criteriaBuilder.like(criteriaBuilder.upper(hedgeGroupRoot.get("name")), "%" + search.toUpperCase() + "%");
            Predicate predicateHedgeOrderName = criteriaBuilder.like(criteriaBuilder.upper(hedgeGroupRoot.join("hedges").get("hedgeName")), "%" + search.toUpperCase() + "%");
            hedgeGroupPredicates.add(criteriaBuilder.or(predicateHedgeGroupName, predicateHedgeOrderName));
        }

        criteriaQuery.where(hedgeGroupPredicates.toArray(new Predicate[hedgeGroupPredicates.size()]));

        criteriaQuery.orderBy(criteriaBuilder.asc(hedgeGroupRoot.get("createdAt")));

        TypedQuery<HedgeGroup> hedgeGroupTypedQuery = entityManager.createQuery(criteriaQuery);

        List<HedgeGroup> hedgeGroups = hedgeGroupTypedQuery.getResultList();

        return hedgeGroups;
    }

    public HedgeGroupDTO updateHedgeGroup(UUID id, SaveHedgeGroupDTO saveHedgeGroupDTO) {
        UUID organizationId = SecurityUtils.getUserDetail().getOrgId();

        Optional<HedgeGroup> hedgeGroupChecked = hedgeGroupRepository.findFirstByNameAndOrganizationIdAndIdIsNot(saveHedgeGroupDTO.getName(), organizationId, id);

        if (hedgeGroupChecked.isPresent()) {
            throw new BadRequestException("name", "このグループは既に登録済みです。");
        }

        HedgeGroup hedgeGroup = this.getHedgeGroupOrThrow(id);

        if (hedgeGroup.getName() != saveHedgeGroupDTO.getName()) {
            hedgeGroup.setName(saveHedgeGroupDTO.getName());
            HedgeGroup hedgeGroupSaved = hedgeGroupRepository.save(hedgeGroup);
            return hedgeGroupMapper.toDto(hedgeGroupSaved);
        }
        return hedgeGroupMapper.toDto(hedgeGroup);
    }

    public void deleteHedgeGroup(UUID id) {
        HedgeGroup hedgeGroup = this.getHedgeGroupOrThrow(id);
        hedgeGroupRepository.delete(hedgeGroup);
    }

    public HedgeGroup getHedgeGroupOrThrow(UUID id) {
        return hedgeGroupRepository.findById(id).orElseThrow(() -> new NotFoundException("hedgeGroupId", "HedgeGroup not found!"));
    }

}
