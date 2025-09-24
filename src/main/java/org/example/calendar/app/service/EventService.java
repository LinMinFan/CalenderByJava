package org.example.calendar.app.service;

import org.example.calendar.app.vo.AddEventReq;
import org.example.calendar.exception.CustomAPIException;

public interface EventService {

    /**
     * 新增事件
     *
     * @author jack
     * @param req
     * @return
     * @throws Exception
     */
    public String executeAddEvent(AddEventReq req) throws CustomAPIException,Exception;

    /**
     * 更新事件
     *
     * @author jack
     * @param req
     * @return
     * @throws Exception
     */
//    public String executeEditEvent(EditEventReq req) throws CustomAPIException,Exception;

    /**
     * 刪除事件
     *
     * @author jack
     * @param req
     * @return
     * @throws Exception
     */
//    public String executeDeleteEvent(DeleteEventReq req) throws CustomAPIException,Exception;

    /**
     * 查詢事件
     *
     * @author jack
     * @param req
     * @return
     * @throws Exception
     */
//    public String executeQueryEvent(QueryEventReq req) throws CustomAPIException,Exception;
}
