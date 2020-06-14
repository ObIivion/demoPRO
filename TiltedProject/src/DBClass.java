import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBClass {

    private Connection connect;

    public Connection connect(){

        try {
            connect = DriverManager.getConnection("jdbc:mysql://localhost/demo?serverTimezone=UTC", "root", "Dinler2015");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Нет подключения к базе даннных", "Connection error", JOptionPane.ERROR_MESSAGE );
            e.printStackTrace();
        }
        return connect;
    }



}
