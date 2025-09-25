package org.example.calendar.app.vo;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class QueryCalendarReq {

    @NotNull(message = "{validation.notEmpty}")
    private Long userId;
}
