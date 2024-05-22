
package Modelo;

import java.sql.Connection;
    import java.sql.DriverManager;
    import java.sql.*;

public class SqlConexion {
    Connection SQLConexion;
      public SqlConexion(){

                String host = "localhost";
                String puerto="3315";
                String nameDB="colegio";
                String usuario ="root";
                String pass="";

                String driver="com.mysql.cj.jdbc.Driver";

                String databaseURL="jdbc:mysql://" + host+":"+puerto+"/"+nameDB+"?useSSL=false";


                try {
                Class.forName(driver);
                SQLConexion = DriverManager.getConnection(databaseURL,usuario,pass) ;
                    System.out.println("Base de datos Conectado");
            } catch (Exception e) {
            }

        }

        public Connection getConectarBD(){

            return SQLConexion;

        }
}
