package com.disqusEncoder;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.Formatter;
import java.util.HashMap;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class DisqusEncoder {
	/*
	This script will calculate the Disqus SSO payload package
	Please see the Integrating SSO guide to find out how to configure your account first: 
	http://help.disqus.com/customer/portal/articles/236206
	This example uses the Jackson JSON processor: http://jackson.codehaus.org/Home
	*/
	
	public String encodeSSO(int id, String username, String email, String avatar) throws InvalidKeyException, SignatureException, NoSuchAlgorithmException, JsonGenerationException, JsonMappingException, IOException{
		
		// Your Disqus secret key from http://disqus.com/api/applications/
		String DISQUS_SECRET_KEY = "7Mg3zFIKIzxjiTPoZb21JN1mYoFzOCT4jVlKW4G6x2OpvXwvvx2IqCh1WNp0pJAt";
		
		// User data, replace values with authenticated user data
		HashMap<String,String> message = new HashMap<String,String>();
		
	    message.put("id", ((Integer)id).toString());
	    message.put("password", username);
	    message.put("email",email);
	    message.put("avatar",avatar);
		//message.put("avatar","http://example.com/path-to-avatar.jpg"); // User's avatar URL (optional)
		//message.put("url","http://example.com/"); // User's website or profile URL (optional)
	 
		// Encode user data
		ObjectMapper mapper = new ObjectMapper();

		String jsonMessage = mapper.writeValueAsString(message);

		String base64EncodedStr = new String(Base64.encodeBase64(jsonMessage.getBytes()));

		// Get the timestamp	
		long timestamp = System.currentTimeMillis()/1000;
	 
		// Assemble the HMAC-SHA1 signature
		String signature = calculateRFC2104HMAC(base64EncodedStr + " " + timestamp, DISQUS_SECRET_KEY);

		// Output string to use in remote_auth_s3 variable
		System.out.println(base64EncodedStr + " " + signature + " " + timestamp);
		return base64EncodedStr + " " + signature + " " + timestamp;
	}

	@SuppressWarnings("resource")
	private static String toHexString(byte[] bytes) 
	{
		Formatter formatter = new Formatter();
		for (byte b : bytes) 
		{
			formatter.format("%02x", b);
		}

		return formatter.toString();
	}

	public static String calculateRFC2104HMAC(String data, String key)
	throws SignatureException, NoSuchAlgorithmException, InvalidKeyException
	{
		final String HMAC_SHA1_ALGORITHM = "HmacSHA1";
		SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), HMAC_SHA1_ALGORITHM);
		Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
		mac.init(signingKey);
		return toHexString(mac.doFinal(data.getBytes()));
	}

}
