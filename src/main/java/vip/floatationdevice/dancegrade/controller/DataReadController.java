package vip.floatationdevice.dancegrade.controller;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vip.floatationdevice.dancegrade.service.DataReadService;

@RestController
@Validated
public class DataReadController
{
    @Autowired
    DataReadService svc;

    @GetMapping("/api/dataCount")
    public ResponseEntity<?> getDataCount()
    {
        return svc.getDataCount();
    }

    @GetMapping("/api/data/{id}")
    public ResponseEntity<?> getData(@PathVariable("id") @PositiveOrZero(message = "'id' must be >= 0") Integer id)
    {
        return svc.getData(id);
    }

    @GetMapping("/api/data")
    public ResponseEntity<?> getAllData(
            @RequestParam("page") @PositiveOrZero(message = "'page' must be >= 0") int page,
            @RequestParam(value = "studentId", required = false) @Positive(message = "'studentId' must be positive") Integer studentId,
            @RequestParam(value = "name", required = false) String name
    )
    {
        if(studentId == null && name == null)
            return svc.getAllData(page);
        else
            return svc.searchData(page, studentId, name);
    }
}
