package daos;

public class JuegosDAO 
{
    public static java.util.ArrayList<model.Juego> findAll()
    {
         java.util.ArrayList<model.Juego> arr = new  java.util.ArrayList<model.Juego>();
         
         for(Object o : daos.AbstractDAO.findAll(model.Juego.class))
         {
             model.Juego juego = (model.Juego) o; 
             arr.add(juego);
         }
         
         return arr;
    }
}
