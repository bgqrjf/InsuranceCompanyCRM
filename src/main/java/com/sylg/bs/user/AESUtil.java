package com.sylg.bs.user;



public class AESUtil {

	// private static Logger logger = LoggerFactory.getLogger(AESUtil.class);

	private static final char[] map = { '0', '1', '2', '3', '4', '5', '6', '7',
			'8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	/**
	 * 
	 * @param clearText
	 *            待加密的明文
	 * @param password
	 *            密码
	 * @return
	 */
	public static String encrypt(String clearText,String password){
		// 如果是空，则返回""
		if (isNullTrim(clearText))
			return "";
		if (clearText.startsWith("["))
			//			logger.warn("source start with [:" + clearText);
			return clearText;

		StringBuffer buffer = new StringBuffer();
		String encText = binaryToHex(encryptBytes(clearText.getBytes(),
				password.getBytes()));
		// 加密后以中括号[]为开头和结尾
		buffer.append("[").append(encText).append("]");
		return buffer.toString();
	}

	/**
	 * 
	 * @param encText
	 *            密文
	 * @param password
	 *            密码
	 * @return
	 */
	public static String decrypt(String encText, String password) {
		if (isNullTrim(encText))
			return "";

		if (encText != null && !encText.startsWith("["))
			// logger.warn("非法的密文 :" + encText);
			return encText;

		// 去掉表示加密的中括号
		String encTextOut = encText.substring(1, encText.length() - 1);

		System.out.println("ectTextOut:" + encTextOut);

		byte[] sourceByte = bytesFromHex(encTextOut);

		byte[] out = null;
		try {
			out = decryptBytes(sourceByte, password.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
			new RuntimeException(e);
		}

		String sourceCrypt = "";
		try {
			sourceCrypt = new String(out, "utf-8");
			byte[] out2 = sourceCrypt.getBytes("utf-8");
			boolean isUTF8 = true;
			if (out2.length == out.length) {
				for (int i = 0; i < out2.length; i++) {
					if (out2[i] != out[i]) {
						isUTF8 = false;
						break;
					}
				}
			} else {
				isUTF8 = false;
			}
			if (!isUTF8) {
				sourceCrypt = new String(out, "gbk");
			}
		} catch (java.io.UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if ("null".equalsIgnoreCase(sourceCrypt)) {
			sourceCrypt = "";
		}

		return sourceCrypt;

	}

	public static byte[] encryptBytes(byte[] bytes, byte[] password) {
		try {
			javax.crypto.KeyGenerator kgen = javax.crypto.KeyGenerator.getInstance("AES"); // KeyGenerator提供（对称）密钥生成器的功能。使用getInstance
																	// 类方法构造密钥生成器。
			kgen.init(256, new java.security.SecureRandom(password));// 使用用户提供的随机源初始化此密钥生成器，使其具有确定的密钥大小。
			javax.crypto.spec.SecretKeySpec key = new javax.crypto.spec.SecretKeySpec(password, "AES");// 使用SecretKeySpec类来根据一个字节数组构造一个
																	// SecretKey,，而无须通过一个（基于
																	// provider
																	// 的）SecretKeyFactory.
			javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance("AES");// 创建密码器 //为创建
														// Cipher
														// 对象，应用程序调用
														// Cipher 的
														// getInstance
														// 方法并将所请求转换
														// 的名称传递给它。还可以指定提供者的名称（可选）。

			byte[] byteContent = bytes;

			cipher.init(javax.crypto.Cipher.ENCRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(byteContent); // 按单部分操作加密或解密数据，或者结束一个多部分操作。数据将被加密或解密（具体取决于此
															// Cipher 的初始化方式）。

			return result; // 加密
		} catch (java.security.NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (javax.crypto.NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (java.security.InvalidKeyException e) {
			e.printStackTrace();
		} catch (javax.crypto.IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (javax.crypto.BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static byte[] decryptBytes(byte[] content, byte[] password) {
		try {
			javax.crypto.KeyGenerator kgen = javax.crypto.KeyGenerator.getInstance("AES");
			kgen.init(128, new java.security.SecureRandom(password));
			javax.crypto.spec.SecretKeySpec key = new javax.crypto.spec.SecretKeySpec(password, "AES");
			javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance("AES");// 创建密码器
			cipher.init(javax.crypto.Cipher.DECRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(content);
			return result; // 加密
		} catch (java.security.NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (javax.crypto.NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (java.security.InvalidKeyException e) {
			e.printStackTrace();
		} catch (javax.crypto.IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (javax.crypto.BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String binaryToHex(byte[] input) {
		char[] result = new char[input.length * 2];
		for (int i = 0; i < input.length; i++) {
			result[i * 2] = map[(input[i] & 240) >>> 4];
			result[i * 2 + 1] = map[input[i] & 15];
		}
		return new String(result);
	}

	public static byte[] bytesFromHex(String input) {
		String hex = input.toUpperCase();
		byte[] result = new byte[hex.length() / 2];
		for (int i = 0; i < result.length; i++) {
			char c = hex.charAt(i * 2);
			int ci = -1;
			for (int j = 0; j < map.length; j++) {
				if (c == map[j]) {
					ci = j;
					break;
				}
			}
			char c1 = hex.charAt(i * 2 + 1);
			int ci1 = -1;
			for (int j = 0; j < map.length; j++) {
				if (c1 == map[j]) {
					ci1 = j;
					break;
				}
			}
			if (ci == -1 || ci1 == -1)
				throw new RuntimeException("Illeagal Hex String");
			result[i] = (byte) (ci * 16 + ci1);
		}
		return result;
	}

	private static boolean isNullTrim(String str) {
		if (str == null || str.trim().equals(""))
			return true;
		else
			return false;

	}
	
	public static void main(String[] args) {
		String clearText = "123456";
		String password = "YjNhZDg2OTk5OWUw";

		String encText = encrypt(clearText, password);

		System.out.println("加密后：" + encText);

		System.out.println("解密后:" + decrypt(encText, password));
	}


}
