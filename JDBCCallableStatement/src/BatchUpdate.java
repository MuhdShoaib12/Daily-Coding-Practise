import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class BatchUpdate {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        Scanner scan = null;
        try{
             connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/VIVO", "root", "@Shoaib2801");
            String sqlInsertQuery = "insert into employee(name,age,address) values(?,?,?)";
            if (connection != null)
                pstmt = connection.prepareStatement(sqlInsertQuery);
            if (pstmt != null ){
                scan = new Scanner(System.in);
                while(true){
                    System.out.print("Enter the name :: ");
                    String name = scan.nextLine();
                    System.out.print("Enter the age :: ");
                    int age = scan.nextInt();
                    System.out.print("Enter the address :: ");
                    String address = scan.next();
                    pstmt.setString(1,name);
                    pstmt.setInt(2,age);
                    pstmt.setString(3,address);

                    //Query add to the batch
                    pstmt.addBatch();
                    System.out.println("Do you want to insert one more record[yes/no] :: ");
                    String option = scan.next();
                    if(option.equalsIgnoreCase("no")){
                        break;
                    }
                }
                pstmt.executeBatch();
                System.out.println("Record insert successfully...");
            }
            connection.close();
            pstmt.close();
            scan.close();
        }catch (SQLException se){
            se.printStackTrace();
        }catch (Exception e ){
            e.printStackTrace();
        }
    }
}
