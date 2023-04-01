import java.sql.*;
import java.util.*;

class EntradasDAO {

    private Connection conn;

    public EntradasDAO() throws Exception {

        try{
            //Carga del driver
            Class.forName("org.postgresql.Driver");
             
            //Definición de la cadena de conexión
            String url = "jdbc:postgresql://localhost:8001/entradas_bd";
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

        try{

            Statement st = conn.createStatement();
            String sql = "SELECT * FROM eventos WHERE idevento=" + idEvento;
            Resultset rs = st.executeQuery(sql);
            Entrada entrada = new Entrada();

            int id = rs.getInt("idevento");
            String artista = rs.getString("artista");
            int numEntradas_disponibles = rs.getInt("numentradas");
            String fecha = rs.getString("fecha");
            String lugar = rs.getString("lugar");
            String ciudad = rs.getString("ciudad");
        }
        catch(SQLException se){
            System.out.println("SQLException: " + se.getMessage());
            se.printStackTrace(System.out);
        }

        rs.close();
        st.close();
        conn.close();

        if(numEntradas_disponibles<entradasSolicitadas){
            entrada.setNumero(numEntradas_disponibles);  //Hacer una impresion en el cliente informando que no quedan suficientes entradas disponibles
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

        return entrada;
        
    }

    public boolean actualizar(int idEvento,int solicitadas,int disponibles,String nombre,String dni){

        try {

            Statement st = conn.createStatement();
            String sql = "UPDATE eventos SET numentradas=" + disponibles-solicitadas + " WHERE id=" + idEvento;
            String sql2 = "INSERT INTO compras (idevento,nombre,dni,cantidad) VALUES (" + idEvento + ",'" + nombre + "','" + dni + "'," + solicitadas;
            int filas = st.executeUpdate(sql);
            int filas2 = st.executeUpdate(sql2);
        }catch(SQLException se){
            System.out.println("SQLException: " + se.getMessage());
            se.printStackTrace(System.out);
        }

        boolean resultado;
        if(filas>0 && filas2>0){
            resultado = true;
            System.out.println("Tablas eventos y compras actualizadas correctamente\n");
        }
        else{
            resultado = false;
        }
        rs.close();
        st.close();
        conn.close();

        return resultado;

    }

    public boolean borrarEntrada(int idCompra){

        try{
            Statement st = conn.createStatement();
            String sql_select = "SELECT idevento FROM compras WHERE idcompra=" + idCompra;
            String sql_delete = "DELETE FROM compras WHERE idcompra=" + idCompra;
    
            Resultset rs = st.executeQuery(sql_select);
            int idEvento = rs.getInt("idevento");
            int entradas_compradas = rs.getInt("cantidad");
            rs.close();

            String sql_update = "UPDATE eventos SET numentradas=numentradas + " + cantidad + " WHERE idevento=" + idEvento;  //Sería necesario pasar el número de entradas que se quieren cancelar

            int filas_d = st.executeUpdate(sql_delete);
            int filas_u = st.executeUpdate(sql_update);

            st.close();
            boolean resultado;

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
        conn.close()
        return resultado;
    }

    public boolean agregarEvento(Evento evento){
        //Rellenar igual que antes con la sentencia INSERT. Esperar a definir correctamente la BBDD
        try{
            Statement st = conn.createStatement();
            String sql = "INSERT INTO eventos (artista,fecha,lugar,ciudad,numentradas) VALUES (" + evento.getArtista() + "','" + evento.getFecha() + "','" + evento.getLugar() + "','" + evento.getCiudad() + "'," + evento.getEntradas();
            int filas = st.executeUpdate(sql);

            boolean resultado;

            if(filas>0){
                resultado = true;

            }else{
                resultado = false;
            }
        }catch(SQLException se){
            System.out.println("SQLException: " + se.getMessage());
            se.printStackTrace(System.out);
        }
        rs.close();
        st.close();
        conn.close();
    }

    public List<Evento> listarEventos(){
        try{
            Statement st = conn.createStatement();
            String sql = "SELECT * FROM eventos";
            Resultset rs = st.executeQuery(sql);
            List<Evento> lista = new ArrayList<Evento>();
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
        }catch(SQLException se){
            System.out.println("SQLException: " + se.getMessage());
            se.printStackTrace(System.out);
        }
        st.close();
        conn.close();
        return lista;
    }

    public List<Entrada> listarEntradas(String dni){
        try{
            Statement st = conn.createStatement();
            String sql = "SELECT * FROM compras WHERE dni='" + dni + "'";
            Resultset rs = st.executeQuery(sql);
            List<Entrada> lista = new ArrayList<Entrada>();
            while(rs.next()){
                Entrada entrada = new Entrada();
                entrada.setId(rs.getInt("idevento"));
                entrada.setArtista(rs.getString("artista"));
                entrada.setCiudad(rs.getString("ciudad"));
                entrada.setLugar(rs.getString("lugar"));
                entrada.setFecha(rs.getString("fecha"));
                entrada.setNumero(rs.getInt("cantidad"));
                lista.add(entrada);
            }
        }catch(SQLException se){
            System.out.println("SQLException: " + se.getMessage());
            se.printStackTrace(System.out);
        }
        rs.close();
        st.close();
        conn.close();
        return lista;
    }
}
