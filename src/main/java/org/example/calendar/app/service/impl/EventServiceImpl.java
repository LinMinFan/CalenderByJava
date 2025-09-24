package org.example.calendar.app.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.calendar.app.service.EventService;
import org.example.calendar.app.vo.AddEventReq;
import org.example.calendar.app.vo.EditEventReq;
import org.example.calendar.common.BaseService;
import org.example.calendar.common.StatusMessage;
import org.example.calendar.dao.mapper.EventMapper;
import org.example.calendar.dao.persistence.Event;
import org.example.calendar.exception.CustomAPIException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional
@Service
public class EventServiceImpl extends BaseService implements EventService {

    @Autowired
    private EventMapper eventMapper;

    /**
     * 新增事件
     *
     * @author jack
     * @param req
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String executeAddEvent(AddEventReq req) throws CustomAPIException, Exception {
        String response = "";

        try {

            // 1. 將 AddEventReq 轉成 MyBatis-Generator 的 Event 實體
            Event event = new Event();
            BeanUtils.copyProperties(req, event);

            // 2. 呼叫 mapper 新增，使用 insertSelective 只寫入非 null 欄位
            int rows = eventMapper.insertSelective(event);

            if (rows != 1) {
                return executeAPIResponseForMap(null, StatusMessage.N_300, null);
            }

            response = executeAPIResponseForMap(null, StatusMessage.N_0, null);
        } catch (Exception e) {
            log.error("CalenderServiceImpl executeAddEvent Exception", e);
            throw e;
        }

        return response;
    }

    /**
     * 更新事件
     *
     * @author jack
     * @param req
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String executeEditEvent(EditEventReq req) throws CustomAPIException,Exception {
        String response = "";

        try {

            // 1. 先檢查事件是否存在
            Event existing = eventMapper.selectByPrimaryKey(req.getId());
            if (existing == null) {
                return executeAPIResponseForMap(null, StatusMessage.N_203, null);
            }

            // 2. 將 EditEventReq 轉換成 Event 實體
            Event event = new Event();
            BeanUtils.copyProperties(req, event);

            // 3. 執行更新 (只更新非 null 欄位)
            int rows = eventMapper.updateByPrimaryKeySelective(event);

            if (rows != 1) {
                return executeAPIResponseForMap(null, StatusMessage.N_300, null);
            }

            response = executeAPIResponseForMap(null, StatusMessage.N_0, null);
        } catch (Exception e) {
            log.error("CalenderServiceImpl executeEditEvent Exception", e);
            throw e;
        }

        return response;
    }
}
