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
	      String sql ="INSERT INTO prodotti (nome, descrizione, componenti,quantità)"+"VALUES ('"+nome+"', '"+descrizione+"', '"+componente+"',0);";
	      
	      statement.executeUpdate(sql);
	      
	      statement.close();
	      
	      connect.close();
	        
	      System.out.println("inserimento avvenuto correttamente");
	      
	    } catch (Exception e) {
	    	System.out.println("errore inserimento dati nel database prodotti");
		      throw e;	      
	    }	
	  }
	   //aggiunge il numero di prodotti indicati al database
	  public String addProd(String a) throws Exception {
		  Class.forName("org.sqlite.JDBC");
		    
	    	connect = DriverManager
	          .getConnection("jdbc:sqlite:magazzino3.db");
	    	System.out.println("Opened database successfully");
	    	 //connect.setAutoCommit(false);
		      System.out.println("Opened database successfully");
		  System.out.println("roccoooooo    "+a);
		  String controllo;
		  String mess="";
	     String[] split = a.split("&");
	     	//cicla inserendo un prodotto alla volta
		   for(int k=0;k<Integer.parseInt(split[0]);k++){
		    try {
		    	controllo="entra";
		    	
			      // statements allow to issue SQL queries to the database
			      statement = connect.createStatement();
			      resultSet=statement.executeQuery("select componenti,quantità from prodotti where nome='"+split[1]+"';");
			      resultSet.next();
			    	  //il numero nel getString indica la colonna a cui fare riferimento; 1=nome, 2=descrizione, 3=quantità
			    	  //si fa rifrimento alla tabella generata dalla query
			    	  
			          String comp1  = resultSet.getString("componenti");
			          System.out.println("roccoooooo    "+comp1);
			          int quant  = resultSet.getInt("quantità");
			          String[] arr = comp1.split("/");
			          //controllo se ci sono abbastanza componenti
			          for(int i=0;i<arr.length;i++){
			        	  System.out.println("arrrrrrr "+arr[i]);
			        	  String[] arr1 = arr[i].split("&");
			        	  statement = connect.createStatement();
					      resultSet1=statement.executeQuery("select nome,quant from componenti where id='"+arr1[1]+"';");
					      resultSet1.next();
			    		  int q=resultSet1.getInt("quant");
			    		  if(q<Integer.parseInt(arr1[0])){
			    			  controllo="non";
			    		  }
			    			 
			    		  
			          }
			          
			          resultSet.close();
			          resultSet1.close();
			          statement.close();
			          if(controllo.equals("non"))
			        	  break;
			          if(controllo.equals("entra")){
			        	  //decremento la quantità dei componenti
			          for(int i=0;i<arr.length;i++){
			        	  System.out.println("arrrrrrr "+arr[i]);
			        	  String[] arr1 = arr[i].split("&");
			        	  statement = connect.createStatement();
					      resultSet1=statement.executeQuery("select nome,quant from componenti where id='"+arr1[1]+"';");
					      resultSet1.next();
			    		  int q=resultSet1.getInt("quant");
			    		  q-=Integer.parseInt(arr1[0]);
			    		  statement = connect.createStatement();
				          
			    	      // resultSet gets the result of the SQL query
			    	      String sql ="UPDATE componenti SET quant="+q+" WHERE id='"+arr1[1]+"';";
			    	      
			    	      statement.executeUpdate(sql);
			    	      
			    	      statement.close();
			    	      statement = connect.createStatement();
				          
			    	      // resultSet gets the result of the SQL query
			    	      sql ="UPDATE prodotti SET quantità="+(quant+1)+" WHERE nome='"+split[1]+"';";
			    	      
			    	      statement.executeUpdate(sql);
			    	      
			    	      statement.close();
			    	      resultSet1.close();
			    		  
			    	}
		                
		            }
			      
		     
		      
			      resultSet.close();
			     
		       mess="Ho inserito "+(k+1)+" "+split[1];
		      System.out.println("inserimento avvenuto correttamente");
		      System.out.println(mess);
		      
		    } catch (Exception e) {
		    	System.out.println("errore inserimento dati nel database prodotti");
			      throw e;	      
		    }
			}
			return mess;
		  }
	  
	//metodo che elimina il componente 
	  public void EliminaProd(String no) throws Exception{
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
		      statement.executeUpdate("delete from prodotti where nome='"+no+"';");
		      System.out.println("Prodotto eliminato correttamente");
		      statement.close();	      
		      connect.close();
		      
		  }catch(Exception e){
			  System.out.println("errore select in 'elimina prodotto' dal database componenti");
			  e.printStackTrace();
			
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
			      resultSet=statement.executeQuery("select nome,descrizione,componenti,quantità from prodotti;");
			      //resultSet = statement.getResultSet();
			      
			      while (resultSet.next()) {
			    	  //il numero nel getString indica la colonna a cui fare riferimento; 1=nome, 2=descrizione, 3=quantità
			    	  //si fa rifrimento alla tabella generata dalla query
			    	  
			    	  String nom1 = resultSet.getString("nome");
			    	  String  desc1 = resultSet.getString("descrizione");
			          String comp1  = resultSet.getString("componenti");
			          int quant=resultSet.getInt("quantità");
			          String[] arr = comp1.split("&");
			          System.out.println("componenti: "+arr[1]+"\n");
		               System.out.println(nom1+", "+desc1+", componenti: "+arr[1]+"\n");
		                stringa = nom1+"-"+desc1+"-"+arr[1]+"-"+quant;
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
	  private ResultSet resultSet, resultSet1;
	  private String stringa="";
	  private ArrayList<String> a=new ArrayList<String>();
	
}
