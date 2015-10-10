package model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.google.gson.Gson;

@Entity
@Table(name="juegos")
public class Juego implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private int id;
    private String nombre;

    public Juego() {
    }

    public Juego(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
    
    //<editor-fold desc="GYS:">
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    //</editor-fold>

    @Override
    public String toString() 
    {
        return "Juego{" + "id=" + id + ", nombre=" + nombre + '}';
    }
    public String toJSON()
    {
        //String JSON = "{\"id' : \"" + id + "\", \"nombre\" : \"" + nombre + "\"}";
        
        Gson  gson = new Gson ();
        
        String JSON = gson.toJson(this);
        
        return JSON;
    }
    
    
}
