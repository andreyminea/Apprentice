package ro.changeneers.apprentice.models;

public class Message
{
    private String name;
    private String text;
    private String date;

    public void setBoth(String name, String text)
    {
        this.name = name;
        this.text = text;
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



    public Message(String name, String text)
    {
        this.name = name;
        this.text = text;
    }


}
