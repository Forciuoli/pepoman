package progetto;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class TableModelComp extends AbstractTableModel{

	private static final long serialVersionUID = 1L;
			public TableModelComp() throws Exception{
				
			    //inserisco i dati nella tabella
			   
			   c=comp.selectComp(); 
			   
			  for(int i=0;i<c.size();i++){	
				 
				  String[] arr = c.get(i).split("/");
				 
				  data[i][0]=arr[0];
				  data[i][1]=arr[1];
				  data[i][2]=arr[2];
				  data[i][3]=arr[3];
				  
			}
			
			
		}
			
			@Override
			public int getColumnCount() {
				 return columnNames.length;
			}
			@Override
			public int getRowCount() {
				return data.length;
			}
			@Override
			public Object getValueAt(int row, int column) {
				return data[row][column];
			}
			 public String getColumnName(int column) {
			        return columnNames[column];
			    }
			
			
	private Object[][] data=new Object[1000][4];
	private ArrayList<String> c;
	private String[] columnNames = {"Nome", "Descrizione", "Quantità", "Codice Materiale" };
	private ArchivioComponenti comp=new ArchivioComponenti();
	
}
