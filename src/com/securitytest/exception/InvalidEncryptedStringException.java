package com.securitytest.exception;

public class InvalidEncryptedStringException extends Exception {
    public InvalidEncryptedStringException(String reason){
        super(reason);
    }
}
