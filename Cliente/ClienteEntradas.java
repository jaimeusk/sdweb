import java.rmi.*;

class ClienteEntradas{

    static public void main(String args[]){

        if(args.length!=2){
            System.err.println("Uso: ClienteEntradas hostregistro numPuertoRegistro\n");
            return;
        }

        if(System.getSecurityManager() == null){
            System.setSecurityManager(new SecurityManager());
        }

        try{
            ServicioEntradas srv = (ServicioEntradas) Naming.lookup("//" + args[0] + ":" + args[1] + "/Entrada");
            for(Evento e:srv.listarEventos()){
                System.out.println("----------------------------" +
                                   "\n| Identificador: " + e.getId() + 
                                   "\n| Artista: " + e.getArtista() +
                                   "\n| Fecha: " + e.getFecha() +
                                   "\n| Lugar: " + e.getLugar() +
                                   "\n| Ciudad: " + e.getCiudad() + 
                                   "\n| Entradas Disponibles: " + e.getEntradas() +
                                   "\n----------------------------\n");
            }
        }
        catch(RemoteException e){
            System.err.println("Error de comunicación: " + e.toString());
        }
        catch(Exception e){
            System.out.println("Excepción en ClienteEntradas");
            e.printStackTrace();
        }
    }
}