package org.example.calendar.common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.example.calendar.common.vo.BaseResponse;
import org.example.calendar.spring.MyContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.beanutils.PropertyUtils;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service("baseService")
public class BaseService {

    private static final Gson GSON = new GsonBuilder().serializeNulls().setDateFormat("yyyyMMddHHmmss").create();

    @Autowired
    private MyContext myContext;

    /**
     * 設定API 回傳 statusCode, message值,並回傳 json 格式字串
     *
     * @author Joey
     * @param responseJson API response json Object
     * @param status       API response status code
     * @param message      API message 置換值(code:80,150)
     * @return json 格式字串
     */
    public String generateAPIStatusMessage(JsonObject responseJson, StatusMessage status, String message) {
        if (responseJson == null) {
            responseJson = new JsonObject();
        }

        String apiMsg = genMessage(status, message);

        responseJson.addProperty(HciConstants.API_STATUS_CODE, status.getCode());
        responseJson.addProperty(HciConstants.API_MESSAGE, apiMsg);

        return responseJson.toString();
    }

    /**
     * 新增API log並回傳 API結果
     *
     * @author Joey
     * @param jsonObj API response json Object
     * @param status       API response status code
     * @param message      API message 置換值(code:80,150)
     * @return json 格式回傳字串
     */
    public String executeAPIResponse(JsonObject jsonObj, StatusMessage status, String message) {
        String response = generateAPIStatusMessage(jsonObj, status, message);
        return response;
    }

    /**
     * 傳入java bean 設定API 回傳 statusCode, message,並回傳 json 格式字串
     *
     * @author Joey
     * @param obj     API response java bean
     * @param status  API response status code
     * @param message API message 置換值(code:80,150)
     * @return json 格式字串
     * @throws Exception
     */
    public String generateAPIReturnMessage(Object obj, StatusMessage status, String message) throws Exception {

        if (obj == null) {
            obj = new BaseResponse();
        }

        String apiMsg = genMessage(status, message);

        PropertyUtils.setProperty(obj, HciConstants.API_STATUS_CODE, status.getCode());
        PropertyUtils.setProperty(obj, HciConstants.API_MESSAGE, apiMsg);

        String jsonString = GSON.toJson(obj);
        return jsonString;
    }

    /**
     * 傳入java bean 設定API 回傳 statusCode, message,並回傳 json 格式字串
     *
     * @author Joey
     * @param map     API response java bean
     * @param status  API response status code
     * @param message API message 置換值(code:80,150)
     * @return json 格式字串
     * @throws Exception
     */
    public String generateAPIReturnMessage(Map<String, Object> map, StatusMessage status, String message) throws Exception {

        if (map == null) {
            map = new HashMap<String, Object>();
        }

        String apiMsg = genMessage(status, message);

        map.put(HciConstants.API_STATUS_CODE, status.getCode());
        map.put(HciConstants.API_MESSAGE, apiMsg);

        String jsonString = GSON.toJson(map);
        return jsonString;
    }

    /**
     * 取得多語後 message.properties 設定值
     *
     * @param status
     * @param message
     * @return
     * @author Joey
     */
    public String genMessage(StatusMessage status, String message) {
        String[] args = null;
        if (StringUtils.isNotEmpty(message)) {
            args = new String[] { message };
        }
        String apiMsg = myContext.getMessage(status.getMsg(), args);

        return apiMsg;
    }

    /**
     * 回傳 API結果
     *
     * @author Joey
     * @return
     */
    public String executeAPIErrorResponse() {
        return genErrorResponse();
    }

    /**
     * 回傳 API結果
     *
     * @author Joey
     * @param statusCode
     * @param message
     * @return
     */
    public String executeAPIErrorResponse(int statusCode, String message) {
        return genErrorResponse(statusCode, message);
    }

    /**
     * 回傳 API結果
     *
     * @author James
     * @param map     API response java bean
     * @param status  API response status code
     * @param message API message 置換值(code:80,150)
     * @return
     * @throws Exception
     */
    public String executeAPIResponseForMap(Map<String, Object> map, StatusMessage status, String message)
            throws Exception {

        String response = generateAPIReturnMessage(map, status, message);
        return response;
    }

    /**
     * 產生錯誤response
     *
     * @author Joey
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
     * @param e
     * @return
     * @author Joey
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
     * @author Joey
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
