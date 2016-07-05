package kz.manasa.pochini.dao;

import java.util.List;
import java.util.UUID;

/**
 * Created by dkadirbekov on 30.06.2016.
 */
public interface GenericDao {

    void save(Object object);

    Object update(Object object);

    <ENTITY> ENTITY saveOrUpdate(ENTITY object);

    void delete(Object object);

    <ENTITY> void delete(Class<ENTITY> clazz , UUID id);

    <ENTITY> ENTITY findById(Class<ENTITY> clazz, UUID id);

    <ENTITY> ENTITY findByCode(Class<ENTITY> clazz, String code);

    <ENTITY> List<ENTITY> findAll(Class<ENTITY> clazz);

    <ENTITY> Class<?> getClass(String className);

}
