package com.china.ciic.studyweb.speechsynthesis.exception;

/**
 * 语音合成异常
 */
public class TtsException extends Exception {

    /**
     * 错误代码
     * 不得为1,1表示语音合成成功
     * @return
     */
    int errorCode = 2;

    public TtsException (String message){
        super(message);
    }

    public TtsException(Exception e){
        super(e);
    }

    public TtsException (String message,Exception e){
        super(message,e);
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
