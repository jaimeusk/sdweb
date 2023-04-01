import java.rmi.*;
import java.rmi.server.*;

class ServidorEntradas{

    static public void main(String args[]){
        if(args.length()!=1){
            System.err.println("Uso: ServidorEntradas numPuertoRegistro\n");
            return;
        }

        if(System.getSecurityManager() == null){
            System.setSecurityManager(new RMISecurityManager());
        }

        try{
            ServicioEntradasImpl srv = new ServicioEntradasImpl();
            Naming.rebind("rmi://localhost:" + args[0] + "/Entrada",srv);

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