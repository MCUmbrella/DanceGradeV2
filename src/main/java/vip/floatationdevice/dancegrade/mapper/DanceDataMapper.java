package vip.floatationdevice.dancegrade.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import vip.floatationdevice.dancegrade.data.DanceData;

import java.util.List;

@Mapper
public interface DanceDataMapper
{
    int getDataCount();

    DanceData getData(int id);

    List<DanceData> getAllData(int offset);

    List<DanceData> searchData(
            @Param("offset") int offset,
            @Param("studentId") Integer studentId,
            @Param("name") String name
    );

    int searchDataCount(
            @Param("studentId") Integer studentId,
            @Param("name") String name
    );

    int hasData(int id);

    int insertData(DanceData d);

    int deleteData(int id);
}
