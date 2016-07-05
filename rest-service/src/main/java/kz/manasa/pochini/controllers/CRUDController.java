package kz.manasa.pochini.controllers;

import kz.manasa.pochini.dao.GenericDao;
import kz.manasa.pochini.mapper.EntityMapper;
import kz.manasa.pochini.models.GenericEntity;
import kz.manasa.pochini.pojo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * Created by dkadirbekov on 04.07.2016.
 */
@RestController
@RequestMapping(value = "/{modelName:\\w+}")
public class CRUDController {

    @Autowired
    GenericDao genericDao;

    @Autowired
    EntityMapper entityMapper;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Response getById(@PathVariable String modelName,
                            @PathVariable UUID id) {
        Class<?> modelClass = genericDao.getClass(modelName);
        GenericEntity obj = (GenericEntity) genericDao.findById(modelClass, id);
        return new Response(obj).success();
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Response getAll(@PathVariable String modelName) {
        Class<?> modelClass = genericDao.getClass(modelName);
        List<?> objects = genericDao.findAll(modelClass);
        return new Response(objects);
    }

    @RequestMapping(value = "/code/{code:\\w+}", method = RequestMethod.GET)
    public Response getByCode(@PathVariable String modelName,
                              @PathVariable String code) {
        Class<?> modelClass = genericDao.getClass(modelName);
        GenericEntity obj = (GenericEntity) genericDao.findByCode(modelClass, code);
        return new Response(obj);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Response create(@PathVariable String modelName,
                           @RequestBody String body) throws IOException {
        Class<?> modelClass = genericDao.getClass(modelName);
        GenericEntity entity = (GenericEntity) entityMapper.readValue(body, modelClass);

        //TODO: validate

        genericDao.save(entity);
        return new Response(entity).success();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Response update(@PathVariable String modelName,
                           @PathVariable UUID id,
                           @RequestBody String body) throws IOException {
        Class<?> modelClass = genericDao.getClass(modelName);
        GenericEntity entity = (GenericEntity) entityMapper.readValue(body, modelClass);
        entity.setId(id);

        //TODO: validate

        genericDao.update(entity);
        return new Response(entity).success();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Response delete(@PathVariable String modelName,
                           @PathVariable UUID id) {
        Class<?> modelClass = genericDao.getClass(modelName);
        genericDao.delete(modelClass, id);

        return new Response(true).success();
    }

}
