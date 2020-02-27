package com.marvel.comic.bookstore.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utils {

    private String getHashForMarvel(String ts, String privatekey, String apikey) throws NoSuchAlgorithmException {

        MessageDigest m = MessageDigest.getInstance("MD5");
        String s = ts+privatekey+apikey;
        m.update((s).getBytes(), 0, s.length());

        return new BigInteger(1,m.digest()).toString(16);

    }
}
