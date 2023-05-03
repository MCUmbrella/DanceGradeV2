package vip.floatationdevice.dancegrade.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import vip.floatationdevice.dancegrade.data.http.CommonMappedResult;
import vip.floatationdevice.dancegrade.data.http.SearchResult;
import vip.floatationdevice.dancegrade.exception.DataNotFoundException;
import vip.floatationdevice.dancegrade.mapper.DanceDataMapper;

@Service
public class DataReadService
{
    @Autowired
    DanceDataMapper mapper;

    public ResponseEntity<?> getDataCount()
    {
        return new ResponseEntity<>(
                new CommonMappedResult<>(
                        HttpStatus.OK.value(),
                        "OK",
                        mapper.getDataCount()
                ),
                HttpStatus.OK
        );
    }

    public ResponseEntity<?> getData(int id)
    {
        if(mapper.hasData(id) != 1)
            throw new DataNotFoundException("Data with ID " + id + " not exists");

        return new ResponseEntity<>(
                new CommonMappedResult<>(
                        HttpStatus.OK.value(),
                        "OK",
                        mapper.getData(id)
                ),
                HttpStatus.OK
        );
    }

    public ResponseEntity<?> getAllData(int page)
    {
        return new ResponseEntity<>(
                new CommonMappedResult<>(
                        HttpStatus.OK.value(),
                        "OK",
                        mapper.getAllData(page * 10)
                ),
                HttpStatus.OK
        );
    }

    public ResponseEntity<?> searchData(int page, Integer studentId, String name)
    {
        return new ResponseEntity<>(
                new CommonMappedResult<>(
                        HttpStatus.OK.value(),
                        "OK",
                        new SearchResult(
                                mapper.searchData(page * 10, studentId, name),
                                mapper.searchDataCount(studentId, name)
                        )
                ),
                HttpStatus.OK
        );
    }
}
