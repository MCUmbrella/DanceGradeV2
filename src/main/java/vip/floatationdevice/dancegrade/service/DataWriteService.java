package vip.floatationdevice.dancegrade.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import vip.floatationdevice.dancegrade.data.DanceData;
import vip.floatationdevice.dancegrade.data.http.CommonMappedResult;
import vip.floatationdevice.dancegrade.exception.DataNotFoundException;
import vip.floatationdevice.dancegrade.exception.UnexpectedAffectedRowsException;
import vip.floatationdevice.dancegrade.mapper.DanceDataMapper;

@Service
public class DataWriteService
{
    @Autowired
    DanceDataMapper mapper;

    public ResponseEntity<?> insertData(DanceData d)
    {
        if(mapper.insertData(d) == 1)
            return new ResponseEntity<>(
                    new CommonMappedResult<>(
                            HttpStatus.CREATED.value(),
                            "Data created"
                    ),
                    HttpStatus.CREATED
            );
        else throw new UnexpectedAffectedRowsException();
    }

    public ResponseEntity<?> deleteData(int id)
    {
        if(mapper.hasData(id) != 1)
            throw new DataNotFoundException("Data with ID " + id + " not exists");

        if(mapper.deleteData(id) == 1)
            return new ResponseEntity<>(
                    new CommonMappedResult<>(
                            HttpStatus.OK.value(),
                            "Data deleted"
                    ),
                    HttpStatus.OK
            );
        else throw new UnexpectedAffectedRowsException();
    }
}
