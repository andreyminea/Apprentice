package ro.changeneers.apprentice.models;

public class Quest {

    private int id;
    private String title;
    private String importanta;
    private String ceInvat;
    private int starsToUnlock;
    private int difficulty;

    private Curs curs1;

    private Curs curs2;

    private Curs curs3;


    public Quest(int id, String title, int starsToUnlock) {
        this.id = id;
        this.title = title;
        this.starsToUnlock = starsToUnlock;
    }

    public Quest(int id, String title, String importanta, String ceInvat, int starsToUnlock, int difficulty, Curs curs1, Curs curs2, Curs curs3) {
        this.id = id;
        this.title = title;
        this.importanta = importanta;
        this.ceInvat = ceInvat;
        this.starsToUnlock = starsToUnlock;
        this.difficulty = difficulty;
        this.curs1 = curs1;
        this.curs2 = curs2;
        this.curs3 = curs3;

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

    public int getStarsToUnlock() {
        return starsToUnlock;
    }

    public void setStarsToUnlock(int starsToUnlock) {
        this.starsToUnlock = starsToUnlock;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public Curs getCurs1() {
        return curs1;
    }

    public void setCurs1(Curs curs1) {
        this.curs1 = curs1;
    }

    public Curs getCurs2() {
        return curs2;
    }

    public void setCurs2(Curs curs2) {
        this.curs2 = curs2;
    }

    public Curs getCurs3() {
        return curs3;
    }

    public void setCurs3(Curs curs3) {
        this.curs3 = curs3;
    }
}
