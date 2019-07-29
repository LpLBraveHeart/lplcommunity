package com.lpl.community.controller;

import com.lpl.community.dto.AccessTokenDTO;
import com.lpl.community.dto.GitHubUser;
import com.lpl.community.provider.GitHubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OuthorizeController {

    @Autowired
    GitHubProvider gitHubProvider;

    @Value("${gitbub.client.id}")
    private String clientId;

    @Value("${gitbub.client.secret}")
    private String clientSecret;

    @Value("${gitbub.redirect.uri}")
    private String redirectUri;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,@RequestParam(name = "state") String state){
        AccessTokenDTO accessTokenDTO=new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirect_uri(redirectUri);
        String token=gitHubProvider.getAccessToken(accessTokenDTO);
        GitHubUser gitHubUser=gitHubProvider.getUser(token);
        System.out.println(gitHubUser.getName());
        return "index";
    }

}
