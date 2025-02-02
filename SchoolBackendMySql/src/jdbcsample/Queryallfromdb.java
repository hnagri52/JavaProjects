package jdbcsample;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import jdbcsample.tables.Students;


public class Queryallfromdb {
	
private static final String sql = "select id,first_name,fee from students where fee<=?";
	
	public static void queryall() throws SQLException {
		
		//Class.forName(com.mysql.jdbc.Driver);
		Connection con = null;
		Statement smth = null;
		ResultSet rs = null;
		
		System.out.println("Hello user");
		try {
			con = DBConnection.getConnection();
			smth = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = smth.executeQuery("select * from students");
			
			Students.getStudents(rs);
			
			
			

			rs.last();
			System.out.println("The last student is: " + rs.getInt("id") + " "  + rs.getString("first_name") + " " + rs.getString("last_name") + " " + rs.getDate("dob") );
			rs.first();
			System.out.println("The first student is: " + rs.getInt("id") + " "  + rs.getString("first_name") + " " + rs.getString("last_name") + " " + rs.getDate("dob") );
			rs.absolute(3);
			System.out.println("The third student is: " + rs.getInt("id") + " "  + rs.getString("first_name") + " " + rs.getString("last_name") + " " + rs.getDate("dob") );

			
			
			
			System.out.println(rs.getRow());	
			System.out.println("Connected!");

		}catch(SQLException e) {
			System.err.println(e);
		}
		finally {
			if(rs != null) {
				rs.close();
			}
			if(smth!=null) {
				smth.close();
			}
			if (con!=null) {
				con.close();
			}
		}
		
		System.out.println("Application is ended");
		
		
	}
	


}
