package ro.changeneers.apprentice;

import java.util.HashMap;
import java.util.List;

import ro.changeneers.apprentice.Curs;

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
        return id + " " + title + " " + importanta + " " + ceInvat + " " + minimStarsToUnlock + " " + listCursuri.get(1).id + listCursuri.get(2).title;
    }
}
