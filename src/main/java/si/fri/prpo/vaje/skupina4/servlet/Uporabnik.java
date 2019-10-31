package si.fri.prpo.vaje.skupina4.servlet;

public class Uporabnik extends Entiteta{
    private String imepriimek;
    private String username;

    public Uporabnik(String imePriimek,String uname){
        imepriimek=imePriimek;
        username=uname;
    }

    public String getImePriimek(){
        return this.imepriimek;
    }

    public void setImePriimek(String imePriimek){
        this.imepriimek=imePriimek;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username=username;
    }

    @Override
    public String toString(){
        return new String("Ime&priimek: "+this.getImePriimek()+", username: "+getUsername());
    }
}
