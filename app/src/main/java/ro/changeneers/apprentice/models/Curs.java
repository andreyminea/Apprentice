package ro.changeneers.apprentice.models;

public class Curs {

    private String id;
    private String title;
    private String url;
    private String pro;
    private String contra;

    public Curs(String id, String title, String url, String pro, String contra) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.pro = pro;
        this.contra = contra;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
