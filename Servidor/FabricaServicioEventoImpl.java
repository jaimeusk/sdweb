import java.rmi.*;
import java.util.*;
import java.rmi.server.*;

class FabricaServicioEventoImpl extends UnicastRemoteObject implements FabricaServicioEvento {
    
    FabricaServicioEventoImpl() throws RemoteException{ }

    public ServicioEventos crearServicioEvento () throws RemoteException {
        ServicioEventosImpl servicioEventos = new ServicioEventosImpl();
        return servicioEventos;
    }

}