package si.fri.prpo.vaje.skupina4.servlet;

public class Uporabnik extends Entiteta{
    private String ImePriimek;

    public Uporabnik(int ID, String imePriimek){
        super.setID(ID);
        ImePriimek=imePriimek;
    }

    public String getImePriimek(){
        return this.ImePriimek;
    }

    public void setImePriimek(String imePriimek){
        this.ImePriimek=imePriimek;
    }

    @Override
    public String toString(){
        return new String("ID: "+String.valueOf(super.getID())+", Ime&priimek: "+this.getImePriimek());
    }
}
