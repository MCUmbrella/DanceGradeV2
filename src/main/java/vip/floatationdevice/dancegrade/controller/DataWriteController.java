package vip.floatationdevice.dancegrade.controller;

import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vip.floatationdevice.dancegrade.data.DanceData;
import vip.floatationdevice.dancegrade.service.DataWriteService;

@RestController
public class DataWriteController
{
    @Autowired
    DataWriteService svc;

    @PostMapping("/api/data")
    public ResponseEntity<?> insertData(@RequestBody @Validated DanceData d)
    {
        return svc.insertData(d);
    }

    @DeleteMapping("/api/data/{id}")
    public ResponseEntity<?> deleteData(@PathVariable("id") @Positive int id)
    {
        return svc.deleteData(id);
    }
}
