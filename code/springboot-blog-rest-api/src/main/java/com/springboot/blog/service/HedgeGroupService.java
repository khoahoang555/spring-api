package com.springboot.blog.service;

import com.springboot.blog.constants.Choices;
import com.springboot.blog.constants.SystemConst;
import com.springboot.blog.dto.HedgeByGroupCriteriaDTO;
import com.springboot.blog.dto.HedgeDTO;
import com.springboot.blog.dto.HedgeGroupDTO;
import com.springboot.blog.entity.Hedge;
import com.springboot.blog.entity.HedgeGroup;
import com.springboot.blog.mapper.HedgeGroupMapper;
import com.springboot.blog.mapper.HedgeMapper;
import com.springboot.blog.mapper.SaveHedgeGroupMapper;
import com.springboot.blog.repository.IHedgeGroupRepository;
import com.springboot.blog.repository.IHedgeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


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

    public List<HedgeGroupDTO> findHedgeOrderAndHedgeGroup(HedgeByGroupCriteriaDTO hedgeByGroupCriteriaDTO) {

        UUID organizationId = UUID.fromString("8d9ef960-90f9-11ed-a1eb-0242ac120002");
        String search = Optional.ofNullable(hedgeByGroupCriteriaDTO.getSearch()).orElse("");
        Choices.BILLING_SELLING buyOrSell = hedgeByGroupCriteriaDTO.getBuyOrSell();
        List<String> status = hedgeByGroupCriteriaDTO.getStatus();

        EntityGraph entityGraph = entityManager.createEntityGraph(HedgeGroup.class);
        entityGraph.addSubgraph("hedges");

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<HedgeGroup> criteriaQuery = criteriaBuilder.createQuery(HedgeGroup.class);

        Metamodel metamodel = entityManager.getMetamodel();
        EntityType<HedgeGroup> hedgeGroupEntityType = metamodel.entity(HedgeGroup.class);

        Root<HedgeGroup> hedgeGroupRoot = criteriaQuery.from(HedgeGroup.class);
        Join<HedgeGroup, Hedge> hedgeJoin = hedgeGroupRoot.join(hedgeGroupEntityType.getSet("hedges", Hedge.class), JoinType.LEFT);
        List<Predicate> hedgeJoinPredicates = new ArrayList<>();
        List<Predicate> hedgeGroupPredicates = new ArrayList<>();

        Expression statusExpression = hedgeJoin.get("status").as(String.class);

        hedgeJoinPredicates.add(criteriaBuilder.equal(hedgeJoin.get("organizationId"), organizationId));
        hedgeJoinPredicates.add(criteriaBuilder.like(hedgeJoin.get("buyingAndSelling").as(String.class), buyOrSell.getKey()));
        hedgeJoinPredicates.add(statusExpression.in(status));

        hedgeJoin.on(hedgeJoinPredicates.toArray(new Predicate[hedgeJoinPredicates.size()]));

        Predicate predicateHedgeGroupOrganizationId = criteriaBuilder.equal(hedgeGroupRoot.get("organizationId"), organizationId);
        Predicate predicateHedgeGroupId = criteriaBuilder.equal(hedgeGroupRoot.get("id"), SystemConst.HEDGE_GROUP_UNASSIGNED_ID);

        hedgeGroupPredicates.add(criteriaBuilder.or(predicateHedgeGroupId, predicateHedgeGroupOrganizationId));

        if (!search.isBlank()) {
            Predicate predicateHedgeGroupName = criteriaBuilder.like(criteriaBuilder.upper(hedgeGroupRoot.get("name")), "%" + search.toUpperCase() + "%");
            Expression<String> expression = criteriaBuilder.function("CONCAT_WS", String.class,
                    criteriaBuilder.literal(" "), hedgeJoin.<String>get("hedgeName"),
                    hedgeJoin.<String>get("transactionAmount"), hedgeJoin.<String>get("percentage"));

            Predicate predicateHedgeOrderName = criteriaBuilder.like(criteriaBuilder.upper(expression), "%" + search.toUpperCase() + "%");
            hedgeGroupPredicates.add(criteriaBuilder.or(predicateHedgeGroupName, predicateHedgeOrderName));
        }

        criteriaQuery.where(hedgeGroupPredicates.toArray(new Predicate[hedgeGroupPredicates.size()]));

        List<Order> orderList = new ArrayList<>();

        orderList.add(criteriaBuilder.asc(hedgeGroupRoot.get("createdAt")));
        orderList.add(criteriaBuilder.asc(hedgeJoin.get("createdAt")));

        //Order order1 = criteriaBuilder.asc(hedgeGroupRoot.get("createdAt"));

        //orderList.add(criteriaBuilder.asc(hedgeGroupRoot.get("createdAt")));

        //Path<String> createdAt = hedgeJoin.get("createdAt");

        //orderList.add( criteriaBuilder.asc(hedgeJoin.get("createdAt")));

        criteriaQuery.orderBy(
                orderList
        );

        //criteriaQuery.or

//        criteriaQuery.orderBy(
//                criteriaBuilder.asc(hedgeJoin.get("createdAt"))
//        );

        TypedQuery<HedgeGroup> hedgeGroupTypedQuery = entityManager.createQuery(criteriaQuery);
        hedgeGroupTypedQuery.setHint("javax.persistence.loadgraph", entityGraph);

        //Pageable pageable = PageRequest.of(0, 1000, Sort.by(orderList));

        List<HedgeGroup> hedgeGroups = hedgeGroupTypedQuery.getResultList();

        //hedgeGroupTypedQuery.setFirstResult(Math.toIntExact(pageable.getOffset()));


        List<HedgeGroupDTO> hedgeGroupDTOS = hedgeGroups.stream().map(hedgeGroup -> {
            HedgeGroupDTO hedgeGroupDTO = hedgeGroupMapper.toDto(hedgeGroup);

            if (hedgeGroup.getHedges().isEmpty()) return hedgeGroupDTO;

            List<Hedge> hedges = hedgeGroup.getHedges().stream().collect(Collectors.toList());
            List<HedgeDTO> hedgeDTOS = new ArrayList<>(hedgeMapper.toDto(hedges));
            hedgeGroupDTO.setHedges(hedgeDTOS);

            return hedgeGroupDTO;
        }).collect(Collectors.toList());

        return hedgeGroupDTOS;
    }

}
