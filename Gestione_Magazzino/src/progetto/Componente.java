package progetto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Componente implements Utilizzabile{
	public Componente(String n, String d, int q){
		nome=n;
		descrizione=d;
		quantità=q;
		//this.id=id;
	}
	
	public String getNome(){
		return nome;
	}
	public String getDesc(){
		return descrizione;
	}
	public int getQuant(){
		return quantità;
	}
	public int getId(){
		return id;
	}
	
//controlla se la quantità del componente è >0 , in tal caso permette di usarlo altrimenti ritorna false
//verificare il funzionamento!
	public boolean eUsabile() {
		  
		try {
		      // this will load the MySQL driver, each DB has its own driver
		      Class.forName("com.mysql.jdbc.Driver");
		      // setup the connection with the DB.
		      connect = DriverManager
		          .getConnection("jdbc:mysql://localhost/magazzino?"+"user=root&password=");

		      // statements allow to issue SQL queries to the database
		      statement = connect.createStatement();
		      statement.execute("select quantita from componenti where nome='"+ nome +"'");
		      resultSet = statement.getResultSet();
		      
		      while (resultSet.next()) {
		    	  //il numero nel getString indica la colonna a cui fare riferimento; 1=nome, 2=descrizione, 3=quantità
		    	  //si fa rifrimento alla tabella generata dalla query
		    	  
	                q = resultSet.getString(1);
	               	               
	               }
		      
		  }catch(Exception e){
			  System.out.println("errore select dal database componenti");
			  try {
				throw e;
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		  }
		
		int quantit = Integer.parseInt(q);
		
		if( quantit>1){
			return true;
		}else{
			return false;
		}
		
	}
	
	private String nome;
	private String descrizione;
	private int quantità;
	private int id;
	private Connection connect;
	private Statement statement;
	private ResultSet resultSet;
	private String q;
	
}
