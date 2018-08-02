package ro.changeneers.apprentice;

import java.util.HashMap;
import java.util.List;

public class Quest
{
    public String Descriere1;
    public int StarsReq;
    public String Titlu;
    public String Descreire2;
    private List<Curs> listCursuri;
    /**
     * a nu se folosi :D, e folosit doar de FirebaseDatabase
     */
    public HashMap<String , Curs> Cursuri;


    public String getDescriere1() {
        return Descriere1;
    }

    public int getStarsReq() {
        return StarsReq;
    }

    public String getTitlu() {
        return Titlu;
    }

    public String getDescreire2() {
        return Descreire2;
    }


    public void setListCursuri(List<Curs> cursuri) {
        listCursuri = cursuri;
    }

    public List<Curs> getlistCursuri() {
        return listCursuri;
    }




    public Quest()
    {}


    public Quest(String descriere, String title,int StarsReq, String desriere2)
    {
        this.Descriere1 = descriere;
        this.StarsReq = StarsReq;
        this.Titlu = title;
        this.Descreire2 = desriere2;
    }

    @Override
    public String toString()
    {
        return Descriere1 + " " + StarsReq + " " + Titlu + Descreire2;
    }
}
