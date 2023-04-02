import java.sql.*;
import java.util.*;

class EntradasDAO {

    private Connection conn;

    public EntradasDAO() throws Exception {

        try{
            //Carga del driver
            Class.forName("org.postgresql.Driver");
             
            //Definición de la cadena de conexión
            String url = "jdbc:postgresql://localhost:8001/entradas_db";
            String user = "rmi";
            String pass = "rmi20";

            //Creación del objeto Connection a través de Driver Manager
            conn = DriverManager.getConnection(url,user,pass);

            System.out.println("La conexión establecida es: " + conn);
        }
        catch(ClassNotFoundException cnfe) {
            System.err.println(cnfe);
        }
        catch(SQLException sqle) {
            System.err.println(sqle);
        };
    };

    public Entrada obtenerEntrada(int idEvento,int entradasSolicitadas,String nombre,String dni){
        Entrada entrada = new Entrada();
        try{

            Statement st = conn.createStatement();
            String sql = "SELECT * FROM eventos WHERE idevento=" + idEvento;
            ResultSet rs = st.executeQuery(sql);
            

            int id = rs.getInt("idevento");
            String artista = rs.getString("artista");
            int numEntradas_disponibles = rs.getInt("numentradas");
            String fecha = rs.getString("fecha");
            String lugar = rs.getString("lugar");
            String ciudad = rs.getString("ciudad");
            rs.close();
            st.close();

            if(numEntradas_disponibles<entradasSolicitadas){
                entrada.setEntradas(numEntradas_disponibles);  //Hacer una impresion en el cliente informando que no quedan suficientes entradas disponibles
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
            conn.close();
        }
        catch(SQLException se){
            System.out.println("SQLException: " + se.getMessage());
            se.printStackTrace(System.out);
        }
        
        return entrada;
        
    }

    public boolean actualizar(int idEvento,int solicitadas,int disponibles,String nombre,String dni){
        boolean resultado = false;
        try {

            Statement st = conn.createStatement();
            int resta = disponibles-solicitadas;
            String sql = "UPDATE eventos SET numentradas=" + resta + " WHERE id=" + idEvento;
            String sql2 = "INSERT INTO compras (idevento,nombre,dni,cantidad) VALUES (" + idEvento + ",'" + nombre + "','" + dni + "'," + solicitadas;
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
            conn.close();
        }catch(SQLException se){
            System.out.println("SQLException: " + se.getMessage());
            se.printStackTrace(System.out);
        }

        
        
        

        return resultado;

    }

    public boolean borrarEntrada(int idCompra){
        boolean resultado = false;
        try{
            Statement st = conn.createStatement();
            String sql_select = "SELECT idevento FROM compras WHERE idcompra=" + idCompra;
            String sql_delete = "DELETE FROM compras WHERE idcompra=" + idCompra;
    
            ResultSet rs = st.executeQuery(sql_select);
            int idEvento = rs.getInt("idevento");
            int entradas_compradas = rs.getInt("cantidad");
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
            conn.close();
        }catch(SQLException se){
            System.out.println("SQLException: " + se.getMessage());
            se.printStackTrace(System.out);
        }
        
        return resultado;
    }

    public boolean agregarEvento(Evento evento){
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
            conn.close();
        }catch(SQLException se){
            System.out.println("SQLException: " + se.getMessage());
            se.printStackTrace(System.out);
        }
       
        
        
        return resultado;
    }

    public List<Evento> listarEventos(){
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
            }
            st.close();
            conn.close();
        }catch(SQLException se){
            System.out.println("SQLException: " + se.getMessage());
            se.printStackTrace(System.out);
        }
        
        
        return lista;
    }

    public List<Entrada> listarEntradas(String dni){
        List<Entrada> lista = new ArrayList<Entrada>();
        try{
            Statement st = conn.createStatement();
            String sql = "SELECT * FROM compras WHERE dni='" + dni + "'";
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                Entrada entrada = new Entrada();
                entrada.setId(rs.getInt("idevento"));
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
}
