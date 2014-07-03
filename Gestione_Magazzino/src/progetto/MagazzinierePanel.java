package progetto;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsConfiguration;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.RootPaneContainer;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;

public class MagazzinierePanel  extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	String stato="nessuno";
	JPanel panel=new JPanel();
	JPanel panel1=new JPanel();
	ListSelectionModel cell;
	JButton listaP=new JButton("Lista Prodotti");
	JButton listaC=new JButton("Lista Componenti");
	JButton creaNP=new JButton("Aggiungi Nuovo Prodotto");
	JButton creaP=new JButton("Crea Prodotto");
	JButton aggiungiC=new JButton("Aggiungi Componente");
	JButton eliminaP=new JButton("Elimina Prodotto");
	JButton eliminaC=new JButton("Elimina Componente");
	JScrollPane scroll;
	JScrollPane scroll1;
	JScrollPane scroll2;
	//JButton rifornisci=new JButton("Rifornisci Componente");	non mi devo dimenticare di implementare questa funzione
	
	TableModelComp model=new TableModelComp();	//modello di tabella x componente
	private JTable table=new JTable(model);		//modello di tabella x component
	TableModelProd model1=new TableModelProd();	//modello di tabella x prodotto
	private JTable table1=new JTable(model1);	//modello di tabella x prodotto
	TableModelCREAprod model2=new TableModelCREAprod();	//modello di tabella x creare prodotto
	private JTable table2=new JTable(model2);	//modello di tabella x creare prodotto
	Font font = new Font("Arial",Font.BOLD,30);
	
	public MagazzinierePanel() throws Exception{
		model=new TableModelComp();		
		setSize(1080, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(true);
		setLocationRelativeTo(null); //schermata al centro dello schermo
		setTitle("Gestione Magazzino");
		table.setFont(new Font("Arial", Font.PLAIN, 15));
		table.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 15));
		table1.setFont(new Font("Arial", Font.PLAIN, 15));
		table1.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 15));
		table2.setFont(new Font("Arial", Font.PLAIN, 15));
		table2.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 15));
		scroll=new JScrollPane(table);
		 table.setPreferredScrollableViewportSize(new Dimension(1000, 500));
		 
		scroll1=new JScrollPane(table1);
		 table1.setPreferredScrollableViewportSize(new Dimension(1000, 500));
		
		scroll2=new JScrollPane(table2);
		 table2.setPreferredScrollableViewportSize(new Dimension(1000, 500));
		 	//--- do i nomi alle colonne della tabella componenti
	        TableColumn nome = table.getColumnModel().getColumn(0);
	        nome.setPreferredWidth(150);
	        TableColumn quantit‡ = table.getColumnModel().getColumn(1);
	        quantit‡.setPreferredWidth(150);	        
	        TableColumn descrizione = table.getColumnModel().getColumn(2);
	        descrizione.setPreferredWidth(150); 
	        table.setFont(font);
	        //---
	        
	        //--- do i nomi alle colonne della tabella prodotti
	        TableColumn nomeP = table1.getColumnModel().getColumn(0);
	        nomeP.setPreferredWidth(150);
	        TableColumn quantit‡P = table1.getColumnModel().getColumn(1);
	        quantit‡P.setPreferredWidth(150);	        
	        TableColumn descrizioneP = table1.getColumnModel().getColumn(2);
	        descrizioneP.setPreferredWidth(150); 
	        //---
//****/*	      
	        //--- do i nomi alle colonne della tabella prodotti
	        //---
	        
	        
	        //***SELEZIONO LA CELLA******
	        table2.setCellSelectionEnabled(true);
	        cell=table2.getSelectionModel();
	        cell.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //MULTIPLE_INTERVAL_SELECTION
	    	
	    	
	        cell.addListSelectionListener(new ListSelectionListener() {
	        
				@Override
				public void valueChanged(ListSelectionEvent e) {
					if(e.getValueIsAdjusting())	// mouse button not released yet
						return;
					TableModelCREAprod d=(TableModelCREAprod) table2.getModel(); //modificato
					
					int row = table2.getSelectedRow();
					if(row < 0)				// true when clearSelection
						return;
					int col = table2.getSelectedColumn();	
						
					if(col < 0)				// true when clearSelection
						return;
					
					boolean riga=(boolean) d.getValueAt(row, 0);
					
					table2.clearSelection();
					if(riga==false && col==0){
						table2.getModel().setValueAt(true, row, 0);
						
					}
					if(riga==true && col==0){
						table2.getModel().setValueAt(false, row, 0);
					}
					String cacca=(String)table2.getModel().getValueAt(row, 3);
					table2.getModel().setValueAt(cacca, row, 3);
					repaint();
					
					

				}
				
			});
	//****/	
		listaP.addActionListener(this);
		listaC.addActionListener(this);
		creaNP.addActionListener(this);
		creaP.addActionListener(this);
		aggiungiC.addActionListener(this);
		eliminaP.addActionListener(this);
		eliminaC.addActionListener(this);
		
		//panel1.add(scroll);
		panel.add(listaP);
		panel.add(listaC);		
		panel.add(creaP);
		panel.add(creaNP);
		panel.add(aggiungiC);
		panel.add(eliminaP);
		panel.add(eliminaC);
		
		
		
		add(panel,BorderLayout.SOUTH);
	}
		
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==listaP){
			if(stato.equals("nessuno")){
				stato="prodotti";
				try {
					model1=new TableModelProd();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	//modello di tabella x componente
				table1=new JTable(model1);	
				table1.setFont(new Font("Arial", Font.PLAIN, 15));
				table1.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 15));
				scroll1=new JScrollPane(table1);
				table1.setPreferredScrollableViewportSize(new Dimension(1000, 500));
				panel1.add(scroll1);
				add(panel1,BorderLayout.NORTH);
				
				add(panel,BorderLayout.SOUTH);
				invalidate();
				validate();
				}
			if(!stato.equals("prodotti") && stato.equals("componenti")){
				stato="prodotti";
				panel1.remove(scroll);
				try {
					model1=new TableModelProd();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	//modello di tabella x componente
				table1=new JTable(model1);	
				table1.setFont(new Font("Arial", Font.PLAIN, 15));
				table1.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 15));
				scroll1=new JScrollPane(table1);
				table1.setPreferredScrollableViewportSize(new Dimension(1000, 500));
				panel1.add(scroll1);
				add(panel1,BorderLayout.NORTH);
				
				add(panel,BorderLayout.SOUTH);
				invalidate();
				validate();
				}
				if(!stato.equals("prodotti") && stato.equals("nuovo")){
					stato="prodotti";
					panel1.remove(scroll2);
					try {
						model1=new TableModelProd();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}	//modello di tabella x componente
					table1=new JTable(model1);	
					table1.setFont(new Font("Arial", Font.PLAIN, 15));
					table1.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 15));
					scroll1=new JScrollPane(table1);
					table1.setPreferredScrollableViewportSize(new Dimension(1000, 500));
					panel1.add(scroll1);
					add(panel1,BorderLayout.NORTH);
					
					add(panel,BorderLayout.SOUTH);
					invalidate();
					validate();
					}
		}
		
		if(e.getSource()==creaP){
			if(stato.equals("nessuno")){
				stato="nuovo";
				try {
					model2=new TableModelCREAprod();	
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	//modello di tabella x componente
				table2=new JTable(model2);	
				table2.setFont(new Font("Arial", Font.PLAIN, 15));
				table2.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 15));
				scroll2=new JScrollPane(table2);
				table2.setPreferredScrollableViewportSize(new Dimension(1000, 500));
				panel1.add(scroll2);
				add(panel1,BorderLayout.NORTH);
				
				add(panel,BorderLayout.SOUTH);
				invalidate();
				validate();
				}
			if(!stato.equals("nuovo") && stato.equals("componenti")){
			stato="nuovo";
			panel1.remove(scroll);
			try {
				model2=new TableModelCREAprod();	
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	//modello di tabella x componente
			table2=new JTable(model2);	
			table2.setFont(new Font("Arial", Font.PLAIN, 15));
			table2.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 15));
			scroll2=new JScrollPane(table2);
			table2.setPreferredScrollableViewportSize(new Dimension(1000, 500));
			panel1.add(scroll2);
			add(panel1,BorderLayout.NORTH);
			
			add(panel,BorderLayout.SOUTH);
			invalidate();
			validate();
			}
			if(!stato.equals("nuovo") && stato.equals("prodotti")){
				stato="nuovo";
				panel1.remove(scroll1);
				try {
					model2=new TableModelCREAprod();	
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	//modello di tabella x componente
				table2=new JTable(model2);	
				table2.setFont(new Font("Arial", Font.PLAIN, 15));
				table2.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 15));
				scroll2=new JScrollPane(table2);
				table2.setPreferredScrollableViewportSize(new Dimension(1000, 500));
				panel1.add(scroll2);
				add(panel1,BorderLayout.NORTH);
				
				add(panel,BorderLayout.SOUTH);
				invalidate();
				validate();
				}
			
		}
		
		if(e.getSource()==listaC){
			if(stato.equals("nessuno")){
				stato="componenti";
				try {
					model=new TableModelComp();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	//modello di tabella x componente
				table=new JTable(model);
				table.setFont(new Font("Arial", Font.PLAIN, 15));
				table.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 15));
				scroll=new JScrollPane(table);
				table.setPreferredScrollableViewportSize(new Dimension(1000, 500));
				panel1.add(scroll);
				add(panel1,BorderLayout.NORTH);
				
				add(panel,BorderLayout.SOUTH);
				invalidate();
				validate();
				}
			if(!stato.equals("componenti") && stato.equals("prodotti")){
			stato="componenti";
			panel1.remove(scroll1);
			try {
				model=new TableModelComp();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	//modello di tabella x componente
			table=new JTable(model);
			table.setFont(new Font("Arial", Font.PLAIN, 15));
			table.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 15));
			scroll=new JScrollPane(table);
			table.setPreferredScrollableViewportSize(new Dimension(1000, 500));
			panel1.add(scroll);
			add(panel1,BorderLayout.NORTH);
			
			add(panel,BorderLayout.SOUTH);
			invalidate();
			validate();
			}
			if(!stato.equals("componenti") && stato.equals("nuovo")){
				stato="componenti";
				panel1.remove(scroll2);
				try {
					model=new TableModelComp();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	//modello di tabella x componente
				table=new JTable(model);
				table.setFont(new Font("Arial", Font.PLAIN, 15));
				table.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 15));
				scroll=new JScrollPane(table);
				table.setPreferredScrollableViewportSize(new Dimension(1000, 500));
				panel1.add(scroll);
				add(panel1,BorderLayout.NORTH);
				
				add(panel,BorderLayout.SOUTH);
				invalidate();
				validate();
				}
		}
		
		if(e.getSource()==aggiungiC){				
			new AddComponentePanel(this).setVisible(true);
				
		}
		if(e.getSource()==creaNP){				
			
			
					try {
						new ProdottoPanel(this).setVisible(true);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			
			
		}
	}
	
	public void m(){
		if(stato.equals("componenti")){
		panel1.remove(scroll);
		try {
			model=new TableModelComp();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	//modello di tabella x componente
		table=new JTable(model);
		table.setFont(new Font("Arial", Font.PLAIN, 15));
		table.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 15));
		scroll=new JScrollPane(table);
		table.setPreferredScrollableViewportSize(new Dimension(1000, 500));
		panel1.add(scroll);
		add(panel1,BorderLayout.NORTH);
		
		add(panel,BorderLayout.SOUTH);
		invalidate();
		validate();
		}
		if(stato.equals("prodotti")){
			panel1.remove(scroll1);
			try {
				model1=new TableModelProd();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	//modello di tabella x componente
			table1=new JTable(model1);	
			table1.setFont(new Font("Arial", Font.PLAIN, 15));
			table1.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 15));
			scroll1=new JScrollPane(table1);
			table1.setPreferredScrollableViewportSize(new Dimension(1000, 500));
			panel1.add(scroll1);
			add(panel1,BorderLayout.NORTH);
			
			add(panel,BorderLayout.SOUTH);
			invalidate();
			validate();
			}
		if(stato.equals("nuovo")){
			panel1.remove(scroll2);
			try {
				model2=new TableModelCREAprod();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	//modello di tabella x componente
			table2=new JTable(model2);	
			table2.setFont(new Font("Arial", Font.PLAIN, 15));
			table2.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 15));
			scroll2=new JScrollPane(table2);
			table2.setPreferredScrollableViewportSize(new Dimension(1000, 500));
			panel1.add(scroll2);
			add(panel1,BorderLayout.NORTH);
			
			add(panel,BorderLayout.SOUTH);
			invalidate();
			validate();
			}
		
	}
	private ArchivioComponenti comp=new ArchivioComponenti();
	private ArrayList<String> s=new ArrayList<String>();
}
