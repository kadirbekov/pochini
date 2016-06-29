package kz.manasa.pochini.scope;

import kz.manasa.pochini.constants.ApplicationConstants;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by dkadirbekov on 29.06.2016.
 */
public class ApplicationScope {

    private static ApplicationScope instance;

    public static ApplicationContext ctx = new ClassPathXmlApplicationContext(ApplicationConstants.APPLICATION_CONTEXT_FILE);

    public static ApplicationScope getInstance(){
        if(instance == null){
            synchronized (ApplicationScope.class) {
                if(instance == null)
                    instance = new ApplicationScope();
            }
        }
        return instance;
    }

    private ApplicationScope(){
    }
}
