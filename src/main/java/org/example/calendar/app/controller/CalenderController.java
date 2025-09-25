package org.example.calendar.app.controller;

import org.example.calendar.app.service.CalenderService;
import org.example.calendar.app.vo.AddCalenderReq;
import org.example.calendar.app.vo.DeleteCalenderReq;
import org.example.calendar.app.vo.EditCalenderReq;
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
     * 新增行事曆
     *
     * @author jack
     * @param req 請求物件
     * @return 成功訊息
     * @throws Exception 操作失敗時
     */
    @PostMapping(value = "/addCalender")
    public ResponseEntity<String> addCalender(@RequestBody @Validated AddCalenderReq req) throws Exception {
        String response = service.executeAddCalender(req);
        return returnResponse(response);
    }

    /**
     * 編輯行事曆
     *
     * @author jack
     * @param req 請求物件
     * @return 成功訊息
     * @throws Exception 操作失敗時
     */
    @PostMapping(value = "/editCalender")
    public ResponseEntity<String> editCalender(@RequestBody @Validated EditCalenderReq req) throws Exception {
        String response = service.executeEditCalender(req);
        return returnResponse(response);
    }

    /**
     * 刪除行事曆
     *
     * @author jack
     * @param req 請求物件
     * @return 成功訊息
     * @throws Exception 操作失敗時
     */
    @PostMapping(value = "/deleteCalender")
    public ResponseEntity<String> deleteCalender(@RequestBody @Validated DeleteCalenderReq req) throws Exception {
        String response = service.executeDeleteCalender(req);
        return returnResponse(response);
    }

    /**
     * 查詢行事曆
     *
     * @author jack
     * @param req 請求物件
     * @return 成功訊息
     * @throws Exception 操作失敗時
     */
    @PostMapping(value = "/queryYear")
    public ResponseEntity<String> queryCalendar(@RequestBody @Validated QueryCalendarReq req) throws Exception {
        return returnResponse("Calendar data Success " + req.getYear());
    }
}
