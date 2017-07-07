package com.brodma.web.security.dao;

import com.brodma.web.security.domain.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class RolesDaoImpl implements RolesDao {

    @Autowired
    @PersistenceContext(unitName = "logingUsersPU")
    private EntityManager entityManager;

    @Override
    public List<Role> findAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Role> cq = cb.createQuery(Role.class);
        Root<Role> roles = cq.from(Role.class);
        cq.select(roles);
        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public List<Role> findByUserId(Long userId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Role> cq = cb.createQuery(Role.class);
        Root<Role> roles = cq.from(Role.class);
        cq.select(roles);

        ParameterExpression<Long> userIdParam = cb.parameter(Long.class, "userId");
        cq.where(cb.equal(roles.get("id"), userIdParam));

        TypedQuery<Role> query = entityManager.createQuery(cq);
        query.setParameter(userIdParam, userId);
        return query.getResultList();
    }
}
