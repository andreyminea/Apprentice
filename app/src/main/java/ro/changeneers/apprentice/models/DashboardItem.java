package ro.changeneers.apprentice.models;

/**
 * Created by retea on 11-Jul-18.
 */

public class DashboardItem {
    private String Title;
    private String Description;
    private int Thumbnail;

    public DashboardItem(String title, String description) {
        Title = title;
        Description = description;
    }

    public String getTitle() {
        return Title;
    }

    public String getDescription() {
        return Description;
    }


    public void setTitle(String title) {
        Title = title;
    }

    public void setDescription(String description) {
        Description = description;
    }

}
