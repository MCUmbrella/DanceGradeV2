package vip.floatationdevice.dancegrade;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import vip.floatationdevice.dancegrade.component.DataManager;

@SpringBootApplication
@Slf4j
public class DanceGrade
{
    @Autowired
    DataManager dataManager;

    private static ConfigurableApplicationContext context;

    public DanceGrade(ConfigurableApplicationContext context)
    {
        DanceGrade.context = context;
    }

    public static ConfigurableApplicationContext getContext(){return context;}

    public static void main(String[] args)
    {
        log.info("Starting up");
        SpringApplication.run(DanceGrade.class, args);
        log.info("Startup completed");
    }

    @PostConstruct
    public void onStartup()
    {
        dataManager.init();
    }

    @PreDestroy
    public void onShutdown()
    {
        log.info("Shutting down");
    }

    public static void shutdown(int code)
    {
        context.close();
        System.exit(code);
    }
}
