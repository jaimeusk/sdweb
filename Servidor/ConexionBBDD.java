import java.sql.*;

public class ConexionBBDD {
    
    private Connection conn;

    public ConexionBBDD(){
        try{
            //Carga del driver
            Class.forName("org.postgresql.Driver");
             
            //Definición de la cadena de conexión
            String url = "jdbc:postgresql://85.208.20.152:8083/entradas_db";
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
    }

    public Connection getConnection(){
        return conn;

    }
}
