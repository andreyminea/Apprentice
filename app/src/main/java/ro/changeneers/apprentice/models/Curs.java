package ro.changeneers.apprentice.models;

import com.google.gson.annotations.SerializedName;

public class Curs {

    @SerializedName("id")
    public String id;

    @SerializedName("title")
    public String title;

    @SerializedName("descriere")
    public String descriere;

    @SerializedName("url")
    public String url;

    @SerializedName("durata")
    public String durata;

    @SerializedName("cost")
    public String cost;

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    private int stars;

    public Curs(){}


}
