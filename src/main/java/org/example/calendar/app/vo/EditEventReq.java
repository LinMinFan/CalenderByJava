package org.example.calendar.app.vo;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.Date;

@Data
public class EditEventReq {

    @NotNull(message = "{validation.notEmpty}")
    private Long id;

    @Pattern(regexp = "^.*\\S.*$", message = "{validation.notEmpty}")
    private String title;

    private String description;

    private String location;

    private Date startDatetime;

    private Date endDatetime;

    private Boolean isAllDay;

    private String recurrenceRule;

    @FutureOrPresent(message = "日期必須是今天或未來的日期")
    private Date recurrenceEndDate;

    // 自訂驗證：結束時間必須晚於開始時間
    @AssertTrue(message = "開始與結束時間必須同時提供，且結束時間要晚於開始時間")
    public boolean isEndAfterStart() {
        if (startDatetime == null && endDatetime == null) {
            return true; // 都沒更新 → 合法
        }
        if (startDatetime == null || endDatetime == null) {
            return false; // 只填一個 → 不合法
        }
        return endDatetime.after(startDatetime);
    }
}
