package ro.changeneers.apprentice.models;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.List;

import static ro.changeneers.apprentice.utils.Constants.NOT_STARTED;

public class Quest {

    //NU MODIFICA DIN PUBLIC SI LISTA PRIVATA

    @SerializedName("id")
    public String id;

    @SerializedName("title")
    public String title;

    @SerializedName("importanta")
    public String importanta;

    @SerializedName("ceInvat")
    public String ceInvat;

    @SerializedName("minimStarsToUnlock")
    public int minimStarsToUnlock;

    @SerializedName("listCursuri")
    private List<Curs> listCursuri;

    private int currentStars;

    private int maxStars;

    private int status = NOT_STARTED;

    private int difficulty;

    public int getCurrentStars() {
        return currentStars;
    }

    public void setCurrentStars(int currentStars) {
        this.currentStars = currentStars;
    }

    public int getMaxStars() {
        return maxStars;
    }

    public void setMaxStars(int maxStars) {
        this.maxStars = maxStars;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * a nu se folosi :D, e folosit doar de FirebaseDatabase
     */
    public HashMap<String , Curs> Cursuri;

    //Are nevoie FirebaseDatabase de acest constructor NU STERGE!
    public Quest(){ }

    public void setListCursuri(List<Curs> cursuri) {
        listCursuri = cursuri;
    }


    @Override
    public String toString() {
        return id + " " + title + " " + importanta + " " + ceInvat + " " + minimStarsToUnlock + " ";
    }

    public List<Curs> getListCursuri() {
        return listCursuri;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
}
