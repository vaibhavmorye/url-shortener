package com.urlshortner.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

public class Base62Util {
  public static final String BASE62_CHARACTERS =
      "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

  public static String convertToBase62(String input) throws UnsupportedEncodingException {

    BigInteger decimalValue = new BigInteger(input.getBytes("utf-8"));
    StringBuilder base62StringBuilder = new StringBuilder();

    while (decimalValue.compareTo(BigInteger.ZERO) > 0) {
      BigInteger[] quotientAndRemainder = decimalValue.divideAndRemainder(BigInteger.valueOf(62));
      base62StringBuilder.insert(0, BASE62_CHARACTERS.charAt(quotientAndRemainder[1].intValue()));
      decimalValue = quotientAndRemainder[0];
    }
    return base62StringBuilder.toString();
  }

  public static String convertFromBase62(String base62String) {
    BigInteger decimalValue = BigInteger.ZERO;

    for (char c : base62String.toCharArray()) {
      decimalValue =
          decimalValue
              .multiply(BigInteger.valueOf(62))
              .add(BigInteger.valueOf(BASE62_CHARACTERS.indexOf(c)));
    }

    return new String(decimalValue.toByteArray());
  }
}
