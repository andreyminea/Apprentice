package ro.changeneers.apprentice.models;

public class Quest {

    private int id;
    private String title;
    private String importanta;
    private String ceInvat;
    private int starsToUnlock;
    private int difficulty;

    private String numeCurs1;
    private String linkCurs1;
    private String proCurs1;
    private String contraCurs1;

    private String numeCurs2;
    private String linkCurs2;
    private String proCurs2;
    private String contraCurs2;

    private String numeCurs3;
    private String linkCurs3;
    private String proCurs3;
    private String contraCurs3;

    public Quest(int id, String title, int starsToUnlock) {
        this.id = id;
        this.title = title;
        this.starsToUnlock = starsToUnlock;
    }

    public Quest(int id, String title, String importanta, String ceInvat, int starsToUnlock, int difficulty,String numeCurs1, String linkCurs1, String proCurs1, String contraCurs1,String numeCurs2, String linkCurs2, String proCurs2, String contraCurs2, String numeCurs3, String linkCurs3, String proCurs3, String contraCurs3) {
        this.id = id;
        this.title = title;
        this.importanta = importanta;
        this.ceInvat = ceInvat;
        this.starsToUnlock = starsToUnlock;
        this.difficulty = difficulty;
        this.linkCurs1 = linkCurs1;
        this.proCurs1 = proCurs1;
        this.contraCurs1 = contraCurs1;
        this.linkCurs2 = linkCurs2;
        this.proCurs2 = proCurs2;
        this.contraCurs2 = contraCurs2;
        this.linkCurs3 = linkCurs3;
        this.proCurs3 = proCurs3;
        this.contraCurs3 = contraCurs3;
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
}
