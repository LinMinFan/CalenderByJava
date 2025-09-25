package org.example.calendar.app.service;

import org.example.calendar.app.vo.AddCalenderReq;
import org.example.calendar.app.vo.DeleteCalenderReq;
import org.example.calendar.app.vo.EditCalenderReq;
import org.example.calendar.app.vo.QueryCalendarReq;
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
    public String executeAddCalender(AddCalenderReq req) throws CustomAPIException,Exception;

    /**
     * 編輯行事曆
     *
     * @author jack
     * @param req 請求物件
     * @return 成功訊息
     * @throws Exception 操作失敗時
     */
    public String executeEditCalender(EditCalenderReq req) throws CustomAPIException,Exception;

    /**
     * 刪除行事曆
     *
     * @author jack
     * @param req 請求物件
     * @return 成功訊息
     * @throws Exception 操作失敗時
     */
    public String executeDeleteCalender(DeleteCalenderReq req) throws CustomAPIException,Exception;

    /**
     * 查詢行事曆
     *
     * @author jack
     * @param req 請求物件
     * @return 成功訊息
     * @throws Exception 操作失敗時
     */
    public String executeQueryCalender(QueryCalendarReq req) throws CustomAPIException,Exception;
}
