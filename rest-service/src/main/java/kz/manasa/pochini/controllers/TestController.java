package kz.manasa.pochini.controllers;

import kz.manasa.pochini.dao.GenericDao;
import kz.manasa.pochini.models.Currency;
import kz.manasa.pochini.pojo.Response;
import kz.manasa.pochini.scope.ApplicationScope;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by dkadirbekov on 29.06.2016.
 */
@RestController
@RequestMapping("/test")
public class TestController {

    private static final Logger LOG = Logger.getLogger(TestController.class.getName());

    ApplicationScope scope;

    @Autowired
    GenericDao genericDao;

    @RequestMapping(value = "/hello")
    public String archiveAndSendToAWS() {
        return "hello";
    }

    @PostConstruct
    public void prepare() {
//        scope = ApplicationScope.getInstance();
//        genericDao = ApplicationScope.ctx.getBean(GenericDao.class);
    }

    @RequestMapping(value = "currencies")
    public Response getCurrencies() {
        List<Currency> currencies = genericDao.findAll(Currency.class);
        return new Response(currencies);
    }

    @RequestMapping(value = "currencies/{code:\\w+}")
    public Response getCurrencyByCode(@PathVariable String code) {
        Currency currency = genericDao.findByCode(Currency.class, code);
        return new Response(currency);
    }

}
