package org.example.calendar.app.service;

import org.example.calendar.app.vo.AddCalenderReq;
import org.example.calendar.exception.CustomAPIException;

public interface CalenderService {

    /**
     * 新增行事曆
     *
     * @author jack
     * @param req
     * @return
     * @throws Exception
     */
    public String executeCalender(AddCalenderReq req) throws CustomAPIException,Exception;

}
