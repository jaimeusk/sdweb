import java.sql.*;
import java.util.*;

class EntradasDAO {

    private ConexionBBDD conexion;
    private Connection conn;

    public EntradasDAO() throws Exception {
        conexion = new ConexionBBDD();
        conn = conexion.getConnection();
    }



    public Entrada obtenerEntrada(int idEvento,int entradasSolicitadas,String nombre,String dni) {
        Entrada entrada = new Entrada();
        try{

            Statement st = conn.createStatement();
            String sql = "SELECT * FROM eventos WHERE idevento=" + idEvento;
            ResultSet rs = st.executeQuery(sql);
            int id = 0;
            String artista = "";
            int numEntradas_disponibles = 0;
            String fecha = "";
            String lugar = "";
            String ciudad = "";
            
            while(rs.next()){
                id = rs.getInt("idevento");
                artista = rs.getString("artista");
                numEntradas_disponibles = rs.getInt("numentradas");
                fecha = rs.getString("fecha");
                lugar = rs.getString("lugar");
                ciudad = rs.getString("ciudad");
            }
            rs.close();
            st.close();

            if(numEntradas_disponibles<entradasSolicitadas){
                entrada.setEntradas(numEntradas_disponibles);  //Hacer una impresion en el cliente informando que no quedan suficientes entradas disponibles
                if(id == 0){
                    entrada = null;
                }
            }
            else{
                boolean actualizar = actualizar(idEvento,entradasSolicitadas,numEntradas_disponibles,nombre,dni); //Actualizamos el número de entradas para el evento del que se han comprado
                if(actualizar){
                    entrada.setId(id);
                    entrada.setArtista(artista);
                    entrada.setFecha(fecha);
                    entrada.setLugar(lugar);
                    entrada.setCiudad(ciudad);
                }
                else{
                    entrada = null;
                    System.out.println("Error en obtenerEntrada() al actualizar las entradas");
                }
            }
            
        }
        catch(SQLException se){
            System.out.println("SQLException: " + se.getMessage());
            se.printStackTrace(System.out);
        }
        
        return entrada;
        
    }

    public boolean actualizar(int idEvento,int solicitadas,int disponibles,String nombre,String dni) {
        boolean resultado = false;
        try {

            Statement st = conn.createStatement();
            int resta = disponibles-solicitadas;
            String sql = "UPDATE eventos SET numentradas=" + resta + " WHERE idevento=" + idEvento;
            String sql2 = "INSERT INTO compras (idevento,nombre,dni,cantidad) VALUES (" + idEvento + ",'" + nombre + "','" + dni + "'," + solicitadas + ")";
            int filas = st.executeUpdate(sql);
            int filas2 = st.executeUpdate(sql2);
            
            st.close();
            if(filas>0 && filas2>0){
                resultado = true;
                System.out.println("Tablas eventos y compras actualizadas correctamente\n");
            }
            else{
                resultado = false;
            }
            
        }catch(SQLException se){
            System.out.println("SQLException: " + se.getMessage());
            se.printStackTrace(System.out);
        }

        
        
        

        return resultado;

    }

    public boolean borrarEntrada(int idCompra) {
        boolean resultado = false;
        int entradas_compradas = 0;
        int idEvento = 0;
        try{
            Statement st = conn.createStatement();
            String sql_select = "SELECT * FROM compras WHERE idcompra=" + idCompra;
            String sql_delete = "DELETE FROM compras WHERE idcompra=" + idCompra;
    
            ResultSet rs = st.executeQuery(sql_select);
            while(rs.next()){
                idEvento = rs.getInt("idevento");
                entradas_compradas = rs.getInt("cantidad");
            }
            rs.close();

            String sql_update = "UPDATE eventos SET numentradas=numentradas + " + entradas_compradas + " WHERE idevento=" + idEvento;  //Sería necesario pasar el número de entradas que se quieren cancelar

            int filas_d = st.executeUpdate(sql_delete);
            int filas_u = st.executeUpdate(sql_update);

            st.close();
            

            if(filas_d >0 && filas_u >0){
                resultado = true;
            }
            else{
                resultado = false;
            }
            
        }catch(SQLException se){
            System.out.println("SQLException: " + se.getMessage());
            se.printStackTrace(System.out);
        }
        
        return resultado;
    }

    public boolean agregarEvento(Evento evento) {
        //Rellenar igual que antes con la sentencia INSERT. Esperar a definir correctamente la BBDD
        boolean resultado = false;
        try{
            Statement st = conn.createStatement();
            String sql = "INSERT INTO eventos (artista,fecha,lugar,ciudad,numentradas) VALUES (" + evento.getArtista() + "','" + evento.getFecha() + "','" + evento.getLugar() + "','" + evento.getCiudad() + "'," + evento.getEntradas();
            int filas = st.executeUpdate(sql);

            
            st.close();

            if(filas>0){
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

    public List<Evento> listarEventos() {
        List<Evento> lista = new ArrayList<Evento>();
        try{
            Statement st = conn.createStatement();
            String sql = "SELECT * FROM eventos ORDER BY idEvento";
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
            }
            
            System.out.println("Detalles del evento " + evento.getId() + " de " + evento.getArtista() + " obtenidos correctamente");
            
            rs.close();
            st.close();
            
        }catch(SQLException se){
            System.out.println("SQLException: " + se.getMessage());
            se.printStackTrace(System.out);
        }
        
        
        return lista;
    }

    public List<Entrada> listarEntradas(String dni) {
        List<Entrada> lista = new ArrayList<Entrada>();
        try{
            Statement st = conn.createStatement();
            String sql = "SELECT * FROM eventos NATURAL JOIN compras  WHERE dni='" + dni + "'";
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                Entrada entrada = new Entrada();
                entrada.setId(rs.getInt("idcompra"));
                entrada.setArtista(rs.getString("artista"));
                entrada.setCiudad(rs.getString("ciudad"));
                entrada.setLugar(rs.getString("lugar"));
                entrada.setFecha(rs.getString("fecha"));
                entrada.setEntradas(rs.getInt("cantidad"));
                lista.add(entrada);
            }
            rs.close();
            st.close();
            
        }catch(SQLException se){
            System.out.println("SQLException: " + se.getMessage());
            se.printStackTrace(System.out);
        }
        
        
        return lista;
    }

    /*public boolean cerraBBDD() {
        boolean resultado = false;
        try{

            conn.close();
            resultado = true;

        }catch(SQLException se){
            System.out.println("SQLException: " + se.getMessage());
            se.printStackTrace(System.out);
        }
        return resultado;
    }*/
}
