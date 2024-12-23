package com.des1gn.client;

public class ResolveMediaInfoVo {

    private String mediaDownloadUrl;

    private String audioDownloadUrl;

    public void setMediaDownloadUrl(String mediaDownloadUrl){
        this.mediaDownloadUrl = mediaDownloadUrl;
    }

    public void setAudioDownloadUrl(String audioDownloadUrl){
        this.audioDownloadUrl = audioDownloadUrl;
    }

    public String getMediaDownloadUrl(){
        return this.mediaDownloadUrl;
    }


    public String getAudioDownloadUrl(){
        return this.audioDownloadUrl;
    }
}
