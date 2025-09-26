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
            regexp = "^(19\\d{2}|20\\d{2})-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$",
            message = "開始日期格式錯誤，請使用 yyyy-MM-dd"
    )
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String startDate;

    /**
     * 查詢月份（必填）
     */
    @NotNull(message = "{validation.notEmpty}")
    @Pattern(
            regexp = "^(19\\d{2}|20\\d{2})-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$",
            message = "結束日期格式錯誤，請使用 yyyy-MM-dd"
    )
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String endDate;
}
