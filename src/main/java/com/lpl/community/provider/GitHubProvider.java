package com.lpl.community.provider;

import com.alibaba.fastjson.JSON;
import com.lpl.community.dto.AccessTokenDTO;
import com.lpl.community.dto.GitHubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GitHubProvider {
    public String getAccessToken(AccessTokenDTO accessTokenDTO){
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
            RequestBody body = RequestBody.create(mediaType,JSON.toJSONString(accessTokenDTO) );
            Request request = new Request.Builder()
                    .url("https://github.com/login/oauth/access_token")
                    .post(body)
                    .build();
            try (Response response = client.newCall(request).execute()) {
                String string=response.body().string();
                System.out.println(string);
                String token=string.split("&")[0].split("=")[1];
                return token;
            }catch (IOException e){
                e.printStackTrace();
            }
            return null;
    }
//83b7b4a0a2250a7425006d10b39d9531d208e64e
    public GitHubUser getUser(String accessToken){
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder().url("https://api.github.com/user?access_token="+accessToken).build();
        try {
            Response response=client.newCall(request).execute();
            String string=response.body().string();
            GitHubUser gitHubUser= JSON.parseObject(string,GitHubUser.class);
            return gitHubUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
