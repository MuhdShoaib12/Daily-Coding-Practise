import java.sql.*;
import java.util.Scanner;

public class CallingBYProductName{
        private static final String storedProcedureCall = "{CALL P_GET_PRODUCT_DETAILS_BY_NAME(?,?)}";
        public static void main(String[] args) {
            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/VIVO", "root", "@Shoaib2801");
                 Scanner scan = new Scanner(System.in)) {

                Class.forName("com.mysql.cj.jdbc.Driver");
                System.out.println("Driver class Loaded...");
                System.out.println("Connection is Established...");

                try (CallableStatement cstmt = connection.prepareCall(storedProcedureCall)) {
                    System.out.print("Enter the product name :: ");
                    String prod1 = scan.next();

                    System.out.print("Enter the product name :: ");
                    String prod2 = scan.next();

                    // Setting the input values
                    cstmt.setString(1, prod1);
                    cstmt.setString(2, prod2);

                    // Run the stored procedure
                    boolean flag = false;
                    try (ResultSet resultSet = cstmt.executeQuery()) {
                        System.out.println("PID\tPNAME\tPCOST\tQTY");
                        while (resultSet.next()) {
                            System.out.println(resultSet.getInt(1) + "\t" + resultSet.getString(2) + "\t" +
                                    resultSet.getInt(3) + "\t" + resultSet.getInt(4));
                            flag = true;
                        }
                    }

                    // Displaying the nature of the result
                    if (flag) {
                        System.out.println("Record available and displayed");
                    } else {
                        System.out.println("Record not available");
                    }
                }
            } catch (ClassNotFoundException cnfe) {
                System.err.println("JDBC Driver not found: " + cnfe.getMessage());
            } catch (SQLException se) {
                System.err.println("SQL Exception: " + se.getMessage());
            } catch (Exception e) {
                System.err.println("Unexpected Exception: " + e.getMessage());
            }
        }

}
