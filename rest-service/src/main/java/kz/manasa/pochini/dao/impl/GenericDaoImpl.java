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
import javax.persistence.metamodel.EntityType;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Created by dkadirbekov on 30.06.2016.
 */
@Repository
public class GenericDaoImpl implements GenericDao {

    @PersistenceContext
    protected EntityManager entityManager;

    @Override
    @Transactional
    public void save(Object object) {
        entityManager.persist(object);
        entityManager.flush();
    }

    @Override
    @Transactional
    public Object update(Object object) {
        object = entityManager.merge(object);
        entityManager.flush();
        return object;
    }

    @Override
    @Transactional
    public <ENTITY> ENTITY saveOrUpdate(ENTITY entity) {
        if (entity instanceof GenericEntity) {
            if (((GenericEntity) entity).getId() == null) {
                entityManager.persist(entity);
                entityManager.flush();
                return entity;
            }
        }

        entity = entityManager.merge(entity);
        entityManager.flush();
        return entity;
    }

    @Override
    @Transactional
    public void delete(Object object) {
        object = entityManager.merge(object);  //reattach the entity first
        entityManager.remove(object);
        entityManager.flush();
    }

    @Override
    @Transactional
    public <ENTITY> void delete(Class<ENTITY> clazz, UUID id) {
        entityManager.remove(entityManager.getReference(clazz, id));
        entityManager.flush();
    }

    @Override
    @Transactional(readOnly = true)
    public <ENTITY> ENTITY findById(Class<ENTITY> clazz, UUID id) {
        return entityManager.find(clazz, id);
    }

    @Override
    @Transactional
    public <ENTITY> ENTITY findByCode(Class<ENTITY> clazz, String code) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(clazz);
        Root root = query.from(clazz);

        query.where(builder.equal(root.get("code").as(String.class), code));

        Optional first = entityManager
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

    @Override
    @Transactional
    public <ENTITY> List<ENTITY> findAll(Class<ENTITY> clazz) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(clazz);
        Root root = query.from(clazz);

        List resultList = entityManager
                .createQuery(query.select(root))
                .getResultList();
        return resultList;
    }

    @Override
    public Class<?> getClass(String className) {
        className = className.toLowerCase();
        for (EntityType<?> entityType : entityManager.getMetamodel().getEntities()) {
            String entityClassName = entityType.getName();
            if (entityClassName.toLowerCase().equals(className)) {
                return entityType.getJavaType();
            }
        }
        throw new IllegalArgumentException("wrongClassName");
    }

}
