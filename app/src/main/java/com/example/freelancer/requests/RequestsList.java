package com.example.freelancer.requests;



public class RequestsList {
    private String login;
    private String avatar_url;
    private String html_url;
    public RequestsList(String login, String gitUrl, String avatar_url){
        this.login = login;
        this.avatar_url = avatar_url;
        this.html_url = gitUrl;
    }
    public String getLogin(){
        return login;
    }
    public String getAvatar_url(){
        return avatar_url;
    }
    public  String getHtml_url(){
        return html_url;
    }
}
