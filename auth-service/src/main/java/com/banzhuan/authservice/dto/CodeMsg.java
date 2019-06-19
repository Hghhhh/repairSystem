package com.banzhuan.authservice.dto;

public class CodeMsg {

    private int code;
    private String msg;

    //通用的错误码
    public static CodeMsg SUCCESS = new CodeMsg(0, "success");
    public static CodeMsg SERVER_ERROR = new CodeMsg(5, "服务端异常");
    public static CodeMsg USER_NOT_FOUND = new CodeMsg(6, "没有找到该用户");
    public int getCode() {
        return code;
    }


    public String getMsg() {
        return msg;
    }

    public CodeMsg(int code,String msg) {
        this.code = code;
        this.msg = msg;
    }

}