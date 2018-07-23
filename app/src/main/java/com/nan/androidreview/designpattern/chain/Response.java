package com.nan.androidreview.designpattern.chain;

/**
 * 相应
 */
public class Response {

    //相应内容
    private String mResult;

    public Response(String result) {
        this.mResult = result;
    }

    public String getReslust() {
        return this.mResult;
    }
}
