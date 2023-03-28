import java.rmi.*;

interface ServicioEntradas extends Remote {
    Entrada comprarEntrada(int idEvento) throws RemoteException;
    boolean cancelarEntrada(int idEntrada) throws RemoteException;
    boolean agregarEvento(Evento evento) throws RemoteException;
}