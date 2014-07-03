package progetto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;



public class Tester {
	public static void main(String[] args) throws Exception{
			
		
		
	/*	Connection c = null;
	    Statement stmt = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:magazzino3.db");
	      System.out.println("Opened magazzino2.db database successfully");

	      stmt = c.createStatement();
	      String sql = "CREATE TABLE componenti " +
	                   "(id INTEGER PRIMARY KEY   AUTOINCREMENT," +
	                   " nome           varchar(50)    NOT NULL, " + 
	                   " descrizione    varchar(500)     NOT NULL, " + 
	                   " quant        	int(20), " + 
	                   " cod_materiale  varchar(500)        NOT NULL" +
	                   " )"; 
	      stmt.executeUpdate(sql);
	      stmt.close();
	      //c.commit();
	      c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("Table componenti created successfully");
	    
	    try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection("jdbc:sqlite:magazzino3.db");
		      System.out.println("Opened magazzino2.db database successfully");

		      stmt = c.createStatement();
		      String sql = "CREATE TABLE prodotti " +
		                   "(id INTEGER PRIMARY KEY   AUTOINCREMENT," +
		                   " nome           varchar(50)  NOT NULL, " + 
		                   " descrizione    varchar(500) NOT NULL, " + 
		                   " componenti     varchar(500) NOT NULL " + 
		                   " )"; 
		      stmt.executeUpdate(sql);
		      stmt.close();
		      //c.commit();
		      c.close();
		    } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		    }
		    System.out.println("Table prodotti created successfully");*/
	   	
		   //CAZZZZZZZZZ
		    
		MagazzinierePanel magazzinierePanel=new MagazzinierePanel();
		magazzinierePanel.setVisible(true);
		
		
		
		//ProdottoPanel a=new ProdottoPanel();
		//a.setvi
	}
	
}
