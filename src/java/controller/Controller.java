package controller;

import java.util.ArrayList;

public class Controller 
{
    public static void hacer()
    {
    }
    public static ArrayList<model.Juego> findAllJuegosDisponibles()
    {
        return daos.JuegosDAO.findAll();
    }
    
}
