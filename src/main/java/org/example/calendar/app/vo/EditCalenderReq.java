package org.example.calendar.app.vo;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EditCalenderReq {

    @NotNull(message = "{validation.notEmpty}")
    private Long id;

    @Size(max = 100, message = "{validation.size}")
    private String name;

    @Pattern(
            regexp = "^#[0-9A-Fa-f]{6}$",
            message = "顏色格式錯誤，請使用 #RRGGBB 格式"
    )
    private String color;

    @NotNull(message = "{validation.notEmpty}")
    private Boolean isDefault;
}
