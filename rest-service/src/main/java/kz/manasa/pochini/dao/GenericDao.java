package kz.manasa.pochini.dao;

/**
 * Created by dkadirbekov on 30.06.2016.
 */
public interface GenericDao {

    void save(Object object);

    Object update(Object object);

    <ENTITY> ENTITY saveOrUpdate(ENTITY object);

    void delete(Object object);

    void delete(Long id, Class clazz);

}
