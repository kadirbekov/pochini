package kz.manasa.pochini.controller;

import kz.manasa.pochini.scope.ApplicationScope;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

/**
 * Created by dkadirbekov on 29.06.2016.
 */
@RestController
@RequestMapping("/test")
public class TestController {

    private static final Logger LOG = Logger.getLogger(TestController.class.getName());

    ApplicationScope scope;

    @RequestMapping(value = "/hello")
    public String archiveAndSendToAWS() {
        return "hello";
    }

    @PostConstruct
    public void prepare() {
        scope = ApplicationScope.getInstance();
    }

}
