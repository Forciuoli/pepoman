package progetto;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ArchivioProdotti {

	public ArchivioProdotti(){
		
	}
	
	//inserisci prodotti nel database
	  public void addToDatabaseProd(Prodotto a) throws Exception {
		 String nome=a.getNomeP();
		 String descrizione=a.getDescP();
		 String componente=a.getComponente();
		 
		
	    try {
	    	
	    	Class.forName("org.sqlite.JDBC");
	    
	    	connect = DriverManager
	          .getConnection("jdbc:sqlite:magazzino3.db");
	    	System.out.println("Opened database successfully");
	       
	    	statement = connect.createStatement();
	          
	      // resultSet gets the result of the SQL query
	      String sql ="INSERT INTO prodotti (nome, descrizione, componenti)"+"VALUES ('"+nome+"', '"+descrizione+"', '"+componente+"');";
	      
	      statement.executeUpdate(sql);
	      
	      statement.close();
	      
	      connect.close();
	        
	      System.out.println("inserimento avvenuto correttamente");
	      
	    } catch (Exception e) {
	    	System.out.println("errore inserimento dati nel database prodotti");
		      throw e;	      
	    }	
	  }
	    
	  //seleziona componenti dal database e restituisce una stringa cn le info relative
		  public ArrayList<String> selectCompProd() throws Exception{
			  try {
			      // this will load the MySQL driver, each DB has its own driver
			      Class.forName("org.sqlite.JDBC");
			      // setup the connection with the DB.
			      connect = DriverManager
			          .getConnection("jdbc:sqlite:magazzino3.db");

			      //connect.setAutoCommit(false);
			      System.out.println("Opened database successfully");
			      // statements allow to issue SQL queries to the database
			      statement = connect.createStatement();
			      resultSet=statement.executeQuery("select nome,descrizione,componenti from prodotti;");
			      //resultSet = statement.getResultSet();
			      
			      while (resultSet.next()) {
			    	  //il numero nel getString indica la colonna a cui fare riferimento; 1=nome, 2=descrizione, 3=quantità
			    	  //si fa rifrimento alla tabella generata dalla query
			    	  
			    	  String nom1 = resultSet.getString("nome");
			    	  String  desc1 = resultSet.getString("descrizione");
			          String comp1  = resultSet.getString("componenti");
		                
		               System.out.println(nom1+", "+desc1+", componenti: "+comp1+"\n");
		                stringa = nom1+"-"+desc1+"-"+comp1;
		                a.add(stringa);
		                
		            }
			      resultSet.close();
			      statement.close();
			      connect.close();
			      
			      return a;
			  }catch(Exception e){
				  System.out.println("errore select dal database componenti");
				  return null;
				
			  }
		    
		  
	    
	  }
	  
	  private Connection connect;
	  private Statement statement;
	  private PreparedStatement preparedStatement;
	  private ResultSet resultSet;
	  private String stringa="";
	  private ArrayList<String> a=new ArrayList<String>();
	
}
