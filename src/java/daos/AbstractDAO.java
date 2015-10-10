
package daos;
import java.util.ArrayList;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class AbstractDAO
{
    private static SessionFactory sessionFactory = hibernate.HibernateUtil.getSessionFactory();
    
    public static Session conectar()
    {
        Session session = null;
        try
        {
          session = sessionFactory.openSession();  
        } 
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("ERROR: AbstractDAO -> conectar()");
        }
        return session;
    }
    public static boolean desconectar(Session session)
    {
        boolean respuesta = false;
        try
        {
            if(session != null)
            {
                session.close();
            }
        } 
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("ERROR: AbstractDAO -> conectar()");
        }
        return respuesta;
    }

     public static int save(Object object)
    {
        int id = 0;
        
        Transaction transaction = null;
        
        try
        {
            Session sessionProvisoria = conectar();
            
            transaction = sessionProvisoria.beginTransaction();
            /*id = (int)*/ sessionProvisoria.save(object);
            transaction.commit();
           
            desconectar(sessionProvisoria);
            id = 1;
        }
        catch(Exception e)
        {
            transaction.rollback();
            e.printStackTrace();
            System.out.println("ERROR: ABSTRACTDAO -> save: " + object);
        }
        return id;
    }
     public static boolean persist(Object object)
     {
         boolean inserto = false;
         Session sessionProvisoria = conectar();
         try
         {
            sessionProvisoria.beginTransaction();
            sessionProvisoria.persist(object);
            sessionProvisoria.getTransaction().commit();
            inserto = true;
            desconectar(sessionProvisoria);
         } 
         catch (Exception e)
         {
            sessionProvisoria.getTransaction().rollback();
            desconectar(sessionProvisoria);
            e.printStackTrace();
            
            System.out.println("ERROR: AbstractDAO -> Persit: " + object);
         }

         return inserto; 
     }
    public static ArrayList<Object> findAll(Class clase)
    {
        ArrayList<Object> arrRespuesta = new ArrayList<Object>();
        
        try 
        {
            Session sessionProvisoria = conectar();

            Query query = sessionProvisoria.createQuery("from " + clase.getName());
            arrRespuesta = (ArrayList<Object>) query.list();        
            
            desconectar(sessionProvisoria);
            
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            System.out.println("ERROR: ABSTRACTDAO -> findAll: " + clase.getName());
        }
        
        return arrRespuesta;
    }
    public static ArrayList<Object> findAll(String sql)
    {
        ArrayList<Object> arrRespuesta = new ArrayList<Object>();
        
        try 
        {
            Session sessionProvisoria = conectar();
            
            Query query = sessionProvisoria.createQuery(sql);
            arrRespuesta = (ArrayList<Object>) query.list();
            
            desconectar(sessionProvisoria);
        } 
        catch (Exception e) 
        {
            //transaction.rollback();
            e.printStackTrace();
            System.out.println("ERROR: ABSTRACTDAO -> findAll: " + sql );
        }
        
        return arrRespuesta;
    }
    public static Object get(Class clase ,int id)
    {
        Object result = null;

        Session sessionProvisoria = conectar();
        
        result = sessionProvisoria.get(clase, id);
        
        desconectar(sessionProvisoria);
        
        return result;
    }
    public static boolean delete(Object object)
    {
        boolean borro = false;
        
        Session sessionProvisoria = conectar();
        Transaction transaction = null ;
        try
        {
            transaction = sessionProvisoria.beginTransaction();
        
            sessionProvisoria.delete(object);
        
            transaction.commit();
            
            borro = true;
        }
        catch(Exception e)
        {
            if(transaction != null)
            {
                transaction.rollback();
            }
            e.printStackTrace();
            System.out.println("ERROR: AbstractDAO -> delete: "+ object);
        }

        desconectar(sessionProvisoria);
        
        
        return borro;
    }
    public static boolean update(Object object)
    {
        boolean updateo = false;
        
        Session sessionProvisoria = conectar();
        Transaction transaction = null ;
        try
        {
            transaction = sessionProvisoria.beginTransaction();
        
            sessionProvisoria.update(object);
        
            transaction.commit();
            
            updateo = true;
        }
        catch(Exception e)
        {
            if(transaction != null)
            {
                transaction.rollback();
            }
            e.printStackTrace();
            System.out.println("ERROR: AbstractDAO -> update: "+ object);
        }

        desconectar(sessionProvisoria);
        
        
        return updateo;
    }
}

