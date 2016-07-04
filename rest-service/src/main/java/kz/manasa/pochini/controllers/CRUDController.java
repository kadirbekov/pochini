package kz.manasa.pochini.controllers;

import kz.manasa.pochini.dao.GenericDao;
import kz.manasa.pochini.models.Currency;
import kz.manasa.pochini.pojo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by dkadirbekov on 04.07.2016.
 */
@RestController
@RequestMapping("/{modelName:\\w+}")
public class CRUDController {

    @Autowired
    GenericDao genericDao;


}
