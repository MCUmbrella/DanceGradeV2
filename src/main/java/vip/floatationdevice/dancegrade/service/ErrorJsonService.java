package vip.floatationdevice.dancegrade.service;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import vip.floatationdevice.dancegrade.data.http.CommonMappedResult;

@Service
public class ErrorJsonService
{
    public ResponseEntity<?> actionError(HttpServletRequest req, HttpServletResponse resp)
    {
        return new ResponseEntity<>(
                new CommonMappedResult<>(
                        (int) req.getAttribute(RequestDispatcher.ERROR_STATUS_CODE),
                        (String) req.getAttribute(RequestDispatcher.ERROR_MESSAGE),
                        req.getAttribute(RequestDispatcher.ERROR_EXCEPTION)
                ),
                HttpStatusCode.valueOf((int) req.getAttribute(RequestDispatcher.ERROR_STATUS_CODE))
        );
    }
}
