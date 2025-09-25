package org.example.calendar.app.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.calendar.app.service.CalenderService;
import org.example.calendar.app.vo.AddCalenderReq;
import org.example.calendar.app.vo.DeleteCalenderReq;
import org.example.calendar.app.vo.EditCalenderReq;
import org.example.calendar.app.vo.QueryCalendarReq;
import org.example.calendar.common.BaseService;
import org.example.calendar.common.StatusMessage;
import org.example.calendar.dao.mapper.CalendarMapper;
import org.example.calendar.dao.persistence.Calendar;
import org.example.calendar.dao.persistence.CalendarExample;
import org.example.calendar.exception.CustomAPIException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Transactional
@Service
public class CalenderServiceImpl extends BaseService implements CalenderService {

    @Autowired
    private CalendarMapper calendarMapper;

    /**
     * 新增行事曆
     *
     * @author jack
     * @param req 請求物件
     * @return 成功訊息
     * @throws Exception 操作失敗時
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String executeAddCalender(AddCalenderReq req) throws CustomAPIException, Exception {
        String response = "";

        try {
            // 1. 將 AddCalenderReq 轉成 Calendar 實體
            Calendar calendar = new Calendar();
            BeanUtils.copyProperties(req, calendar);

            // 2. 呼叫 mapper 新增，使用 insertSelective 只寫入非 null 欄位
            int rows = calendarMapper.insertSelective(calendar);

            if (rows != 1) {
                return executeAPIResponseForMap(null, StatusMessage.N_300, null);
            }

            response = executeAPIResponseForMap(null, StatusMessage.N_0, null);
        } catch (Exception e) {
            log.error("CalenderServiceImpl executeCalender Exception", e);
            throw e;
        }


        return response;
    }

    /**
     * 編輯行事曆
     *
     * @author jack
     * @param req 請求物件
     * @return 成功訊息
     * @throws Exception 操作失敗時
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String executeEditCalender(EditCalenderReq req) throws CustomAPIException,Exception {
        String response = "";

        try {

            // 1. 先檢查是否存在
            Calendar existing = calendarMapper.selectByPrimaryKey(req.getId());
            if (existing == null) {
                return executeAPIResponseForMap(null, StatusMessage.N_203, null);
            }

            // 2. 將 EditEventReq 轉換成 Event 實體
            Calendar calendar = new Calendar();
            BeanUtils.copyProperties(req, calendar);

            // 3. 執行更新 (只更新非 null 欄位)
            int rows = calendarMapper.updateByPrimaryKeySelective(calendar);

            if (rows != 1) {
                return executeAPIResponseForMap(null, StatusMessage.N_300, null);
            }

            response = executeAPIResponseForMap(null, StatusMessage.N_0, null);
        } catch (Exception e) {
            log.error("CalenderServiceImpl executeEditCalender Exception", e);
            throw e;
        }

        return response;
    }

    /**
     * 刪除行事曆
     *
     * @author jack
     * @param req 請求物件
     * @return 成功訊息
     * @throws Exception 操作失敗時
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String executeDeleteCalender(DeleteCalenderReq req) throws CustomAPIException,Exception {
        String response = "";

        try {

            // 1. 先檢查是否存在
            Calendar existing = calendarMapper.selectByPrimaryKey(req.getId());
            if (existing == null) {
                return executeAPIResponseForMap(null, StatusMessage.N_203, null);
            }

            // 2. 執行刪除
            int rows = calendarMapper.deleteByPrimaryKey(req.getId());
            if (rows != 1) {
                return executeAPIResponseForMap(null, StatusMessage.N_300, null);
            }

            response = executeAPIResponseForMap(null, StatusMessage.N_0, null);
        } catch (Exception e) {
            log.error("CalenderServiceImpl executeDeleteCalender Exception", e);
            throw e;
        }

        return response;
    }

    /**
     * 查詢行事曆
     *
     * @author jack
     * @param req 請求物件
     * @return 成功訊息
     * @throws Exception 操作失敗時
     */
    public String executeQueryCalender(QueryCalendarReq req) throws CustomAPIException,Exception {
        String response = "";

        try {

            // 1. 根據 userId 查詢行事曆
            CalendarExample example = new CalendarExample();
            CalendarExample.Criteria criteria = example.createCriteria();
            criteria.andUserIdEqualTo(req.getUserId());

            long totalCnt = calendarMapper.countByExample(example);

            if (totalCnt == 0) {
                return executeAPIResponseForMap(null, StatusMessage.N_90, null);
            }

            List<Calendar> calendars = calendarMapper.selectByExample(example);

            Map<String, Object> resMap = new HashMap<>();

            resMap.put("totalCnt", totalCnt);
            resMap.put("calendars", calendars);

            response = executeAPIResponseForMap(resMap, StatusMessage.N_0, null);
        } catch (Exception e) {
            log.error("CalenderServiceImpl executeQueryCalender Exception", e);
            throw e;
        }

        return response;
    }
}
