package com.china.ciic.studyweb.speechsynthesis.quartz.bo;

public class TtsSuccess {
    /**
     * 语音合成任务成功失败
     */
    boolean success = true;
    /**
     * 语音合成异常
     */
    Exception e;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Exception getE() {
        return e;
    }

    public void setE(Exception e) {
        this.e = e;
    }
}
