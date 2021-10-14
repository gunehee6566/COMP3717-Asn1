package ca.bcit.assignment1;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class Contributors implements Serializable {
    @SerializedName("contributors")
    @Expose
    private ArrayList<Developer> contributors = new ArrayList<Developer>();

    public ArrayList<Developer> getContributors() {
        return contributors;
    }

    public void setContributors(ArrayList<Developer> contributors) {
        this.contributors = contributors;
    }

}