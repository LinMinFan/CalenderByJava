package org.example.calendar.spring.interceptor;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.example.calendar.common.StatusMessage;
import org.example.calendar.common.vo.BaseResponse;
import org.example.calendar.exception.CustomAPIException;
import org.example.calendar.spring.MyContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionAdvice {

    @Autowired
    private MyContext myContext;

    /**
     * 上傳檔案超過限制大小回應訊息處理
     * @param e
     * @return
     * @author jack
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<BaseResponse> handleMaxSizeException(MaxUploadSizeExceededException e) {
        String errMsg = ExceptionUtils.getStackTrace(e);
        log.error("MaxUploadSizeExceede exception: {}", errMsg);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        StatusMessage n94 = StatusMessage.N_94;
        BaseResponse obj = new BaseResponse();
        obj.setStatusCode(n94.getCode());
        obj.setMessage(myContext.getMessage(n94.getMsg()));

        return new ResponseEntity<>(obj, responseHeaders, HttpStatus.OK);
    }

    /**
     * 處理 form data request 檢核失敗拋出的異常
     *
     * @param e
     * @return
     * @author jack
     */
    @ExceptionHandler(BindException.class)
    public ResponseEntity<BaseResponse> bindExceptionHandler(BindException e) {

        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        List<String> fieldErrorList = fieldErrors.stream().map(error -> error.getField() + " " + error.getDefaultMessage())
                .collect(Collectors.toList());
        String msg = StringUtils.join(fieldErrorList, ", ");

        return validationErrorResponse(msg);
    }

    /**
     * 處理 json request 檢核失敗拋出的異常
     * @param e
     * @return
     * @author jack
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResponse> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {

        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        List<String> fieldErrorList = fieldErrors.stream().map(error -> error.getField() + " " + error.getDefaultMessage())
                .collect(Collectors.toList());
        String msg = StringUtils.join(fieldErrorList, ", ");

        return validationErrorResponse(msg);
    }

    /**
     * 處理單一參數檢核失敗拋出的異常
     * @param e
     * @return
     * @author jack
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<BaseResponse> constraintViolationExceptionHandler(ConstraintViolationException e) {

        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        List<String> fieldErrorList = constraintViolations.stream().map(o -> o.getMessage()).collect(Collectors.toList());

        String msg = StringUtils.join(fieldErrorList, ", ");
        return validationErrorResponse(msg);
    }

    /**
     * CustomAPIException訊息處理
     * @param e
     * @return
     * @author jack
     */
    @ExceptionHandler(CustomAPIException.class)
    public ResponseEntity<BaseResponse> customAPIExceptionHandler(CustomAPIException e) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        BaseResponse obj = new BaseResponse();
        obj.setStatusCode(e.getStatusCode());
        obj.setMessage(e.getMessage());

        return new ResponseEntity<>(obj, responseHeaders, HttpStatus.OK);
    }

    /**
     * 程式發生未預期錯誤回應訊息處理
     * @param e
     * @return
     * @author jack
     */
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<BaseResponse> exceptionHandler(Throwable e) {
        String errMsg = ExceptionUtils.getStackTrace(e);
        log.error("unhandled exception: {}", errMsg);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        StatusMessage n999 = StatusMessage.N_999;
        BaseResponse obj = new BaseResponse();
        obj.setStatusCode(n999.getCode());
        obj.setMessage(myContext.getMessage(n999.getMsg()));

        return new ResponseEntity<>(obj, responseHeaders, HttpStatus.OK);
    }

    /**
     * @param msg
     * @return
     * @author jack
     */
    private ResponseEntity<BaseResponse> validationErrorResponse(String msg) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        StatusMessage sm = StatusMessage.N_300;
        BaseResponse obj = new BaseResponse();
        obj.setStatusCode(sm.getCode());
        obj.setMessage(msg);

        return new ResponseEntity<>(obj, responseHeaders, HttpStatus.OK);
    }
}
