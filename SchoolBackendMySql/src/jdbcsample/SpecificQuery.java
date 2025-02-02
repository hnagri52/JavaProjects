package jdbcsample;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import jdbcsample.tables.Students;


public class SpecificQuery {
		
		private static final String sql = "select * from students where class <=?";
		
		public static void specificQuery(String args[]) throws SQLException {
			
			double maxfee;
			
			try {
				maxfee = Input.getInt("Enter a maximum class: ");
			}catch (Exception e) {
				System.err.println("Error: Invalid number");
				return;
			}
			
			ResultSet rs = null;
			try {
				Connection conn = DBConnection.getConnection();
				PreparedStatement state = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				state.setDouble(1, maxfee);
				
				
				
				
				rs = state.executeQuery();
				Students.getStudents(rs);
				
			}catch (Exception e) {
				System.err.println(e);
			}finally {
				if (rs!=null) {
					rs.close();
				}
			}
			
		}

}
