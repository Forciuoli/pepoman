package progetto;


	import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GraphicsConfiguration;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import javax.swing.DefaultCellEditor;

	
public class ProdottoPanel extends JFrame implements ActionListener{
	private static GraphicsConfiguration guiFrame;
	
     JPanel tablepanel=new JPanel();
     JPanel panel=new JPanel();  
     JPanel panel1=new JPanel();   
     JLabel nome1=new JLabel("Nome: ");
     JLabel descrizione1=new JLabel("descrizione: ");
	 JTextField nome= new JTextField(20);
	 JTextArea descrizione= new JTextArea(4, 20);
 
     JButton ok=new JButton("ok");
     
	    public ProdottoPanel() throws Exception
	    {	
	    	super(guiFrame);
	        setTitle("Prodotto");
	        setSize(1000,600);
	      
	        //This will center the JFrame in the middle of the screen
	        setLocationRelativeTo(null);
	        
	        //******SELEZIONO LA CELLA******
	        table.setCellSelectionEnabled(true);
	        table.setRowSorter(null);
	        cell=table.getSelectionModel();
	        cell.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //MULTIPLE_INTERVAL_SELECTION
	        cell.addListSelectionListener(new ListSelectionListener() {
				
				@Override
				public void valueChanged(ListSelectionEvent e) {
					if(e.getValueIsAdjusting())	// mouse button not released yet
						return;
					TableModel d=(TableModel) table.getModel();
					
					int row = table.getSelectedRow();
					if(row < 0)				// true when clearSelection
						return;
					int col = table.getSelectedColumn();	
						
					if(col < 0)				// true when clearSelection
						return;
					
					boolean riga=(boolean) d.getValueAt(row, 0);
					
					table.clearSelection();
					if(riga==false && col==0){
						table.getModel().setValueAt(true, row, 0);
						
					}
					if(riga==true && col==0){
						table.getModel().setValueAt(false, row, 0);
					}
					String cacca=(String)table.getModel().getValueAt(row, 3);
					table.getModel().setValueAt(cacca, row, 3);
					repaint();
					
					

				}
				
			});
	        
	        
	        //Set the column sorting functionality on
	      //  table.setAutoCreateRowSorter(true);
	       
	        //Set the default editor for the Country column to be the combobox
	        TableColumn nome = table.getColumnModel().getColumn(1);
	        nome.setPreferredWidth(150);
	       
	        //set the Event column to be larger than the rest and the Place column 
	        //to be smaller
	        TableColumn quantità = table.getColumnModel().getColumn(3);
	        quantità.setPreferredWidth(150);
	        
	        TableColumn descrizione = table.getColumnModel().getColumn(2);
	        descrizione.setPreferredWidth(150);        
	        
	        //Place the JTable object in a JScrollPane for a scrolling table
	        JScrollPane tableScrollPane = new JScrollPane(table);
	        table.setPreferredScrollableViewportSize(new Dimension(900, 400));
	        
	        ok.addActionListener(this);
	        panel1.add(ok);
	        panel.add(nome1);
	        panel.add(this.nome);
	        panel.add(descrizione1);
	        panel.add(this.descrizione);
	        
	        tablepanel.add(tableScrollPane);
	        
	        add(tablepanel,BorderLayout.NORTH);
	        add(panel,BorderLayout.CENTER);
	        add(panel1,BorderLayout.SOUTH);
	        
	        dispose(); //x chiudere la finestra; oppure setvisible(false);
	      
	    }
	    
	  
		public void actionPerformed(ActionEvent e) {

			if(e.getSource()==ok){
				String nom=(String) nome.getText();
				String desc=(String) descrizione.getText();
				try {
					stringaId=model.getSelezionati();
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				try {
					prod.addToDatabaseProd(new Prodotto(nom, desc, stringaId));
					JOptionPane.showMessageDialog(panel,"Inserimento avvenuto correttamente",
						    "inserimento corretto",
						    JOptionPane.INFORMATION_MESSAGE);
					dispose();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(panel,"errore inserimento",
						    "Campo vuoto",
						    JOptionPane.WARNING_MESSAGE);
				}
				
			}
			
		}
	   
	    TableModel model=new TableModel();
	    private JTable table=new JTable(model);
	    ListSelectionModel cell;
	    private ArrayList<String> id;
	    private String stringaId;
	    ArchivioProdotti prod=new ArchivioProdotti();
	    ArchivioComponenti comp=new ArchivioComponenti();
		
	}


