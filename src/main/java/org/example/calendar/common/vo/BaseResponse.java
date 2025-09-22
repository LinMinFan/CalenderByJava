package org.example.calendar.common.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer statusCode;
    private String message;
}
