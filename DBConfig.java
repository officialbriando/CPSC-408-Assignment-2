import java.sql.Connection;
import java.sql.DriverManager;

public class DBConfig
{
    private Connection pgSqlConnection = null;

    public static Connection getMySqlConnection()
    {
        Connection mySqlConnection = null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            String connectionUrl = "jdbc:mysql://localhost:3306/studentdb";
            mySqlConnection = DriverManager.getConnection(connectionUrl, "Legaci", "theLegaci0");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }

        return mySqlConnection;
    }
}