import java.rmi.*;
import java.util.*;

interface ServicioEntradas extends Remote {
    Entrada comprarEntrada(int idEvento,int entradasSolicitadas,String nombre,String dni,int numGrada) throws RemoteException;
    boolean cancelarEntrada(int idCompra) throws RemoteException;
    List<Entrada> listarEntradas(String dni) throws RemoteException;
    boolean cambiarAsiento(int numGrada,int idCompra) throws RemoteException;
    int obtenerEscenario(int idCompra) throws RemoteException;
    //boolean cancelarSesión() throws RemoteException;
}