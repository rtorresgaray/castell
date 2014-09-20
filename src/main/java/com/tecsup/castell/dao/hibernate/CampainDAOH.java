package com.tecsup.castell.dao.hibernate;

import com.tecsup.castell.dao.CampainDAO;
import com.tecsup.castell.model.Campain;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class CampainDAOH extends BaseHibernateDAO implements CampainDAO {

    @Override
    public List<Campain> all() {
        Criteria criteria = this.getSession().createCriteria(Campain.class);
        return criteria.list();
    }

    @Override
    public Campain find(Long id) {
        Criteria criteria = this.getSession().createCriteria(Campain.class);
        criteria.add(Restrictions.eq("id", id));
        return (Campain) criteria.uniqueResult();
    }

    @Override
    public void save(Campain t) {
        this.getSession().save(t);
    }

    @Override
    public void update(Campain t) {
        this.getSession().merge(t);
    }

    @Override
    public void delete(Campain t) {
        this.getSession().delete(t);
    }

}
