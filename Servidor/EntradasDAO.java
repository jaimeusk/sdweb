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

    public Entrada obtenerEntrada(int idEvento){

        Statement st = conn.createStatement();
        String sql = "SELECT * FROM eventos WHERE id='" + idEvento + "';";
        Resultset rs = st.executeQuery(sql);
        ...
    }
}