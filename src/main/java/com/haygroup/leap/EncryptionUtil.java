package com.haygroup.leap;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class EncryptionUtil {
	
	StandardPBEStringEncryptor encryptor;
	
	public EncryptionUtil() {
	
		encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword("ThisIsTheMostSecureEncruptionDecryptionKeyDonotIRepeatDONOTMessWithIt"); 
        encryptor.setAlgorithm("PBEWithMD5AndTripleDES"); 
	}

	
	 public String encryptString(String key){
			
	        return encryptor.encrypt(key);
	    }

	    public String decryptString(String hash){
	 
	        return encryptor.decrypt(hash);
	    }

}
