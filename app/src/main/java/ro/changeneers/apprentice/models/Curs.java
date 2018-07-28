package ro.changeneers.apprentice.models;

public class Curs {

    private int id;
    private String title;
    private String uri;
    private String pro;
    private String contra;

    public Curs(int id, String title, String uri, String pro, String contra) {
        this.id = id;
        this.title = title;
        this.uri = uri;
        this.pro = pro;
        this.contra = contra;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getPro() {
        return pro;
    }

    public void setPro(String pro) {
        this.pro = pro;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }
}
