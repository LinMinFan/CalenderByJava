package org.example.calendar.app.vo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class QueryCalendarReq {

    @Size(min = 4, max = 4, message = "{validation.size}")
    @NotBlank(message = "{validation.notEmpty}")
    private String year;
}
