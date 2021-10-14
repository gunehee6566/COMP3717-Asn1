package ca.bcit.assignment1;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Developer implements Serializable {
    @SerializedName("login")
    @Expose
    private String username;
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }


    @SerializedName("avatar_url")
    @Expose
    private String pictureUrl;
    public String getPictureUrl() {
        return pictureUrl;
    }
    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }


    public Developer(String username, String pictureUrl) {
        this.username = username;
        this.pictureUrl = pictureUrl;
    }


    @Override
    public String toString() {
        return "Developer{" +
                "username='" + username + '\'' +
                ", pictureUrl='" + pictureUrl + '\'' +
                '}';
    }
}

