import java.sql.*;
import java.util.Scanner;

public class CsStoredProcedureByName{
    private static final String storedProcedureCall = "{CALL P_GET_PRODUCT_DETAILS_BY_NAME(?,?)}";
    public static void main(String[] args) {
        Connection connection = null;
        CallableStatement cstmt = null;
        ResultSet resultSet = null;

        Scanner scan = null;
        String prod1 = null;
        String prod2 = null;
        boolean flag = false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver class Loaded...");

            String url = "jdbc:mysql://localhost:3306/VIVO";
            String user = "root";
            String password = "@Shoaib2801";

            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connection is Established...");

            if (connection != null)
                cstmt = connection.prepareCall(storedProcedureCall);

            scan = new Scanner(System.in);
            if (scan != null) {
                System.out.print("Enter the product name :: ");
                prod1 = scan.next();

                System.out.print("Enter the product name :: ");
                prod2 = scan.next();
            }

            // Setting the input values
            if (cstmt != null) {
                cstmt.setString(1, prod1);
                cstmt.setString(2, prod2);
            }

            // run the stored procedure
            if (cstmt != null) {
                cstmt.execute();
            }

            // retreive the result
            if (cstmt != null) {
                resultSet = cstmt.getResultSet();
            }

            // process the resultSet
            if (resultSet != null) {
                System.out.println("PID\tPNAME\tPCOST\tQTY");
                while (resultSet.next()) {
                    System.out.println(resultSet.getInt(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getInt(3)
                            + "\t" + resultSet.getInt(4));
                    flag  =true;
                }
            }
            //Displaying the nature of the result
            if (flag) {
                System.out.println("Record available and displayed");
            } else {
                System.out.println("Record not available");
            }

            connection.close();
            cstmt.close();
            scan.close();
        }catch (ClassNotFoundException cnfe){
            cnfe.printStackTrace();
        }catch (SQLException se){
            se.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
