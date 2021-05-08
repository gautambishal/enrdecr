package com.securitytest.exception;

public class InvalidDecryption extends Exception {
    public InvalidDecryption(String reason){
        super(reason);
    }
}
