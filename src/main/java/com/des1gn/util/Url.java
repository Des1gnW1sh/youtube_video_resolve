package com.des1gn.util;

public class Url {


    /**
     *  resole url to get video_id
     * @param url
     * @return url‘s video_id
     */
    public static String getVideoId(String url) throws NullPointerException{
        int startIndex = url.indexOf("=");
        if (startIndex < 0){
            throw new NullPointerException("url not null");
        }
        startIndex ++;
        int lastIndex = -1;
        if ((lastIndex = url.indexOf("&")) > -1){
        }else {
            lastIndex = url.length();
        }
        return url.substring(startIndex,lastIndex);
    }

}
