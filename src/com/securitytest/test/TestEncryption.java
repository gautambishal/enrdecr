package com.securitytest.test;

import com.securitytest.encrdecr.EncrDecr;
import com.securitytest.exception.InvalidDecryption;
import com.securitytest.exception.InvalidEncryptedStringException;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TestEncryption {
    @Test
    public void testEncryptedString(){
       String value="This is the string that needs to be encrypted";
        System.out.println(EncrDecr.encrypt(value));
    }
    @Test
    public void testEncryptDecrypt() throws InvalidEncryptedStringException, InvalidDecryption {
      String value="This is the string that needs to be encrypted";
      String encryptedText=EncrDecr.encrypt(value);
      System.out.println("The encrypted string is :"+encryptedText);
       assertEquals(value,EncrDecr.decrypt(encryptedText));
        System.out.println("After decryption string is:"+EncrDecr.decrypt(encryptedText));
    }
    @Test
    public void testHashValueOfString(){
           String value="I need to see if this cannot be reversed back";
     String hasCodeValue= EncrDecr.hasCod(value);
        System.out.println(hasCodeValue);
     char[] arrayOfHashCodeValue=hasCodeValue.toCharArray();
     char[] reversedString=new char[arrayOfHashCodeValue.length];
        //resultantArray[i]=(char)(((int)charArray[i]/7)*13+19);
        for(int i=0;i<arrayOfHashCodeValue.length;i++){
            reversedString[i]=(char)((((int)(arrayOfHashCodeValue[i])-19)/13)*7);
        }
        assertNotEquals(new String(reversedString),value);
        System.out.println(new String(reversedString));
    }
}
