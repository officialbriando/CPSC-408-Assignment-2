import java.util.Scanner;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DatabaseMetaData;

public class Main
{
    public static void displayDBProperties(Connection con)
    {
        DatabaseMetaData dm;
        ResultSet rs = null;

        try
        {
            if(con != null)
            {
                dm = con.getMetaData();
                System.out.println("Driver Information");
                System.out.println("\tDriver Name: " + dm.getDriverName());
                System.out.println("\tDriver Version: " + dm.getDriverVersion());
                System.out.println("\nDatabase Information ");
                System.out.println("\tDatabase Name: " + dm.getDatabaseProductName());
                System.out.println("\tDatabase Version: " + dm.getDatabaseProductVersion());
                System.out.println("Available Catalogs ");
                rs = dm.getCatalogs();
            }

            while(rs.next())
            {
                System.out.println("\tcatalog: " + rs.getString(1));
            }

            rs.close();
            con.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] argv)
    {
        Scanner keyboard = new Scanner(System.in);
        Connection con = null;

        try
        {
            con = DBConfig.getMySqlConnection();
            System.out.println("CONNECTION TO SQL SERVER SUCCESSFUL.");
            displayDBProperties(con);

            if(con.isClosed())
            {
                System.out.println("Connection closed; creating new connection to MySQL.");
                con = DBConfig.getMySqlConnection();
            }

            System.out.println("You are now connected to the student database. You may do any of the following: ");
            System.out.println("1. Display all students and their information.");
            System.out.println("2. Create a new student.");
            System.out.println("3. Update a student's MAJOR or ADVISOR.");
            System.out.println("4. Delete a student.");
            System.out.println("5. Display students by MAJOR, GPA, or ADVISOR.");
            System.out.print("Please pick an action to perform: ");

            while(keyboard.hasNextInt() == false)
            {
                System.out.print("That is not a valid option, try again: ");
            }
            int userChoice = keyboard.nextInt();

            switch(userChoice){

                case 1:
                    SQLStmts.SQLQuery();
                    break;
                case 2:
                    SQLStmts.SQLInsert();
                    break;
                case 3:
                    SQLStmts.SQLUpdate();
                    break;
                case 4:
                    SQLStmts.SQLDelete();
                    break;
                case 5:
                    SQLStmts.SQLDisplayBy();
            }
        }



        catch(Exception ex) {
            ex.printStackTrace(); }
        finally {
            try {
                if(con != null) con.close(); }
            catch(Exception ex) {
                ex.printStackTrace(); }
        }
    }
}