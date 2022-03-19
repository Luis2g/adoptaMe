package mx.edu.utez.adoptaMe.helpers;

import java.nio.charset.StandardCharsets;

import com.google.common.hash.Hashing;

public class Encrypt {

	public String encrypt(String string) {
		return string = Hashing.sha256()
				.hashString(string, StandardCharsets.UTF_8)
				.toString();
	}
	
}