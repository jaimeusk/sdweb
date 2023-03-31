import java.sql.*;

class EntradasDAO {

    private Connection conn;

    public EntradasDAO() throws Exception {

        try{
            //Carga del driver
            Class.forName("org.postgresql.Driver");
             
            //Definición de la cadena de conexión
            String url = "jdbc:postgresql://localhost:5432/dit";
            String user = "dit";
            String pass = "dit";

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

    public Entrada obtenerEntrada(int idEvento,int numero_solicitadas){

        try{

            Statement st = conn.createStatement();
            String sql = "SELECT * FROM eventos WHERE id=" + idEvento + ";";
            Resultset rs = st.executeQuery(sql);
            Entrada entrada = new Entrada();

            int id = rs.getInt("id");
            String artista = rs.getString("Artista");
            int numEntradas_disponibles = rs.getInt("Entradas");
            String fecha = rs.getString("Fecha");
            String lugar = rs.getString("Lugar");
            String ciudad = rs.getString("Ciudad");
        }
        catch(SQLException se){
            System.out.println("SQLException: " + se.getMessage());
            se.printStackTrace(System.out);
        }

        rs.close();
        st.close();
        conn.close();

        if(numEntradas_disponibles<numero_solicitadas){
            entrada.setNumero(numEntradas_disponibles);  //Hacer una impresion en el cliente informando que no quedan suficientes entradas disponibles
        }
        else{
            boolean actualizar = actualizarEntradas(idEvento,numero_solicitadas,numEntradas_disponibles); //Actualizamos el número de entradas para el evento del que se han comprado
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

    public boolean actualizarEntradas(int idEvento,int solicitadas,int disponibles){

        try {

            Statement st = conn.createStatement();
            String sql = "UPDATE eventos SET Entradas=" + disponibles-solicitadas + " WHERE id=" + idEvento + ";";
            int filas = st.executeUpdate(sql);
        }catch(SQLException se){
            System.out.println("SQLException: " + se.getMessage());
            se.printStackTrace(System.out);
        }

        boolean resultado;
        if(filas>0){
            resultado = true;
            System.out.println("Tabla eventos actualizada correctamente\n");
        }
        else{
            resultado = false;
        }
        rs.close();
        st.close();
        conn.close();

        return resultado;

    }

    public boolean borrarEntrada(int idEntrada){

        try{
            Statement st = conn.createStatement();
            String sql_select = "SELECT idEvento FROM compradas WHERE idEntrada=" + idEntrada + ";";
            String sql_delete = "DELETE FROM compradas WHERE id=" + idEntrada +";";
    
            Resultset rs = st.executeQuery(sql_select);
            int idEvento = rs.getInt("idEvento");
            rs.close();

            String sql_update = "UPDATE eventos SET Entradas=Entradas + 1 WHERE id=" + idEvento;  //Sería necesario pasar el número de entradas que se quieren cancelar

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

    public boolean agregarEvento(){
        //Rellenar igual que antes con la sentencia INSERT. Esperar a definir correctamente la BBDD
    }
}