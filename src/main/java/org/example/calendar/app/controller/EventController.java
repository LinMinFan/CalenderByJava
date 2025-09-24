package org.example.calendar.app.controller;

import org.example.calendar.app.service.EventService;
import org.example.calendar.app.vo.AddEventReq;
import org.example.calendar.app.vo.DeleteEventReq;
import org.example.calendar.app.vo.EditEventReq;
import org.example.calendar.common.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/event")
public class EventController extends BaseController {

    @Autowired
    private EventService service;

    /**
     * 新增事件
     *
     * @author jack
     * @param req 請求物件
     * @return 成功訊息
     * @throws Exception 操作失敗時
     */
    @PostMapping(value = "/addEvent")
    public ResponseEntity<String> addEvent(@RequestBody @Validated AddEventReq req) throws Exception {
        String response = service.executeAddEvent(req);
        return returnResponse(response);
    }

    /**
     * 編輯事件
     *
     * @author jack
     * @param req 請求物件
     * @return 成功訊息
     * @throws Exception 操作失敗時
     */
    @PostMapping(value = "/editEvent")
    public ResponseEntity<String> editEvent(@RequestBody @Validated EditEventReq req) throws Exception {
        String response = service.executeEditEvent(req);
        return returnResponse(response);
    }

    /**
     * 刪除事件
     *
     * @author jack
     * @param req 請求物件
     * @return 成功訊息
     * @throws Exception 操作失敗時
     */
    @PostMapping(value = "/deleteEvent")
    public ResponseEntity<String> deleteEvent(@RequestBody @Validated DeleteEventReq req) throws Exception {
        String response = service.executeDeleteEvent(req);
        return returnResponse(response);
    }
}
