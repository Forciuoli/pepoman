package progetto;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
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
	private JTable table=new JTable(model);		//modello di tabella x componente
	TableModelProd model1=new TableModelProd();	//modello di tabella x prodotto
	private JTable table1=new JTable(model1);	//modello di tabella x prodotto
	TableModelCREAprod model2=new TableModelCREAprod();	//modello di tabella x creare prodotto
	private JTable table2=new JTable(model2);	//modello di tabella x creare prodotto


	public MagazzinierePanel() throws Exception{
		
		model=new TableModelComp();		
		setSize(1080, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(true);
		setLocationRelativeTo(null); //schermata al centro dello schermo
		setTitle("Gestione Magazzino");
		
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
	        TableColumn nomecP = table2.getColumnModel().getColumn(1);
	        nomecP.setPreferredWidth(150);
	        TableColumn quantit‡cP = table2.getColumnModel().getColumn(2);
	        quantit‡cP.setPreferredWidth(150);	        
	        TableColumn descrizionecP = table2.getColumnModel().getColumn(3);
	        descrizionecP.setPreferredWidth(150); 
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
			panel1.remove(scroll);
			panel1.add(scroll1);
			add(panel1,BorderLayout.NORTH);
			
			add(panel,BorderLayout.SOUTH);
			invalidate();
			validate();
			
		}
		
		if(e.getSource()==creaP){				
			//panel1.remove(scroll);
			panel1.add(scroll2);
			add(panel1,BorderLayout.NORTH);
			
			add(panel,BorderLayout.SOUTH);
			invalidate();
			validate();
		}
		
		if(e.getSource()==listaC){	
			panel1.remove(scroll1);
			panel1.add(scroll);
			add(panel1,BorderLayout.NORTH);
			
			add(panel,BorderLayout.SOUTH);
			invalidate();
			validate();
		}
		
		if(e.getSource()==aggiungiC){				
			new AddComponentePanel().setVisible(true);
		}
		if(e.getSource()==creaNP){				
			
			
					try {
						new ProdottoPanel().setVisible(true);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			
			
		}
	}
	
	 
	private ArchivioComponenti comp=new ArchivioComponenti();
	private ArrayList<String> s=new ArrayList<String>();
}
