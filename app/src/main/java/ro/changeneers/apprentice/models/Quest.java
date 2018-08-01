package ro.changeneers.apprentice.models;

import java.util.List;

public class Quest {

    private String id;
    private String title;
    private String importanta;
    private String ceInvat;
    private int minimStarsToUnlock;
    private List<Curs> cursuri;


    public Quest(String id, String title, int minimStarsToUnlock) {
        this.id = id;
        this.title = title;
        this.minimStarsToUnlock = minimStarsToUnlock;
    }

    public Quest(String id, String title, String importanta, String ceInvat, int minimStarsToUnlock,List<Curs> cursuri){
        this.id = id;
        this.title = title;
        this.importanta = importanta;
        this.ceInvat = ceInvat;
        this.minimStarsToUnlock = minimStarsToUnlock;
        this.cursuri = cursuri;

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

    public String getImportanta() {
        return importanta;
    }

    public void setImportanta(String importanta) {
        this.importanta = importanta;
    }

    public String getCeInvat() {
        return ceInvat;
    }

    public void setCeInvat(String ceInvat) {
        this.ceInvat = ceInvat;
    }

    public int getMinimStarsToUnlock() {
        return minimStarsToUnlock;
    }

    public void setMinimStarsToUnlock(int minimStarsToUnlock) {
        this.minimStarsToUnlock = minimStarsToUnlock;
    }

    public List<Curs> getCursuri() {
        return cursuri;
    }

    public void setCursuri(List<Curs> cursuri) {
        this.cursuri = cursuri;
    }
}
