package org.example.calendar.app.vo;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class QueryEventReq {

    /**
     * 行事曆 ID（必填）
     */
    @NotNull(message = "{validation.notEmpty}")
    private Long calendarId;

    /**
     * 查詢年份（必填）
     */
    @NotNull(message = "{validation.notEmpty}")
    @Pattern(
            regexp = "^(19\\d{2}|20\\d{2})$",
            message = "年份格式錯誤，請使用 1900–2099 之間的 yyyy 格式"
    )
    private String year;

    /**
     * 查詢月份（必填）
     */
    @NotNull(message = "{validation.notEmpty}")
    @Pattern(regexp = "^(0[1-9]|1[0-2])$", message = "月份格式錯誤，請使用 MM 格式")
    private String month;
}
