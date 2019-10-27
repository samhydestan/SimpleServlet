package si.fri.prpo.vaje.skupina4.servlet;

public class Uporabnik extends Entiteta{
    private String ImePriimek;

    public Uporabnik(String imePriimek){
        ImePriimek=imePriimek;
    }

    public String getImePriimek(){
        return this.ImePriimek;
    }

    public void setImePriimek(String imePriimek){
        this.ImePriimek=imePriimek;
    }
}
