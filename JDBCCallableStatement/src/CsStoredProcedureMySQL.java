import java.sql.*;
import java.util.Scanner;

public class CsStoredProcedureMySQL {
    private static final String storedProcedureCall = "{CALL P_GET_PRODUCT_DETAILS_BY_ID(?,?,?,?)}";
    public static void main(String[] args) {
        Connection connection = null;
        CallableStatement cstmt = null;
        Scanner scan =  new Scanner(System.in);
        Integer id = null;
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

            if (scan != null) {
                System.out.print("Enter the product id :: ");
                id = scan.nextInt();
            }

            // Setting the input values
            if (cstmt != null) {
                cstmt.setInt(1, id);
            }

            // Setting the Datetype of output values
            if (cstmt != null) {
                cstmt.registerOutParameter(2, Types.VARCHAR);
                cstmt.registerOutParameter(3, Types.INTEGER);
                cstmt.registerOutParameter(4, Types.INTEGER);
            }

            // run the stored procedure
            if (cstmt != null) {
                cstmt.execute();
            }

            // retreive the result
            if (cstmt != null) {
                System.out.println("Product Name is :: " + cstmt.getString(2));
                System.out.println("Product Cost is :: " + cstmt.getInt(3));
                System.out.println("Product QTY  is :: " + cstmt.getInt(4));
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
