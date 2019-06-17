package com.payment.merchant.util;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

public class Base64Util {

    public String encode(String plainText) {

        Encoder encoder = null;
        encoder = Base64.getEncoder();
        byte[] targetBytes = plainText.getBytes();
        byte[] encodedBytes = encoder.encode(targetBytes);

        return new String(encodedBytes);

    }

    public String decode(String encodedText) {

        Decoder decoder = null;
        decoder = Base64.getDecoder();
        byte[] targetBytes = encodedText.getBytes();
        byte[] decodedBytes = decoder.decode(targetBytes);

        return new String(decodedBytes);

    }
}
