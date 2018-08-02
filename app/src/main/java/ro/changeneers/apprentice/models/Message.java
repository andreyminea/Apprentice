package ro.changeneers.apprentice.models;

public class Message
{
    private String name;
    private String text;
    private String date;
    private Boolean link;
    private String user_pic;

    public void setAll(String name, String text, Boolean link, String date, String user_pic)
    {
        this.name = name;
        this.text = text;
        this.link = link;
        this.date = date;
        this.user_pic = user_pic;
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

    public String getUser_pic() {
        return user_pic;
    }

    public void setUser_pic(String user_pic) {
        this.user_pic = user_pic;
    }



    public Message(String name, String text, Boolean link, String date, String user_pic)
    {
        this.name = name;
        this.text = text;
        this.link = link;
        this.date = date;
        this.user_pic = user_pic;
    }
}
