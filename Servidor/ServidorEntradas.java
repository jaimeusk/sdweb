import java.rmi.*;
import java.rmi.server.*;
import java.util.*;

class ServidorEntradas{

    static public void main(String args[]){
        if(args.length!=1){
            System.err.println("Uso: ServidorEntradas numPuertoRegistro\n");
            return;
        }

        if(System.getSecurityManager() == null){
            System.setSecurityManager(new RMISecurityManager());
        }

        try{
            ServicioEntradasImpl srvEntradas = new ServicioEntradasImpl();
            Naming.rebind("rmi://93.189.94.195:" + args[0] + "/Entrada",srvEntradas);

        }catch(RemoteException e){
            System.err.println("Error de comunicación: " + e.toString());
            System.exit(1);
        }
        catch(Exception e){
            System.err.println("Excepción en ServidorEntradas");
            e.printStackTrace();
            System.exit(1);
        }
    }
}