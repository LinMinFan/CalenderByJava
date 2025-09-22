package org.example.calendar.app.controller;

import org.example.calendar.app.vo.QueryCalendarReq;
import org.example.calendar.common.BaseController;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/calendar")
public class CalenderController extends BaseController {

    /**
     * 功能描述
     *
     * @author jack
     * @param req
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/queryYear")
    public ResponseEntity<String> queryCalendar(@RequestBody @Validated QueryCalendarReq req) throws Exception {
        return returnResponse("Calendar data Success " + req.getYear());
    }
}
