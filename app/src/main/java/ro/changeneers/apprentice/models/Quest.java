package ro.changeneers.apprentice.models;

import java.util.HashMap;
import java.util.List;

public class Quest {

    //NU MODIFICA DIN PUBLIC SI LISTA PRIVATA
    public String id;
    public String title;
    public String importanta;
    public String ceInvat;
    public int minimStarsToUnlock;
    private List<Curs> listCursuri;

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
}
