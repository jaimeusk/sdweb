import java.rmi.*;
import java.rmi.server.*;

class ServicioEntradasImpl extends UnicastRemoteObject implements ServicioEntradas {

    private Entrada entrada;
    private boolean resultado;

    public ServicioEntradasImpl(){

    }

    public Entrada comprarEntrada(int idEvento) {

        EntradasDAO comprar = new EntradasDAO();
        entrada = comprar.obtenerEntrada(idEvento);
        return entrada;

    }

    public boolean cancelarEntrada(int idEntrada) {

        EntradasDAO cancelar = new EntradasDAO();
        resultado = cancelar.borrarEntrada(int idEntrada);
        return resultado;

    }

    public boolean agregarEvento(Evento evento) {

        EntradasDAO agregar = new EntradasDAO();
        resultado = agregar.agregarEvento(evento);
        return resultado;

    }
}