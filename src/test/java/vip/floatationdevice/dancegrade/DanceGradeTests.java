package vip.floatationdevice.dancegrade;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vip.floatationdevice.dancegrade.mapper.DanceDataMapper;

import static vip.floatationdevice.dancegrade.DanceGradeDataGenerator.*;

@SpringBootTest
@Slf4j
class DanceGradeTests
{
    @Autowired
    DanceDataMapper mapper;

    @Test
    void contextLoads()
    {
        //generateData();
    }

    void generateData()
    {
        initialize();
        for(int i = 0; i != getDataCount(); i++)
            mapper.insertData(generateDanceData());
        log.info("OK: generated " + getStudentCount() + " students, " + getDataCount() + " data");
    }
}
