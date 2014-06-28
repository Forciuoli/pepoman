package progetto;

import java.awt.Cursor;
import java.io.Closeable;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class ArchivioComponenti {
	public ArchivioComponenti(){
		
	}
	//
	
	private Connection connect;
	private Statement statement;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	private String stringa="";
	private ArrayList<String> a=new ArrayList<String>();
	
	
	
	
	
	//crea la tabella
	
	public void creaDatabase(){
		Connection c = null;
	    Statement stmt = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:magazzino1.db");
	      System.out.println("Opened database successfully");

	      stmt = c.createStatement();
	      String sql = "CREATE TABLE componenti " +
	                   "(id INTEGER PRIMARY KEY   AUTOINCREMENT," +
	                   " nome           varchar(50)    NOT NULL, " + 
	                   " descrizione    varchar(500)     NOT NULL, " + 
	                   " quant        	int(20) " + 
	                   " )"; 
	      stmt.executeUpdate(sql);
	      stmt.close();
	      c.commit();
	      c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("Table created successfully");
	  }
	
	
//inserisci componenti nel database
	  public void addToDatabase(Componente a) throws Exception {
		 String nome=a.getNome();
		 String descrizione=a.getDesc();
		 int quant=a.getQuant();
	
	  
	    try {
	     
	    	Class.forName("org.sqlite.JDBC");
	   
	      connect = DriverManager
	          .getConnection("jdbc:sqlite:magazzino2.db");
	     
	      	System.out.println("Opened database successfully");
	         			     
		      statement = connect.createStatement();
		      String sql = "INSERT INTO componenti (nome, descrizione, quant) " +
		                   "VALUES ('"+nome+"', '"+descrizione+"', "+quant+");"; 
		      
		      statement.executeUpdate(sql);
			      
		      statement.close();
		      
		      connect.close();
		        
	    	
	    	 
		    
	      /*
	      // resultSet gets the result of the SQL query
	      String sql ="INSERT INTO componenti (nome, descriione, quantita)"+" VALUES (?,?,?)";
	      
	      preparedStatement = connect.prepareStatement(sql);
	      
	      preparedStatement.setString(1, nome);
	      preparedStatement.setString(2, descrizione);
	      preparedStatement.setInt(3, quant);
	      preparedStatement.executeUpdate(); 
	      
	      System.out.println("inserimento avvenuto correttamente");
	     */
	     
	    } catch (Exception e) {
	    	System.out.println("errore inserimento dati nel database componenti");
		      throw e;
		    
	      
	    }	
	  }
	  
//seleziona componenti dal database e restituisce una stringa cn le info relative
	  public ArrayList<String> selectComp() throws Exception{
		  try {
		      // this will load the MySQL driver, each DB has its own driver
		      Class.forName("org.sqlite.JDBC");
		      // setup the connection with the DB.
		      connect = DriverManager
		          .getConnection("jdbc:sqlite:magazzino2.db");

		      //connect.setAutoCommit(false);
		      System.out.println("Opened database successfully");
		      // statements allow to issue SQL queries to the database
		      statement = connect.createStatement();
		      resultSet=statement.executeQuery("select nome,descrizione,quant from componenti;");
		      //resultSet = statement.getResultSet();
		      
		      while (resultSet.next()) {
		    	  //il numero nel getString indica la colonna a cui fare riferimento; 1=nome, 2=descrizione, 3=quantità
		    	  //si fa rifrimento alla tabella generata dalla query
		    	  
		    	  String nom1 = resultSet.getString("nome");
		          String  desc1 = resultSet.getString("descrizione");
		          int quant1  = resultSet.getInt("quant");
	                
	               System.out.println(nom1+", "+desc1+", "+quant1+"\n");
	                stringa = nom1+"/"+desc1+"/"+quant1;
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
	  
	  
	//*******aggiorna le quantità******
	  
	  //UPDATE Customers
	  //SET ContactName='Alfred Schmidt', City='Hamburg'
	  //WHERE CustomerName='Alfreds Futterkiste';
	  
	  public void aggiornaQuantità(String no,int q) throws Exception{
		  try {
		      // this will load the MySQL driver, each DB has its own driver
			  Class.forName("org.sqlite.JDBC");
			// setup the connection with the DB.
		      connect = DriverManager
		          .getConnection("jdbc:sqlite:magazzino2.db");

		      //connect.setAutoCommit(false);
		      System.out.println("Opened database successfully");
		      // statements allow to issue SQL queries to the database
		      statement = connect.createStatement();
		      resultSet=statement.executeQuery("select quant from componenti where id='"+no+"';");
		     	      
		      while (resultSet.next()) {
		    	  //il numero nel getString indica la colonna a cui fare riferimento; 1=nome, 2=descrizione, 3=quantità
		    	  //si fa rifrimento alla tabella generata dalla query
		    	  
	                int qu = resultSet.getInt("quant");
	                               
	               System.out.println(qu+" ");
	                int res=qu-q;
	                
	                
	            }
		      
		  }catch(Exception e){
			  System.out.println("errore select in 'aggiorna quantità' dal database componenti");
			  
			
		  }
	    }
	  
	  
	//restituisce arraylist cn gli id dei componenti
	  public String selectIdComp(String no) throws Exception{
		  try {
		      // this will load the MySQL driver, each DB has its own driver
		      Class.forName("com.mysql.jdbc.Driver");
		      // setup the connection with the DB.
		      connect = DriverManager
		          .getConnection("jdbc:mysql://localhost/magazzino?"+"user=root&password=");

		      // statements allow to issue SQL queries to the database
		      statement = connect.createStatement();
		      statement.execute("select id from componenti where nome='"+no+"'");
		      resultSet = statement.getResultSet();
		      
		      while (resultSet.next()) {
		    	  //il numero nel getString indica la colonna a cui fare riferimento; 1=nome, 2=descrizione, 3=quantità
		    	  //si fa rifrimento alla tabella generata dalla query
		    	  
	                String nom1 = ""+resultSet.getInt(1);
	                               
	               System.out.println(nom1+" \n");
	                stringa = nom1;
	                
	                
	            }
		      return stringa;
		  }catch(Exception e){
			  System.out.println("errore select dal database componenti");
			  return null;
			
		  }
	    }
}	
