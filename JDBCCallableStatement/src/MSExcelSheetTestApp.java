import java.sql.*;

public class MSExcelSheetTestApp{
    public static void main(String[] args) {
        String EXCEL_FILE = "select * from Data.student";
//        String url = "jdbc:Excel://Users/shoaibshah/JAVA/JDBCCallableStatement/Data.xlsx";
        String url = "Users/shoaibshah/JAVA/JDBCCallableStatement/Data.xlsx";
        try (Connection connection = DriverManager.getConnection(url)) {
            try (PreparedStatement pstmt = connection.prepareStatement(EXCEL_FILE)) {
                try (ResultSet resultSet = pstmt.executeQuery()) {
                    while (resultSet.next()) {
                        System.out.println(
                                resultSet.getInt(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getString(3));
                    }
                }
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
