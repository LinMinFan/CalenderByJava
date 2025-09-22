package org.example.calendar.common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.example.calendar.common.vo.BaseResponse;
import org.example.calendar.spring.MyContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service("baseService")
public class BaseService {

    private static final Gson GSON = new GsonBuilder().serializeNulls().setDateFormat("yyyyMMddHHmmss").create();

    @Autowired
    private MyContext myContext;

    /**
     * 回傳 API結果
     *
     * @author jack
     * @return
     */
    public String executeAPIErrorResponse() {
        return genErrorResponse();
    }

    /**
     * 回傳 API結果
     *
     * @author jack
     * @param statusCode
     * @param message
     * @return
     */
    public String executeAPIErrorResponse(int statusCode, String message) {
        return genErrorResponse(statusCode, message);
    }

    /**
     * 產生錯誤response
     *
     * @author jack
     * @return
     */
    public String genErrorResponse() {
        int code = StatusMessage.N_999.getCode();
        String message = myContext.getMessage(StatusMessage.N_999.getMsg(), null);
        return genErrorResponse(code, message);
    }

    /**
     * 產生錯誤response, 錯誤訊息包含exception message
     *
     * @author jack
     * @param e
     * @return
     */
    public String genErrorResponse(Exception e) {
        int code = StatusMessage.N_999.getCode();
        String message = myContext.getMessage(StatusMessage.N_999.getMsg(), null);
        String stackTrace = ExceptionUtils.getStackTrace(e);

        return genErrorResponse(code, message + stackTrace);
    }

    /**
     * 產生未控制錯誤response
     *
     * @author jack
     * @param statusCode
     * @param message
     * @return
     */
    public String genErrorResponse(int statusCode, String message) {
        String response = "";
        try {
            BaseResponse obj = new BaseResponse();
            obj.setStatusCode(statusCode);
            obj.setMessage(message);

            response = GSON.toJson(obj);
        } catch (Exception e) {
            log.error("genErrorResponse error!", e);
        }

        return response;
    }
}
