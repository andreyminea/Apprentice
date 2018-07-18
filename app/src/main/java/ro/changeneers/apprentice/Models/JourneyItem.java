package ro.changeneers.apprentice.Models;

public class JourneyItem {

    private String Title;
    private String Descriere;

    public JourneyItem(String title,String descriere) {
        Title = title;
        Descriere = descriere;
    }

    public String getTitle() {
        return Title;
    }

    public String getDescriere() {
        return Descriere;
    }

    public void setDescriere(String descriere) {
        Descriere = descriere;
    }

    public void setTitle(String title) {
        Title = title;
    }

}
