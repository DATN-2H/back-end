package com.menuplus.backend.library.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
public class HashUtil {

  public static String sha1(String input) {
    String sha1 = null;
    try {
      MessageDigest msdDigest = MessageDigest.getInstance("SHA-1");
      msdDigest.update(
        input.getBytes(StandardCharsets.UTF_8),
        0,
        input.length()
      );
      sha1 = bytesToHex(msdDigest.digest());
    } catch (NoSuchAlgorithmException e) {
      log.error("SHA-1 algorithm not found", e);
    }
    return sha1;
  }

  private static String bytesToHex(byte[] bytes) {
    StringBuilder hexString = new StringBuilder();
    for (byte b : bytes) {
      String hex = Integer.toHexString(0xff & b);
      if (hex.length() == 1) {
        hexString.append('0');
      }
      hexString.append(hex);
    }
    return hexString.toString().toUpperCase();
  }
}
