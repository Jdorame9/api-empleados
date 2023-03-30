package com.taller.apiempleados.repositories;
import java.util.List;

import org.springframework.stereotype.Component;
import com.taller.apiempleados.entities.EmpleadoEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;

@Component
public class EmpleadoPersistenceContext {
    
    @PersistenceContext
    private EntityManager entityManager;

    public EmpleadoEntity findEmpleado(int idempleado) {
        return entityManager.find(EmpleadoEntity.class, idempleado);
    }

    public List<EmpleadoEntity> findEmpleados() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<EmpleadoEntity> criteriaQuery = criteriaBuilder.createQuery(EmpleadoEntity.class);
        Root<EmpleadoEntity> from = criteriaQuery.from(EmpleadoEntity.class);

        criteriaQuery.select(from);

        TypedQuery<EmpleadoEntity> type = entityManager.createQuery(criteriaQuery);

        return type.getResultList();
    }
    
    @Transactional
    public EmpleadoEntity insertEmpleado(EmpleadoEntity empleadoEntity) {
        entityManager.persist(empleadoEntity);
        return empleadoEntity;
    }

    @Transactional
    public void deleteEmpleado(EmpleadoEntity empleadoEntity) {
        entityManager.remove(empleadoEntity);
    }

    @Transactional
    public void updateEmpleado(EmpleadoEntity empleadoEntity) {
        entityManager.merge(empleadoEntity);
    }
}
