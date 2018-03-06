import java.util.Scanner;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class SQLStmts
{
    static Connection con = DBConfig.getMySqlConnection();
    static Scanner keyboard = new Scanner(System.in);
    static String foo;

    public static void SQLQuery()
    {
        try
        {
            PreparedStatement pstQuery = con.prepareStatement("SELECT * from student;");
            ResultSet rsQuery = pstQuery.executeQuery();

            while (rsQuery.next()) {
                System.out.println("StudentID: " + rsQuery.getInt("StudentID") +
                        ", FirstName: " + rsQuery.getString("FirstName") +
                        ", LastName: " + rsQuery.getString("LastName") +
                        ", GPA: " + rsQuery.getDouble("GPA") +
                        ", Major: " + rsQuery.getString("Major") +
                        ", FacultyAdvisor: " + rsQuery.getString("FacultyAdvisor"));
            }
        }
        catch(Exception e) {
            e.printStackTrace(); }
    }

    public static void SQLInsert()
    {
        try
        {
            PreparedStatement pstInsert = con.prepareStatement("INSERT INTO student(FirstName, LastName, GPA, Major, FacultyAdvisor) " +
                                                                    "VALUES(?,?,?,?,?);");

            pstInsert.clearParameters();
            keyboard.nextLine();
            System.out.print("\n Enter the student's FIRST NAME: ");
            pstInsert.setString(1, keyboard.nextLine());
            System.out.print("\n Enter the student's LAST NAME: ");
            pstInsert.setString(2, keyboard.nextLine());
            System.out.print("\n Enter the student's GPA: ");
            pstInsert.setDouble(3, keyboard.nextDouble());
            keyboard.nextLine();
            System.out.print("\n Enter the student's MAJOR: ");
            pstInsert.setString(4, keyboard.nextLine());
            System.out.print("\n Enter the student's FACULTY ADVISOR: ");
            pstInsert.setString(5, keyboard.nextLine());

            pstInsert.executeUpdate();
            System.out.println("A new entry has been made for your student. \n");
        }
        catch(Exception e) {
            e.printStackTrace(); }
    }

    public static void SQLUpdate()
    {
        try
        {
            System.out.print("Enter the ID of the student you wish to update: ");
            int studentID = keyboard.nextInt();

            System.out.println("What would you like to update?");
            System.out.println("1. MAJOR");
            System.out.println("2. ADVISOR");

            int userChoice = keyboard.nextInt();
            switch(userChoice)
            {
                case 1:
                    PreparedStatement pstUpdateMajor = con.prepareStatement("UPDATE student SET Major = ? WHERE StudentID = ?");
                    pstUpdateMajor.clearParameters();
                    pstUpdateMajor.setInt(2, studentID);

                    System.out.print("\n Enter the student's updated MAJOR: ");
                    keyboard.nextLine();
                    pstUpdateMajor.setString(1, keyboard.nextLine());
                    pstUpdateMajor.executeUpdate();
                    break;
                case 2:
                    PreparedStatement pstUpdateAdvisor = con.prepareStatement("UPDATE student SET FacultyAdvisor = ? WHERE StudentID = ?");
                    pstUpdateAdvisor.clearParameters();
                    pstUpdateAdvisor.setInt(2, studentID);

                    System.out.print("\n Enter the student's updated FACULTY ADVISOR: ");
                    keyboard.nextLine();
                    pstUpdateAdvisor.setString(1, keyboard.nextLine());
                    pstUpdateAdvisor.executeUpdate();
                    break;

            }

            System.out.println("The student has been updated. \n");
        }
        catch(Exception e) {
            e.printStackTrace(); }
    }

    public static void SQLDelete()
    {
        try
        {
            System.out.print("Enter the ID of the student you wish to delete: ");
            int studentID = keyboard.nextInt();

            PreparedStatement pstDelete = con.prepareStatement("DELETE FROM student WHERE StudentID = ?;");
            pstDelete.clearParameters();
            pstDelete.setInt(1, studentID);
            pstDelete.executeUpdate();

            System.out.println("The student with your given ID has been deleted.\n");
        }
        catch(Exception e) {
            e.printStackTrace(); }
    }

    public static void SQLDisplayBy()
    {
        try
        {
            System.out.println("What would you like to display students by?");
            System.out.println("1. GPA");
            System.out.println("2. MAJOR");
            System.out.println("3. FACULTY ADVISOR");
            int userChoice = keyboard.nextInt();

            switch(userChoice)
            {
                case 1:
                    PreparedStatement QueryGPA = con.prepareStatement("SELECT * FROM student WHERE GPA = ?;");
                    QueryGPA.clearParameters();
                    System.out.print("\n Enter the GPA to display: ");
                    QueryGPA.setDouble(1, keyboard.nextDouble());

                    System.out.println("Here are the entries that match your given input: \n");

                    ResultSet rsGPA = QueryGPA.executeQuery();
                    while(rsGPA.next())
                    {
                        System.out.println("StudentID: " + rsGPA.getInt("StudentID") +
                                ", FirstName: " + rsGPA.getString("FirstName") +
                                ", LastName: " + rsGPA.getString("LastName") +
                                ", GPA: " + rsGPA.getDouble("GPA") +
                                ", Major: " + rsGPA.getString("Major") +
                                ", FacultyAdvisor: " + rsGPA.getString("FacultyAdvisor"));
                    }
                    break;
                case 2:
                    PreparedStatement QueryMAJOR = con.prepareStatement("SELECT * FROM student WHERE Major = ?;");
                    QueryMAJOR.clearParameters();
                    System.out.print("\n Enter the MAJOR to display: ");
                    keyboard.nextLine();
                    QueryMAJOR.setString(1, keyboard.nextLine());

                    System.out.println("Here are the entries that match your given input: \n");

                    ResultSet rsMAJOR = QueryMAJOR.executeQuery();
                    while(rsMAJOR.next())
                    {
                        System.out.println("StudentID: " + rsMAJOR.getInt("StudentID") +
                                ", FirstName: " + rsMAJOR.getString("FirstName") +
                                ", LastName: " + rsMAJOR.getString("LastName") +
                                ", GPA: " + rsMAJOR.getDouble("GPA") +
                                ", Major: " + rsMAJOR.getString("Major") +
                                ", FacultyAdvisor: " + rsMAJOR.getString("FacultyAdvisor"));
                    }
                    break;
                case 3:
                    PreparedStatement QueryADVISOR = con.prepareStatement("SELECT * FROM student WHERE FacultyAdvisor = ?;");
                    QueryADVISOR.clearParameters();
                    System.out.print("\n Enter the FACULTY ADVISOR to display: ");
                    keyboard.nextLine();
                    QueryADVISOR.setString(1, keyboard.nextLine());

                    System.out.println("Here are the entries that match your given input: \n");

                    ResultSet rsADVISOR = QueryADVISOR.executeQuery();
                    while(rsADVISOR.next())
                    {
                        System.out.println("StudentID: " + rsADVISOR.getInt("StudentID") +
                                ", FirstName: " + rsADVISOR.getString("FirstName") +
                                ", LastName: " + rsADVISOR.getString("LastName") +
                                ", GPA: " + rsADVISOR.getDouble("GPA") +
                                ", Major: " + rsADVISOR.getString("Major") +
                                ", FacultyAdvisor: " + rsADVISOR.getString("FacultyAdvisor"));
                    }
                    break;
            }
        }
        catch(Exception e) {
            e.printStackTrace(); }
    }
}
