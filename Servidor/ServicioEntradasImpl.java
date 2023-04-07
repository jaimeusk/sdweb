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

    public Entrada comprarEntrada(int idEvento,int entradasSolicitadas,String nombre,String dni,int numGrada) throws RemoteException {

        //EntradasDAO comprar = new EntradasDAO();
        entrada = entradasDAO.obtenerEntrada(idEvento,entradasSolicitadas,nombre,dni,numGrada);
        return entrada;

    }

    public boolean cancelarEntrada(int idCompra) throws RemoteException {

        //EntradasDAO cancelar = new EntradasDAO();
        resultado = entradasDAO.borrarEntrada(idCompra);
        return resultado;

    }

    public List<Entrada> listarEntradas(String dni) throws RemoteException{
        lista_entradas = entradasDAO.listarEntradas(dni);
        return lista_entradas;

    }

    /*public boolean cancelarSesión() throws RemoteException{
        resultado = entradasDAO.cerraBBDD();
        return resultado;
    }*/

    public boolean cambiarAsiento(int numGrada,int idCompra) throws RemoteException{
        resultado = entradasDAO.actualizaGrada(numGrada,idCompra);
        return resultado;
    }

    public int obtenerEscenario(int idCompra) throws RemoteException{
        int escenario = entradasDAO.obtenerEscenario(idCompra);
        return escenario;
    }
        

}