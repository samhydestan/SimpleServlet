package si.fri.prpo.vaje.skupina4.servlet;

import java.io.Serializable;

public abstract class Entiteta implements Serializable{
    private int ID;

    public int getID(){
        return this.ID;
    }

    public void setID(int ID){
        this.ID = ID;
    }
}
