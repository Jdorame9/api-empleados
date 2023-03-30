package com.taller.apiempleados.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.taller.apiempleados.entities.EmpleadoEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

@Repository
public class EmpleadoRepository {

    @Autowired EntityManager entityManager;

    public EmpleadoEntity obtenerEmpleado(int idempleado) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<EmpleadoEntity> criteriaQuery = criteriaBuilder.createQuery(EmpleadoEntity.class);
        Root<EmpleadoEntity> from = criteriaQuery.from(EmpleadoEntity.class);

        criteriaQuery.select(from);
        criteriaQuery.where(criteriaBuilder.equal(from.get("idempleado"), idempleado));

        TypedQuery<EmpleadoEntity> type = entityManager.createQuery(criteriaQuery);

        return type.getSingleResult();
    }

    public List<EmpleadoEntity> obtenerEmpleados() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<EmpleadoEntity> criteriaQuery = criteriaBuilder.createQuery(EmpleadoEntity.class);
        Root<EmpleadoEntity> from = criteriaQuery.from(EmpleadoEntity.class);

        criteriaQuery.select(from);

        TypedQuery<EmpleadoEntity> type = entityManager.createQuery(criteriaQuery);

        return type.getResultList();
    }
}
