import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class connectToDB {

	public static void main(String[] args) {

		try {
			Class.forName("org.postgresql.Driver");
			Connection con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","admin");
		PreparedStatement stmt=con.prepareStatement("select * from public.chatbot");
		ResultSet Rs=stmt.executeQuery();
		while(Rs.next())
		{
		System.out.println(Rs.getString(1));
		System.out.println(Rs.getString(2));

		}
		 stmt=con.prepareStatement("SELECT * FROM public.unanswered_questn");
		 Rs=stmt.executeQuery();
		while(Rs.next())
		{
		System.out.println(Rs.getString(1));
		System.out.println(Rs.getString(2));

		}
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

}
