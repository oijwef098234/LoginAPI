package com.example.loginapi.exception;

public class NoWordException extends RuntimeException{
    public NoWordException(String feildName, String noWord){
        super(feildName + "에" + " 금지어 " + noWord + "이(가) 포함되어있습니다.");
    }
}
