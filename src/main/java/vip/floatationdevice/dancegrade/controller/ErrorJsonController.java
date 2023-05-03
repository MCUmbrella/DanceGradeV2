package vip.floatationdevice.dancegrade.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.floatationdevice.dancegrade.service.ErrorJsonService;

@RestController
public class ErrorJsonController implements ErrorController
{
    @Autowired
    ErrorJsonService svc;

    @RequestMapping("/error")
    public ResponseEntity<?> actionError(HttpServletRequest req, HttpServletResponse resp)
    {
        return svc.actionError(req, resp);
    }
}
