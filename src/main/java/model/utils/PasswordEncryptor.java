package model.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

public class PasswordEncryptor {

	// Gera um hash SHA-256 e codifica em Base64
	public static String hashPassword(String plainTextPassword) {
		try {
			var digest = MessageDigest.getInstance("SHA-256");
			var hash = digest.digest(plainTextPassword.getBytes(StandardCharsets.UTF_8));
			return Base64.getEncoder().encodeToString(hash);
		} catch (Exception e) {
			throw new RuntimeException("Erro ao gerar hash da senha", e);
		}
	}

	// Compara a senha fornecida com o hash armazenado
	public static boolean checkPassword(String plainTextPassword, String hashedPassword) {
		return hashPassword(plainTextPassword).equals(hashedPassword);
	}
}

