package MoviesDetails;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;  
import java.sql.Statement;
import java.util.Scanner;


public class Movies {
	//create New Database
	public static void createNewDatabase() {  
		   
        String url = "jdbc:sqlite:C:/Users/pankaj/eclipse-workspace/MoviesDetails/Movies.db";  
   
        try {  
            Connection conn = DriverManager.getConnection(url);  
            if (conn != null) {  
                DatabaseMetaData meta = conn.getMetaData();  
                System.out.println("The driver name is " + meta.getDriverName());  
                System.out.println("A new database has been created.");  
            }  
   
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  
    }  
	//Connect SQLite
	private Connection connect() {  
        // SQLite connection string  
        String url = "jdbc:sqlite:C://Users//pankaj//eclipse-workspace//MoviesDetails//Movies.db";  
        Connection conn = null;  
        try {  
            conn = DriverManager.getConnection(url);  
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  
        return conn;  
    }  
   //Create Table
	public static void createNewTable() {  
        // SQLite connection string  
        String url = "jdbc:sqlite:C://Users//pankaj//eclipse-workspace//MoviesDetails//Movies.db";  
          
        // SQL statement for creating a new table  
        String sql = "CREATE TABLE IF NOT EXISTS movies (\n" 
        		+ " id integer PRIMARY KEY,\n"  
                + " name varchar(30),\n"
                + " actor varchar(30),\n"
                + " actress varchar(30),\n"
                + " director varchar(30),\n"
                + " yearRelease integer\n" 
                + ");";  
          
        try{  
            Connection conn = DriverManager.getConnection(url);  
            Statement stmt = conn.createStatement();  
            stmt.execute(sql);  
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  
    } 
     	
	//Insert Data into Table
    public void insert(String name,String actor,String actress,String director, int yearRelease) {  
        String sql = "INSERT INTO movies(name,actor,actress,director,yearRelease) VALUES(?,?,?,?,?)";  
   
        try{  
            Connection conn = this.connect();  
            PreparedStatement pstmt = conn.prepareStatement(sql);  
            pstmt.setString(1, name);
            pstmt.setString(2, actor);
            pstmt.setString(3, actress);
            pstmt.setString(4, director);
            pstmt.setLong(5, yearRelease);  
            pstmt.executeUpdate();  
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  
    } 
    
    //Retrieve the Data from a Table
	public void selectAll(){  
        String sql = "SELECT * FROM movies";  
          
        try {  
            Connection conn = this.connect();  
            Statement stmt  = conn.createStatement();  
            ResultSet rs    = stmt.executeQuery(sql);  
              
            // loop through the result set  
            while (rs.next()) {  
                System.out.println(rs.getInt("id") +  "\t" +   
                                   rs.getString("name") + "\t\t" +
                                   rs.getString("actor") + "\t\t" +
                                   rs.getString("actress") + "\t\t" +
                                   rs.getString("director") + "\t" +
                                   rs.getLong("yearRelease"));  
            }  
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  
    }  
	
	public static void main(String[] args) throws SQLException {
		//Take Details from user About Movies
        try (Scanner sc = new Scanner(System.in)) {
			System.out.print("Enter the Movie Name: ");
			String name=sc.nextLine();
			System.out.print("Enter the Leading Actor: ");
			String actor=sc.nextLine();
			System.out.print("Enter the Leading Actress: ");
			String actress=sc.nextLine();
			System.out.print("Enter the Director: ");
			String director=sc.nextLine();
			System.out.print("Enter the YearOfRelease: ");
			int yearRelease=sc.nextInt();
      
			//Database creation
			createNewDatabase(); 
			//Table creation 
			createNewTable();  
			Movies app = new Movies(); //object creation
			System.out.println("S.No.\tMoviesName  LeadingActor  LeadingActress  Director  YearOfRelease");
			//insertion of data
			app.insert(name,actor,actress,director,yearRelease);
			//printing the data
			app.selectAll();
        }
		
	}
}
