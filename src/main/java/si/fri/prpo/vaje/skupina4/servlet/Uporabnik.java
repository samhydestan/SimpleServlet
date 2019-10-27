package si.fri.prpo.vaje.skupina4.servlet;

public class Uporabnik extends Entiteta{
    private int ID;
    private String ImePriimek;

    @Override
    public int getID(){
        return this.ID;
    }

    @Override
    public void setID(int ID){
        this.ID=ID;
    }

    public String getImePriimek(){
        return this.ImePriimek;
    }

    public void setImePriimek(String imePriimek){
        this.ImePriimek=imePriimek;
    }
}
