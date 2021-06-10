package com.ant.common.beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JuheBaseResponse<T> {

    @SerializedName("reason")
    @Expose
    private String reason;

    @SerializedName("error_code")
    @Expose
    private int errorCode;

    @SerializedName("result")
    @Expose
    private T result;

    public String getReason() {
        return reason;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public boolean isSuccessful() {
        return errorCode == 0;
    }

    public T getResult() {
        return result;
    }
}