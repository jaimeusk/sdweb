import java.rmi.*;
import java.util.*;
import java.rmi.server.*;


class ServicioEventosImpl extends UnicastRemoteObject implements ServicioEventos {
    
    private Evento evento;
    private boolean resultado;
    private EventosDAO eventosDAO;
    private List<Evento> listaEventos;

    ServicioEventosImpl() throws RemoteException{ 
        eventosDAO = new EventosDAO();
    }

    public boolean crearEvento (String artista,String fecha,String lugar,String ciudad,int entradas) throws RemoteException {
        Evento evento = new Evento (artista,fecha,lugar,ciudad,entradas);        
        resultado = eventosDAO.agregarEvento(evento);
        return resultado;
    }  


    public boolean borrarEvento(int idEvento) throws RemoteException {
        resultado = eventosDAO.borrarEvento(idEvento);
        return resultado;
    }

    public Evento listarDetalleEvento(int idEvento) throws RemoteException{
        evento = eventosDAO.obtenerEvento(idEvento);
        return evento;        
    }

    public List<Evento> listarEventos() throws RemoteException{
        listaEventos = eventosDAO.obtenerListaEventos();
        return listaEventos;
    }
}
