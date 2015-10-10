public class Main 
{
    public static void main(String args[])
    {
        //controller.Controller.hacer();
        for(model.Juego juego : controller.Controller.findAllJuegosDisponibles())
        {
            System.out.println( juego.toJSON() );
        }
    }
}
