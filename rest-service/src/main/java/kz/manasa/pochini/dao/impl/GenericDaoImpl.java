package kz.manasa.pochini.dao.impl;

import kz.manasa.pochini.dao.GenericDao;
import kz.manasa.pochini.models.GenericEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by dkadirbekov on 30.06.2016.
 */
@Repository
public class GenericDaoImpl implements GenericDao {

    @PersistenceContext
    protected EntityManager em;

    @Transactional
    @Override
    public void save(Object object) {
        em.persist(object);
        em.flush();
    }

    @Transactional
    @Override
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

    @Transactional
    @Override
    public void delete(Object object) {
        object = em.merge(object);  //reattach the entity first
        em.remove(object);
        em.flush();
    }

    @Override
    public void delete(Long id, Class clazz) {
        em.remove(em.getReference(clazz, id));
        em.flush();
    }

}
