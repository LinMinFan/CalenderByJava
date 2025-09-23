package org.example.calendar.app.vo;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddCalenderReq {

    @NotNull(message = "{validation.notEmpty}")
    private Long userId;

    @NotNull(message = "{validation.notEmpty}")
    private String name;

    @NotNull(message = "{validation.notEmpty}")
    private String color;

    @NotNull(message = "{validation.notEmpty}")
    private Boolean is_default;
}
