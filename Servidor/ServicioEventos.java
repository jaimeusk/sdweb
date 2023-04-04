import java.rmi.*;
import java.util.*;

interface ServicioEventos extends Remote {

    boolean crearEvento(String artista,String fecha,String lugar,String ciudad,int entradas) throws RemoteException;
    boolean borrarEvento(int idEvento) throws RemoteException;
    Evento listarDetalleEvento(int idEvento) throws RemoteException;
    List<Evento> listarEventos() throws RemoteException;
        
}
