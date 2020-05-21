package betcio.com.randomcustomers.config;

import betcio.com.randomcustomers.service.ServiceWriteToDB;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Configuration
@EnableScheduling
public class AppConfig{

    private final ServiceWriteToDB serviceWriteToDB;
    private ConfigurableApplicationContext context;

    @Autowired
    public AppConfig(ServiceWriteToDB serviceWriteToDB, ConfigurableApplicationContext context) {
        this.serviceWriteToDB = serviceWriteToDB;
        this.context = context;
    }

    @Bean
    public void startSendToDB(){
        System.out.println("Starting send data to DB");
        serviceWriteToDB.start();
    }

    @Scheduled(initialDelay = 600000, fixedRate = 5000)
    public void shutDown () {
        System.out.println("Closing service...");
        System.exit(SpringApplication.exit(context));
    }

}
