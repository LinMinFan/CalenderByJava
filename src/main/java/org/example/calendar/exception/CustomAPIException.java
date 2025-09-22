package org.example.calendar.exception;

/**
 * API 檢核錯誤拋出錯誤訊息及statusCode
 *
 * @author jack
 */
public class CustomAPIException extends Exception {

    private Integer statusCode;

    public CustomAPIException(String msg, int statusCode) {
        super(msg);
        this.statusCode = statusCode;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }
}
