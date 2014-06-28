package progetto;

import java.util.ArrayList;

public class Prodotto {

	public Prodotto(String n,String d,String c){
		nome=n;
		descrizione=d;
		componente=c;
	}
	
	public String getNomeP(){
		return nome;
	}
	public String getDescP(){
		return descrizione;
	}
	public String getComponente(){
		return componente;
	}
	
	private String nome;
	private String descrizione;
	private String componente;
}
