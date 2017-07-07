package com.brodma.web.security.dao;

import com.brodma.web.security.domain.entity.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

@Repository
public class LoginUsersDaoImpl implements LoginUsersDao {

    @Autowired
    @PersistenceContext(unitName = "logingUsersPU")
    private EntityManager entityManager;

    @Override
    public LoginUser findByUserName(String userName) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<LoginUser> cq = cb.createQuery(LoginUser.class);
        Root<LoginUser> root = cq.from(LoginUser.class);
        cq.select(root);

        ParameterExpression<String> userNameParam = cb.parameter(String.class, "userName");
        cq.where(cb.equal(root.get("userName"), userNameParam));
        TypedQuery<LoginUser> query = entityManager.createQuery(cq);
        query.setParameter(userNameParam, userName);
        return query.getSingleResult();
    }
}
