package progetto;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class TableModelProd extends AbstractTableModel{

	private static final long serialVersionUID = 1L;
			public TableModelProd() throws Exception{
				
			    //inserisco i dati nella tabella
			   
			   c=comp.selectCompProd();
			   
			  for(int i=0;i<c.size();i++){	
				 
				  String[] arr = c.get(i).split("-");
				 
				  data[i][0]=arr[0];
				  data[i][1]=arr[1];
				  data[i][2]=arr[2];
				  
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
	private String[] columnNames = {"Nome", "Descrizione", "Componenti" };
	private ArchivioProdotti comp=new ArchivioProdotti();
	
}

