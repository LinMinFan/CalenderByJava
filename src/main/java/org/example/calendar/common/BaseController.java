package org.example.calendar.common;

import org.apache.commons.lang3.StringUtils;
import org.example.calendar.exception.CustomAPIException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;


public class BaseController {

    @Autowired
    @Qualifier("baseService")
    private BaseService service;

    /**
     * API return
     *
     * @author jack
     * @param result json format String
     * @return
     */
    public ResponseEntity<String> returnResponse(String result) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        return new ResponseEntity<String>(result, responseHeaders, HttpStatus.OK);
    }

    /**
     * exception處理及新增API log
     *
     * @author jack
     * @param e
     * @return
     */
    public String handleExceptionLog(Exception e) {
        String response = "";
        CustomAPIException ce = null;

        if (e instanceof CustomAPIException) {
            ce = (CustomAPIException) e;
        } else {
            Throwable cause = e.getCause();

            while (cause != null) {
                if (cause instanceof CustomAPIException) {
                    ce = (CustomAPIException) cause;
                    break;
                } else {
                    cause = cause.getCause();
                }
            }
        }

        if (ce != null) {
            response = service.executeAPIErrorResponse(ce.getStatusCode(), ce.getMessage());
        } else {
            response = service.executeAPIErrorResponse();
        }

        return response;
    }

    /**
     * 驗證request
     *
     * @author jack
     * @param bindingResult
     * @throws CustomAPIException
     */
    protected void validateRequest(BindingResult bindingResult) throws CustomAPIException {
        if (bindingResult.hasFieldErrors()) {
            List<String> fieldErrorList = bindingResult.getFieldErrors().stream()
                    .map(error -> error.getField() + " " + error.getDefaultMessage()).collect(Collectors.toList());
            String msg = StringUtils.join(fieldErrorList, ", ");

            throw new CustomAPIException(msg, StatusMessage.N_300.getCode());
        }
    }
}
