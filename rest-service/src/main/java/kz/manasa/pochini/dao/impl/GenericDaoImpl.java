package kz.manasa.pochini.dao.impl;

import kz.manasa.pochini.dao.GenericDao;
import kz.manasa.pochini.models.GenericEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Optional;

/**
 * Created by dkadirbekov on 30.06.2016.
 */
@Repository
public class GenericDaoImpl implements GenericDao {

    @PersistenceContext
    protected EntityManager em;

    @Override
    @Transactional
    public void save(Object object) {
        em.persist(object);
        em.flush();
    }

    @Override
    @Transactional
    public Object update(Object object) {
        object = em.merge(object);
        em.flush();
        return object;
    }

    @Override
    @Transactional
    public <ENTITY> ENTITY saveOrUpdate(ENTITY entity) {
        if (entity instanceof GenericEntity) {
            if (((GenericEntity) entity).getId() == null) {
                em.persist(entity);
                em.flush();
                return entity;
            }
        }

        entity = em.merge(entity);
        em.flush();
        return entity;
    }

    @Override
    @Transactional
    public void delete(Object object) {
        object = em.merge(object);  //reattach the entity first
        em.remove(object);
        em.flush();
    }

    @Override
    @Transactional
    public void delete(Long id, Class clazz) {
        em.remove(em.getReference(clazz, id));
        em.flush();
    }

    @Override
    @Transactional(readOnly = true)
    public <ENTITY> ENTITY findById(Class<ENTITY> clazz, Long id) {
        return em.find(clazz, id);
    }

    @Override
    @Transactional
    public <ENTITY> ENTITY findByCode(Class<ENTITY> clazz, String code) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(clazz);
        Root root = query.from(clazz);

        query.where(builder.equal(root.get("code").as(String.class), code));

        Optional first = em
                .createQuery(query.select(root))
                .setMaxResults(1)
                .getResultList().stream()
                .findFirst();

        if (first.isPresent()) {
            return (ENTITY) first.get();
        } else {
            return null;
        }
    }

}
