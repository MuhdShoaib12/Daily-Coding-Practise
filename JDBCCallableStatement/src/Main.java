import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception{
        Connection connection = null;
        Statement  stmt = null;
        ResultSet resultSet = null;
        ResultSet rs = null;
        Scanner scan = null;

        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Driver class Loaded...");

        String url = "jdbc:mysql://localhost:3306/VIVO";
        String user = "root";
        String password = "@Shoaib2801";

        connection = DriverManager.getConnection(url, user, password);
        System.out.println("Connection is Established...");

        if(connection != null)
            stmt = connection.createStatement();
        if(stmt != null)
            resultSet = stmt.executeQuery("select name,balance from accounts");
        if(resultSet != null){
            System.out.println("NAME\tBALANCE");
            while(resultSet.next()){
                System.out.println(resultSet.getString(1) + "\t" + resultSet.getInt(2));
            }
        }
        // Transaction begins
        System.out.println(" Transaction begins.....");
        connection.setAutoCommit(false);

        // Prepare the operations as a single unit
        stmt.executeUpdate("update accounts set balance = balance+200 where name = 'Asif'");
        stmt.executeUpdate("update accounts set balance = balance-200 where name = 'Aman'");

        System.out.print("Can u please confirm the transaction  of 200INR...[YES/NO]");
        scan = new Scanner(System.in);
        String option = scan.next();
        if (option.equalsIgnoreCase("yes")) {
            connection.commit();
        } else {
            connection.rollback();
        }

        System.out.println("Data after transaction....");
        rs = stmt.executeQuery("select name,balance from accounts");
        if (rs != null) {
            System.out.println("NAME\tBALANCE");
            while (rs.next()) {
                System.out.println(rs.getString(1) + "\t" + rs.getInt(2));
            }
        }

    }
}