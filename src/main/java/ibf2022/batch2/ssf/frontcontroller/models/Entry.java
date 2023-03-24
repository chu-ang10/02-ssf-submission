package ibf2022.batch2.ssf.frontcontroller.models;

import java.io.Serializable;


import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Entry implements Serializable{
    private static final long serialVersionUID=1L;

    private String username;
    private String password;
    
    public Entry() {
    }

    public Entry(String username, String password){
        this.username = username;
        this.password = password;
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

    @Override
    public String toString() {
        return "Entry []";
    }

    

    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("username", username)
                .add("password", password)
                .build();
    }

    
    

}
