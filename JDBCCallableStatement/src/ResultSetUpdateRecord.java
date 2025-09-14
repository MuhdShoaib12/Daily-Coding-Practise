import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ResultSetUpdateRecord {
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
                System.out.println("Records before updation....");
                System.out.println("ID\tNAME\tAGE\tADDRESS\tSALARY");
                while (resultSet.next()) {
                    System.out.println(resultSet.getInt(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getInt(3)
                            + "\t" + resultSet.getString(4) + "\t" + resultSet.getInt(5));
                }

                //performing update operation
                resultSet.beforeFirst();
                while (resultSet.next()) {
                    int originalSalary = resultSet.getInt(5);
                    if (originalSalary < 10000) {
                        int incrSalary = originalSalary + 1000;
                        resultSet.updateInt(5, incrSalary);
                        resultSet.updateRow();

                    }
                }

                System.out.println();
                resultSet.beforeFirst();// taking the cursor to BFR
                System.out.println("Records after updation....");
                System.out.println("ID\tNAME\tAGE\tADDRESS");
                while (resultSet.next()) {
                    System.out.println(resultSet.getInt(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getInt(3)
                            + "\t" + resultSet.getString(4) + "\t" + resultSet.getInt(5));
                }
            }
            connection.close();
            stmt.close();
            resultSet.close();
        }catch (Exception e ){
            e.printStackTrace();
        }
    }
}
