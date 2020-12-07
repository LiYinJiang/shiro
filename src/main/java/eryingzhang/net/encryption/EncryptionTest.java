package eryingzhang.net.encryption;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;

public class EncryptionTest {

	@Test
	public void simpleEncryption() {
		String password = "halalu";
		Md5Hash encode = new Md5Hash(password);
		System.out.println("encode : " + encode.toString());
	}

	@Test
	public void saltEncryption() {
		String password = "halalu";
		String salt = new SecureRandomNumberGenerator().nextBytes().toString();
		int time = 3;
		String algorithmName = "md5";
		SimpleHash encode = new SimpleHash(algorithmName, password, salt, time);
		System.out.printf("source : %s algorithName : %s salt : %s time: %d cipherText: %s", password, algorithmName,
				salt,time, encode.toString());
	}
	

	
	
}
