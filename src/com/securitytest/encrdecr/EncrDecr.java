package com.securitytest.encrdecr;

import com.securitytest.exception.InvalidDecryption;
import com.securitytest.exception.InvalidEncryptedStringException;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.util.Date;
import java.util.Random;

public class EncrDecr {
    static Random random;
//values from the textarea will be passed here when the button for encryption is passed
    public static String encrypt(String values){
        random=new Random(new Date().getTime());
        BASE64Encoder encoder=new BASE64Encoder();
        byte[] salt=new byte[8];
        random.nextBytes(salt);
        String firstPhase=encoder.encode(salt);
        firstPhase=firstPhase.concat(encoder.encode(values.getBytes()));
        return firstPhase;
    }
    //value of encrypted textarea is passed here
    public static String decrypt(String ecryptedValues) throws InvalidEncryptedStringException, InvalidDecryption {
       if(ecryptedValues.length()>12){
           String cipherText=ecryptedValues.substring(12);
           BASE64Decoder base64Decoder=new BASE64Decoder();
           try{
                byte[] decryptedText=base64Decoder.decodeBuffer(cipherText);
                return new String(decryptedText);
           }catch (Exception e){
                throw new InvalidDecryption("Error occured while decrypting");
           }
       }
       else{
           throw new InvalidEncryptedStringException("Invalid Number of characters");
       }
    }
//calls when hash of the string has to be generated, always send non encrypted text here
   public static String hasCod(String value){
        char[] charArray=value.toCharArray();
        char[] resultantArray=new char[charArray.length];
        for(int i=0;i<charArray.length;i++){
            resultantArray[i]=(char)(((int)charArray[i]/7)*13+19);
        }
        return new String(resultantArray);
    }
}
