package org.example.calendar.common;

public enum StatusMessage {
    N_0 (0, "hci.status.code.0"),
    /** 帳號未生效或逾期，請連繫管理人員！ */
    N_3 (3, "hci.status.code.3"),
    /** 帳號、密碼錯誤！ */
    N_5 (5, "hci.status.code.5"),
    /** 請確認您的帳號及Email輸入正確。 */
    N_7 (7, "hci.status.code.7"),
    /** 帳號已鎖住，請聯繫系統管理員！ */
    N_10 (10, "hci.status.code.10"),
    /** 帳號已停權，請聯繫系統管理員！ */
    N_13 (13, "hci.status.code.13"),
    /** 帳號已鎖定，請聯繫系統管理員！ */
    N_14 (14, "hci.status.code.14"),
    /** 登入成功，請變更密碼！ */
    N_20 (20, "hci.status.code.20"),
    /** 帳號、密碼錯誤！ */
    N_30 (30, "hci.status.code.30"),
    /** 查無相應的帳號與驗證碼，請重新輸入驗證碼或重新發送驗證信！ */
    N_40 (40, "hci.status.code.40"),
    /** 兩次新輸入的新密碼不一致，請重新輸入！ */
    N_70 (70, "hci.status.code.70"),
    /** 密碼長度不足，請大於等於{0}碼 */
    N_80 (80, "hci.status.code.80"),
    /** 沒有取得資料！ */
    N_90 (90, "hci.status.code.90"),
    /** 檔案上傳超過100mb限制，請調整檔案後重新上傳！ */
    N_94 (94, "hci.status.code.94"),
    /** 無法新增，輸入的角色代碼不正確！ */
    N_100 (100, "hci.status.code.100"),
    /** 無法新增，輸入的程式代碼不正確！ */
    N_110 (110, "hci.status.code.110"),
    /** 資料已存在，無法新增！ */
    N_120 (120, "hci.status.code.120"),
    /** 已存在相同的{0}，無法修改！ */
    N_124 (124, "hci.status.code.124"),
    /** 查無對應的資料可以刪除！ */
    N_130 (130, "hci.status.code.130"),
    /** 查無對應的使用者帳號可更新！ */
    N_133 (133, "hci.status.code.133"),
    /** 查無對應的使用者帳號可停權！ */
    N_135 (135, "hci.status.code.135"),
    /** 查無對應的使用者帳號可復權！ */
    N_136 (136, "hci.status.code.136"),
    /** 供應商資料重複，無法新增！ */
    N_137 (137, "hci.status.code.137"),
    /** 使用者帳號狀態為正常使用，不允許進行復權操作！ */
    N_138 (138, "hci.status.code.138"),
    /** 使用者帳號狀態為重設密碼，不允許進行復權操作！ */
    N_139 (139, "hci.status.code.139"),
    /** 已存在相同的使用帳號，不允許新增！ */
    N_140 (140, "hci.status.code.140"),
    /** 使用者帳號不存在！ */
    N_170 (170, "hci.status.code.170"),
    /** 角色不存在！ */
    N_180 (180, "hci.status.code.180"),
    /** 使用者帳號與角色對應已存在，不允許新增！ */
    N_190 (190, "hci.status.code.190"),
    /** 已存在相同的參數類別，不允許新增！ */
    N_200 (200, "hci.status.code.200"),
    /** 查無對應的資料可更新！ */
    N_203 (203, "hci.status.code.203"),
    /** 參數檢核有誤 */
    N_300 (300, "hci.status.code.300"),
    /** 檔案內容不能是空的 */
    N_350 (350, "hci.status.code.350"),
    /** 參數{0}不可為空值！ */
    N_333 (333, "hci.status.code.333"),
    /** 檔案內容檢核有誤 */
    N_360 (360, "hci.status.code.360"),
    /** 非有效資訊，無法處理。 */
    N_370 (370, "hci.status.code.370"),
    /** 裝置設定錯誤的裝置機型，或是裝置機型設定錯誤，請通知管理人員！ */
    N_400(400, "hci.status.code.400"),
    /** 無法連接控制器，請連繫系統管理員！ */
    N_402 (402, "hci.status.code.402"),
    /** Mail發送失敗！ */
    N_444 (444, "hci.status.code.444"),
    /** 已存在相同Topic，請確認！ */
    N_504 (504, "hci.status.code.504"),
    /** 設備機型參數設定已參用此payload 格式設定，不可刪除。 */
    N_510 (510, "hci.status.code.510"),
    /** Publish Device ID已存在，請確認！ */
    N_521 (521, "hci.status.code.521"),
    /** 該Device Model已存在相同的Parameter name！ */
    N_530 (530, "hci.status.code.530"),
    /** {0}不存在！ */
    N_540 (540 ,"hci.status.code.540"),
    N_999(999, "hci.status.code.999");

    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    StatusMessage(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
