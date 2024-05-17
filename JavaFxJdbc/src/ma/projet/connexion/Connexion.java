package ma.projet.connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {
    private static final String URL = "jdbc:mysql://localhost:3306/demojdbctp4";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private static Connection connection ;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
           
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connexion réussie !");
        } catch (ClassNotFoundException e) {
            System.err.println("Erreur de chargement du driver : " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Erreur de connexion à la base de données : " + e.getMessage());
        }
    }

    public static Connection getCn() {
        return connection;
    }
}


