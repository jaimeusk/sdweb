import java.rmi.*;
import java.rmi.server.*;
import java.util.*;

class ServidorEventos{

    static public void main(String args[]){
        if(args.length!=1){
            System.err.println("Uso: ServidorEventos numPuertoRegistro\n");
            return;
        }

        if(System.getSecurityManager() == null){
            System.setSecurityManager(new RMISecurityManager());
        }
              
        try{            
            FabricaServicioEventoImpl fabricaSrvEvent = new FabricaServicioEventoImpl();
            Naming.rebind("rmi://localhost:" + args[0] + "/Eventos",fabricaSrvEvent);
        }catch(RemoteException e){
            System.err.println("Error de comunicación: " + e.toString());
            System.exit(1);
        }
        catch(Exception e){
            System.err.println("Excepción en ServidorEventos");
            e.printStackTrace();
            System.exit(1);
        }
    }
}