package ro.changeneers.apprentice;

public class Quest
{
    public String Descriere1;
    public int StarsReq;
    public String Titlu;
    public String Descreire2;


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
