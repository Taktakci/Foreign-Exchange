package com.taktakci.exchange.repository;

import com.taktakci.exchange.entity.Conversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Repository
@Transactional
public class ConversionRepository {

    @Autowired
    EntityManager entityManager;

    private static final int PAGE_SIZE = 10;

    public Conversion findById(Long transactionId) {
        return entityManager.find(Conversion.class, transactionId);
    }

    public List<Conversion> findAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Conversion> criteriaQuery = criteriaBuilder.createQuery(Conversion.class);
        Root<Conversion> rootEntry = criteriaQuery.from(Conversion.class);
        CriteriaQuery<Conversion> all = criteriaQuery.select(rootEntry);
        TypedQuery<Conversion> allQuery = entityManager.createQuery(all);
        return allQuery.getResultList();
    }

    public void deleteById(Long transactionId) {
        Conversion entityToDelete = findById(transactionId);
        if (entityToDelete != null) {
            entityManager.remove(entityToDelete);
        }
    }

    public void save(Conversion entity) {

        if (entity.getTransactionId() == null) {
            entityManager.persist(entity);
        } else {
            entityManager.merge(entity);
        }

    }

    public List<Conversion> findByTransactionDate(String transactionDate, Integer page) {
        LocalDate localDate = stringToLocalDate(transactionDate);
        int pageNumber = page == null ? 1 : page;
        return entityManager.createQuery("Select c from Conversion c where c.transactionDate = :transactionDate", Conversion.class)
                .setFirstResult((pageNumber-1) * PAGE_SIZE)
                .setMaxResults(PAGE_SIZE)
                .setParameter("transactionDate", localDate)
                .getResultList();
    }

    private LocalDate stringToLocalDate(String transactionDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(transactionDate, formatter);
    }
}
