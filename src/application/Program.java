package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DB;

public class Program {

	public static void main(String[] args) {

		//inserindo um vendedor

		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Connection conn = null;
		PreparedStatement st= null;
		
		try {
			conn = DB.getConnection();
			
			st = conn.prepareStatement(
					"INSERT INTO seller "
					+ "(Name, Email, BirthDate, BaseSalary, DepartmentID) "
					+ "VALUES "
					+ "(?, ?, ?, ?, ?)",
					//retornando o id gerado
					Statement.RETURN_GENERATED_KEYS);	
			
			st.setString(1, "Mario");
			st.setString(2, "mario@gmail.com");
			st.setDate(3, new java.sql.Date(sdf.parse("22/02/1985").getTime()));
			st.setDouble(4, 6000.0);
			st.setInt(5, 3);
			
			// retorna um int st.executeUpdate();
			
			int rowsAffected = st.executeUpdate();
						
			//System.out.println("Done! Rows affected: " + rowsAffected);
			
			if (rowsAffected > 0) {
				//pegando o id  linha 33
				st.getGeneratedKeys();
				ResultSet rs = st.getGeneratedKeys();
								
				while (rs.next()) {
					int id = rs.getInt(1);
					System.out.println("Done! Id = " + id);
				}
			}
			else {
				System.out.println("No rows affected!");
			}
			
			
							
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		finally {
			DB.closeStatement(st);
			DB.closeConection();
		}
	}
}
