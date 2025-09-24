package org.example.calendar.app.service;

import org.example.calendar.app.vo.AddEventReq;
import org.example.calendar.app.vo.EditEventReq;
import org.example.calendar.exception.CustomAPIException;

public interface EventService {

    /**
     * 新增事件
     *
     * @author jack
     * @param req 請求物件
     * @return 成功訊息
     * @throws Exception 操作失敗時
     */
    public String executeAddEvent(AddEventReq req) throws CustomAPIException,Exception;

    /**
     * 更新事件
     *
     * @author jack
     * @param req 請求物件
     * @return 成功訊息
     * @throws Exception 操作失敗時
     */
    public String executeEditEvent(EditEventReq req) throws CustomAPIException,Exception;

    /**
     * 刪除事件
     *
     * @author jack
     * @param req 請求物件
     * @return 成功訊息
     * @throws Exception 操作失敗時
     */
//    public String executeDeleteEvent(DeleteEventReq req) throws CustomAPIException,Exception;

    /**
     * 查詢事件
     *
     * @author jack
     * @param req 請求物件
     * @return 成功訊息
     * @throws Exception 操作失敗時
     */
//    public String executeQueryEvent(QueryEventReq req) throws CustomAPIException,Exception;
}
