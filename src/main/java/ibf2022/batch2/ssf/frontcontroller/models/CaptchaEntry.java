package ibf2022.batch2.ssf.frontcontroller.models;

import java.io.Serializable;
import java.util.Optional;


import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonReader;

public class CaptchaEntry implements Serializable{
    private static final long serialVersionUID=1L;

    private String username;
    private String password;
    private String captcha;

    public CaptchaEntry(String username, String password, String captcha){
        this.username = username;
        this.password = password;
        this.captcha = captcha;
    }

    public CaptchaEntry() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    @Override
    public String toString() {
        return "Entry []";
    }



    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("username", username)
                .add("password", password)
                .add("captcha", captcha)
                .build();
    }




}