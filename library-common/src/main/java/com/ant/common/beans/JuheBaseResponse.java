package com.ant.common.beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JuheBaseResponse {

    @Expose
    private String reason;

    @SerializedName("error_code")
    @Expose
    private int errorCode;

    public String getReason() {
        return reason;
    }

    public int getErrorCode() {
        return errorCode;
    }
}