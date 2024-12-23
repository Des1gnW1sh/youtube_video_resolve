package com.des1gn.client;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.des1gn.util.Http;
import com.des1gn.util.Url;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Client {

    private static final String RESOLVE_URL = "https://www.youtube.com/youtubei/v1/player";

    private static final List<String> QUALITY_LABEL_LIST = new LinkedList<>();

    static {
        QUALITY_LABEL_LIST.add("1080p");
        QUALITY_LABEL_LIST.add("720p");
        QUALITY_LABEL_LIST.add("480p");
        QUALITY_LABEL_LIST.add("360p");
    }

    public ResolveMediaInfoVo resolve(String videoUrl) throws IOException {
        if (null == videoUrl || videoUrl.isEmpty()){
            throw new NullPointerException("video url is null");
        }
        String videoId = Url.getVideoId(videoUrl);
        String body = getBody(videoId);
        String response = Http.HttpPost(RESOLVE_URL, body);
        return parser(response);
    }

    private ResolveMediaInfoVo parser(String response){
        ResolveMediaInfoVo result = new ResolveMediaInfoVo();
        JSONObject mediaInfo = JSONObject.parseObject(response);
        JSONArray formats = mediaInfo.getJSONObject("streamingData").getJSONArray("adaptiveFormats");
        //get media url
        for (String quality : QUALITY_LABEL_LIST) {
            if(result.getMediaDownloadUrl() == null){
                for (Object format : formats) {
                    JSONObject formatObject = JSONObject.parseObject(JSONObject.toJSONString(format));
                    if (!formatObject.getString("mimeType").startsWith("video")) {
                        continue;
                    }
                    if (!formatObject.containsKey("qualityLabel") || !formatObject.getString("qualityLabel").startsWith(quality)) {
                        continue;
                    }
                    if (!formatObject.containsKey("url")) {
                        continue;
                    }
                    result.setMediaDownloadUrl(formatObject.getString("url"));
                    break;
                }
            }
        }
        //get audio url
        for (Object format : formats) {
            JSONObject formatObject = JSONObject.parseObject(JSONObject.toJSONString(format));
            if (!formatObject.getString("mimeType").startsWith("audio")){
                continue;
            }
            if (!formatObject.containsKey("url")){
                continue;
            }
            result.setAudioDownloadUrl(formatObject.getString("url"));
            break;
        }
        return result;
    }

    private String getBody(String videoId){
         /* {
            "videoId": "eDxBGc9TD4c",
            "context": {
                "client": {
                    "clientName": "ANDROID_TESTSUITE",
                    "clientVersion": "1.9",
                    "androidSdkVersion": 30,
                    "hl": "en",
                    "gl": "US",
                    "utcOffsetMinutes": 0
                }
            }
        }*/
        JSONObject client = new JSONObject();
        client.put("clientName","ANDROID_TESTSUITE");
        client.put("clientVersion","1.9");
        client.put("androidSdkVersion","30");
        client.put("hl","en");
        client.put("gl","US");
        client.put("utcOffsetMinutes",0);

        JSONObject context = new JSONObject();
        context.put("client",client);

        JSONObject param = new JSONObject();
        param.put("videoId",videoId);
        param.put("context",context);
        return param.toJSONString();
    }



}
