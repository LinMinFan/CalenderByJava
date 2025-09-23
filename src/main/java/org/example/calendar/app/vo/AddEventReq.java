package org.example.calendar.app.vo;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class AddEventReq {

    @NotNull(message = "{validation.notEmpty}")
    private Long calendarId;

    @NotBlank(message = "{validation.notEmpty}")
    private String title;

    private String description;

    private String location;

    @NotNull(message = "{validation.notEmpty}")
    private Date startDatetime;

    @NotNull(message = "{validation.notEmpty}")
    private Date endDatetime;

    @NotNull(message = "{validation.notEmpty}")
    private Boolean isAllDay;

    private String recurrenceRule;

    @FutureOrPresent(message = "日期必須是今天或未來的日期")
    private Date recurrenceEndDate;

    // 自訂驗證：結束時間必須晚於開始時間
    @AssertTrue(message = "結束時間必須晚於開始時間")
    public boolean isEndAfterStart() {
        if (startDatetime == null || endDatetime == null) {
            return true; // 讓 @NotNull 處理 null 情況
        }
        return endDatetime.after(startDatetime);
    }
}
