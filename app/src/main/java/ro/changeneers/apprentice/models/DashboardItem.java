package ro.changeneers.apprentice.models;

/**
 * Created by retea on 11-Jul-18.
 */

public class DashboardItem {
    private int id;
    private String title;
    private String description;
    private int thumbnailRes;

    public DashboardItem(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public DashboardItem(int id, String title, String description, int picRes) {
        this.id = id;
        this.title = title;
        this.description = description;
        thumbnailRes = picRes;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getThumbnail() {
        return thumbnailRes;
    }

    public void setThumbnail(int thumbnail) {
        thumbnailRes = thumbnail;
    }
}
