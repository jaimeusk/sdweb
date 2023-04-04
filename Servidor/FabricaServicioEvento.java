import java.rmi.*;
import java.util.*;

interface FabricaServicioEvento extends Remote {
    ServicioEventos crearServicioEvento() throws RemoteException;
}
