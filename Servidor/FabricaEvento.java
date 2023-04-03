import java.rmi.*;
import java.util.*;

interface FabricaEvento extends Remote {

    Evento crearEvento() throws RemoteException;
    Evento crearEvento (int id,String artista,String fecha,String lugar,String ciudad,int entradas) throws RemoteException;
        
}
