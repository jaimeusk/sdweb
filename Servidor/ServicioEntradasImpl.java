import java.rmi.*;
import java.rmi.server.*;
import java.util.*;
import java.sql.*;

class ServicioEntradasImpl extends UnicastRemoteObject implements ServicioEntradas {

    private Entrada entrada;
    private boolean resultado;
    private EntradasDAO entradasDAO;
    private List<Evento> lista;
    private List<Entrada> lista_entradas;

    public ServicioEntradasImpl() throws RemoteException,Exception{
        
        entradasDAO = new EntradasDAO();
        lista = new ArrayList<Evento>();
        lista_entradas = new ArrayList<Entrada>();

    }

    public Entrada comprarEntrada(int idEvento,int entradasSolicitadas,String nombre,String dni) throws RemoteException {

        //EntradasDAO comprar = new EntradasDAO();
        entrada = entradasDAO.obtenerEntrada(idEvento,entradasSolicitadas,nombre,dni);
        return entrada;

    }

    public boolean cancelarEntrada(int idCompra) throws RemoteException {

        //EntradasDAO cancelar = new EntradasDAO();
        resultado = entradasDAO.borrarEntrada(idCompra);
        return resultado;

    }

    public boolean agregarEvento(Evento evento) throws RemoteException {

        //EntradasDAO agregar = new EntradasDAO();
        resultado = entradasDAO.agregarEvento(evento);
        return resultado;

    }

    public List<Evento> listarEventos() throws RemoteException{
        lista = entradasDAO.listarEventos();
        return lista;
    }

    public List<Entrada> listarEntradas(String dni) throws RemoteException{
        lista_entradas = entradasDAO.listarEntradas(dni);
        return lista_entradas;

    }
}