package org.example.calendar.app.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.calendar.app.service.CalenderService;
import org.example.calendar.app.vo.AddCalenderReq;
import org.example.calendar.app.vo.AddEventReq;
import org.example.calendar.exception.CustomAPIException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional
@Service
public class CalenderServiceImpl implements CalenderService {

    /**
     * 新增行事曆
     *
     * @author jack
     * @param req
     * @return
     * @throws Exception
     */
    @Override
    public String executeCalender(AddCalenderReq req) throws CustomAPIException, Exception {
        String response = "";

        try {

        } catch (Exception e) {
            log.error("CalenderServiceImpl executeCalender Exception", e);
            throw e;
        }


        return response;
    }

    /**
     * 新增事件
     *
     * @author jack
     * @param req
     * @return
     * @throws Exception
     */
    @Override
    public String executeAddEvent(AddEventReq req) throws CustomAPIException, Exception {
        String response = "";

        try {

        } catch (Exception e) {
            log.error("CalenderServiceImpl executeAddEvent Exception", e);
            throw e;
        }


        return response;
    }
}
