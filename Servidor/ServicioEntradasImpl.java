import java.rmi.*;
import java.rmi.server.*;

class ServicioEntradasImpl extends UnicastRemoteObject implements ServicioEntradas {

    private Entrada entrada;
    private boolean resultado;
    private EntradasDAO entradasDAO;

    public ServicioEntradasImpl(EntradasDAO entradasDAO){
        
        this.entradasDAO = entradasDAO;

    }

    public Entrada comprarEntrada(int idEvento,int solicitadas) {

        //EntradasDAO comprar = new EntradasDAO();
        entrada = entradasDAO.obtenerEntrada(idEvento,solicitadas);
        return entrada;

    }

    public boolean cancelarEntrada(int idEntrada) {

        //EntradasDAO cancelar = new EntradasDAO();
        resultado = entradasDAO.borrarEntrada(int idEntrada);
        return resultado;

    }

    public boolean agregarEvento(Evento evento) {

        //EntradasDAO agregar = new EntradasDAO();
        resultado = entradasDAO.agregarEvento(evento);
        return resultado;

    }
}