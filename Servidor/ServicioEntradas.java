import java.rmi.*;
import java.util.*;

interface ServicioEntradas extends Remote {
    Entrada comprarEntrada(int idEvento,int entradasSolicitadas,String nombre,String dni) throws RemoteException;
    boolean cancelarEntrada(int idCompra) throws RemoteException;
    boolean agregarEvento(Evento evento) throws RemoteException;
    List<Evento> listarEventos() throws RemoteException;
    List<Entrada> listarEntradas(String dni) throws RemoteException;
    //boolean cancelarSesión() throws RemoteException;
}