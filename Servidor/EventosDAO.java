import java.sql.*;
import java.util.*;


class EventosDAO {

    private ConexionBBDD conexion;
    private Connection conn;
    private boolean resultado;

    public EventosDAO(){
        conexion = new ConexionBBDD();
        conn = conexion.getConnection();
    }

    public boolean agregarEvento(Evento evento){
        
        try{
            Statement st = conn.createStatement();
            String sql = "INSERT INTO eventos (idevento, artista,fecha,lugar,ciudad,numentradas) VALUES (250,'" + evento.getArtista() + "','" + evento.getFecha() + "','" + evento.getLugar() + "','" + evento.getCiudad() + "'," + evento.getEntradas() + ")";
            System.out.println(sql);
            int filas = st.executeUpdate(sql);

            st.close();

            if(filas>0){
                System.out.println("Evento " + evento.getArtista() + " añadido correctamente\n");
                resultado = true;

            }else{
                resultado = false;
            }
            
        }catch(SQLException se){
            System.out.println("SQLException: " + se.getMessage());
            se.printStackTrace(System.out);
        }
        
        return resultado;
    }


    public boolean borrarEvento (int idEvento){

        try{
            Statement st = conn.createStatement();
            String sql_deleteE = "DELETE FROM eventos WHERE idevento=" + idEvento;
            String sql_deleteC = "DELETE FROM compras WHERE idevento=" + idEvento;
            
            int filasE = st.executeUpdate(sql_deleteE);
            int filasC = st.executeUpdate(sql_deleteC);
            st.close();
    
            if(filasE>0 && filasC>0){
                System.out.println("Evento " + idEvento + " eliminado correctamente\n");
                resultado = true;
            }
            else{
                System.out.println("Error al eliminar el evento " + idEvento);
                resultado = false;
            }
        }catch(SQLException se){
            System.out.println("SQLException: " + se.getMessage());
            se.printStackTrace(System.out);
        }   
        return resultado;
    }

    public Evento obtenerEvento(int idEvento){
        Evento evento = new Evento();         
        
        try{
            Statement st = conn.createStatement();
            String sql = "SELECT * FROM eventos WHERE idevento="+idEvento;
            ResultSet rs = st.executeQuery(sql);
                      
            
            while(rs.next()){
                evento.setId(rs.getInt("idevento"));
                evento.setArtista(rs.getString("artista"));
                evento.setEntradas(rs.getInt("numentradas"));
                evento.setFecha(rs.getString("fecha"));
                evento.setLugar(rs.getString("lugar"));
                evento.setCiudad(rs.getString("ciudad"));
            }

            rs.close();
            st.close();

            
        }catch(SQLException se){
            System.out.println("SQLException: " + se.getMessage());
            se.printStackTrace(System.out);
        }
        
        return evento;  
    }

    public List<Evento> obtenerListaEventos() {

        List<Evento> lista = new ArrayList<Evento>();

        try{
            Statement st = conn.createStatement();
            String sql = "SELECT * FROM eventos";
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                Evento evento = new Evento();
                evento.setId(rs.getInt("idEvento"));
                evento.setArtista(rs.getString("artista"));
                evento.setFecha(rs.getString("fecha"));
                evento.setLugar(rs.getString("lugar"));
                evento.setCiudad(rs.getString("ciudad"));
                evento.setEntradas(rs.getInt("numentradas"));
                lista.add(evento);
                System.out.println("Eventos agregados a la lista\n");
            }
            //System.out.println("Eventos agregados a la lista\n");
            rs.close();
            st.close();

            
        }catch(SQLException se){
            System.out.println("SQLException: " + se.getMessage());
            se.printStackTrace(System.out);
        }
        
        
        return lista;
    }
}
