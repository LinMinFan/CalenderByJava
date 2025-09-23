package org.example.calendar.app.controller;

import org.example.calendar.app.service.CalenderService;
import org.example.calendar.app.vo.AddCalenderReq;
import org.example.calendar.app.vo.AddEventReq;
import org.example.calendar.app.vo.QueryCalendarReq;
import org.example.calendar.common.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/calendar")
public class CalenderController extends BaseController {

    @Autowired
    private CalenderService service;

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

    /**
     * 新增行事曆
     *
     * @author jack
     * @param req
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/addCalender")
    public ResponseEntity<String> addCalender(@RequestBody @Validated AddCalenderReq req) throws Exception {
        String response = service.executeCalender(req);
        return returnResponse(response);
    }

    /**
     * 新增事件
     *
     * @author jack
     * @param req
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/addEvent")
    public ResponseEntity<String> addEvent(@RequestBody @Validated AddEventReq req) throws Exception {
        String response = service.executeAddEvent(req);
        return returnResponse(response);
    }
}
