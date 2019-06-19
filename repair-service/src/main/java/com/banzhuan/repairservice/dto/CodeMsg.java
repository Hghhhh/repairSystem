package com.banzhuan.repairservice.dto;

public class CodeMsg {

    private int code;
    private String msg;

    //通用的错误码
    public static CodeMsg SUCCESS = new CodeMsg(0, "success");
    public static CodeMsg SERVER_ERROR = new CodeMsg(5, "服务端异常");
    public static CodeMsg DOUBLE_DO_ERROR = new CodeMsg(4,"请勿重复操作");

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