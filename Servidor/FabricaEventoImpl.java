import java.rmi.*;
import java.util.*;
import java.rmi.server.*;


class FabricaEventoImpl extends UnicastRemoteObject implements FabricaEvento {
    
    
    FabricaEventoImpl() throws RemoteException{ 
    }

    public Evento crearEvento () throws RemoteException {
        Evento evento = new Evento();
        return evento;
    }
    
    public Evento crearEvento (int id,String artista,String fecha,String lugar,String ciudad,int entradas) throws RemoteException {
        Evento evento = new Evento (id, artista,fecha,lugar,ciudad,entradas);
        return evento;
    }  

}
