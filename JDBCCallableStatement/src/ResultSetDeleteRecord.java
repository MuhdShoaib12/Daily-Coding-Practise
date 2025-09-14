import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ResultSetDeleteRecord {
    public static void main(String[] args) {
        Connection connection = null;
        Statement stmt = null;
        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver class Loaded...");

            String url = "jdbc:mysql://localhost:3306/VIVO";
            String user = "root";
            String password = "@Shoaib2801";

            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connection is Established...");

            if (connection != null)
                // Expecting ResultSet to be SCROLLABLE AND UPDATABLE
                stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            String sqlQuery = "select id,name,age,address,salary from employee";

            if (stmt != null)
                resultSet = stmt.executeQuery(sqlQuery);

            if (resultSet != null) {
                System.out.println("Records before deletion....");
                System.out.println("ID\tNAME\tAGE\tADDRESS");
                while (resultSet.next()) {
                    System.out.println(resultSet.getInt(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getInt(3)
                            + "\t" + resultSet.getString(4));
                }
                System.out.println();

                // performing delete operation using reusltSet
                resultSet.last();// cursor in last row

                resultSet.deleteRow();//peforming deletion operation

                resultSet.beforeFirst();//taking the cursor to BFR
                System.out.println("Records after deletion....");
                System.out.println("ID\tNAME\tAGE\tADDRESS");
                while (resultSet.next()) {
                    System.out.println(resultSet.getInt(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getInt(3)
                            + "\t" + resultSet.getString(4));
                }
            }
        }catch (Exception e ){
            e.printStackTrace();
        }
    }
}
