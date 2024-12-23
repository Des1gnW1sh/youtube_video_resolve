package com.des1gn;

import com.des1gn.client.Client;
import com.des1gn.client.ResolveMediaInfoVo;

import java.io.IOException;

public class Example {
    public static void main(String[] args) throws IOException {
        Client client = new Client();
        ResolveMediaInfoVo resolve = client.resolve("https://www.youtube.com/watch?v=s608ivWeDyo");
    }
}