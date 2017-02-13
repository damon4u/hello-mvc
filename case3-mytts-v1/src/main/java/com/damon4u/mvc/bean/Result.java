package com.damon4u.mvc.bean;

/**
 * Description:
 *
 * @author damon4u
 * @version 2017-02-10 15:51
 */
public class Result {

    private boolean ret;

    private String errorCode;

    private String errorMsg;

    public boolean getRet() {
        return ret;
    }

    public void setRet(boolean ret) {
        this.ret = ret;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
