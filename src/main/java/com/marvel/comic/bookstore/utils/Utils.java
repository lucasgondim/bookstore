package com.marvel.comic.bookstore.utils;

import org.springframework.core.env.Environment;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utils {

    private static String getHashForMarvel(String ts, String privateKey, String apiKey) {

        MessageDigest m = null;
        try {
            m = MessageDigest.getInstance("MD5");
            String s = ts+privateKey+apiKey;
            m.update((s).getBytes(), 0, s.length());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return new BigInteger(1,m.digest()).toString(16);

    }

    public static String getComicUrl(Environment env) {

        String comicUrl = env.getProperty("marvel.api.comics");
        String ts = env.getProperty("marvel.ts");
        String apikey = env.getProperty("marvel.api.key");
        String privatekey = env.getProperty("marvel.private.key");

        String hash = getHashForMarvel(ts, privatekey, apikey);

        return comicUrl+"?ts="+ts+"&apikey="+apikey+"&hash="+hash;

    }

}