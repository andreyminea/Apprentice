package ro.changeneers.apprentice.models;

import java.util.Calendar;

public class Message
{
    private String name;
    private String text;
    private String date;
    private Boolean link;

    public void setAll(String name, String text, Boolean link, String date)
    {
        this.name = name;
        this.text = text;
        this.link = link;
        this.date = date;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Boolean getLink() {
        return link;
    }



    public Message(String name, String text, Boolean link, String date)
    {
        this.name = name;
        this.text = text;
        this.link = link;
        this.date = date;
    }

}
