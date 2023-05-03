package vip.floatationdevice.dancegrade.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vip.floatationdevice.dancegrade.mapper.DanceDataMapper;

@Component
@Slf4j
public class DataManager
{
    @Autowired
    DanceDataMapper mapper;

    public void init()
    {
        log.info("Initializing DataManager");
        log.info("Found " + mapper.getDataCount() + " records in the database");
        log.info("Database connection OK");
        log.info("DataManager initialization completed");
    }
}
