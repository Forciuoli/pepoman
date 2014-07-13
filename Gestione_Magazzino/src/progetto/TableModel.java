package progetto;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.table.AbstractTableModel;

public  class TableModel extends AbstractTableModel{
	
	
    
	public TableModel() throws Exception{
		
	    //inserisco i dati nella tabella
	   
	   c=comp.selectComp(); 
	   
	  for(int i=0;i<c.size();i++){	
		 
		  String[] arr = c.get(i).split("/");
		  data[i][0]=false;
		  data[i][1]=arr[0];
		  data[i][2]=arr[1];
		  data[i][3]="";
		  
	}
	
	
   
	  
 }
    
	
	public void aggiornaTable(int r,int col,boolean f) throws Exception{
		
			data[r][0]=f;
			
	}
    
	public String getSelezionati() throws Exception{
		for(int i=0;i<c.size();i++){	
			//System.out.println(i+" fanculo ");
			    String app="";
				boolean str=(boolean) getValueAt(i, 0);
				// in questa variabile c'è il quantitativo inserito dell'utente
				if(str==true){
						String str1=(String) getValueAt(i, 1);
						String quant=(String) getValueAt(i, 3);
					    	app=quant+"&"+comp.selectIdComp(str1);
					    	System.out.println("weeeee"+app);
							stringaId+=app+"/";
						    app="";
						//System.out.println(str1+"nome ");
						//System.out.println(cacca+" caccaaaaaaa");
						
				}
				
		}
		System.out.println("dsdasdas  "+stringaId);
		return stringaId;
	}
	 
    @Override
    public int getRowCount()
    {
        return data.length;
    }
    
    @Override
    public int getColumnCount()            
    {
        return columnNames.length;
    }
    
    @Override
    public Object getValueAt(int row, int column)
    {        
    	//System.out.println(row+" "+column);
        return data[row][column];
    }
    
    public void setValueAt(Object z,int row, int column)
    {        
    	//System.out.println(row+" "+column);
        data[row][column]=z;
        fireTableCellUpdated(row, column);
        

        
    }
    //Used by the JTable object to set the column names
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
    
    //Used by the JTable object to render different
    //functionality based on the data type
    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }
    
    @Override
    public boolean isCellEditable(int row, int column)
    {
       if (column <2)
   {
        return false;
   }
   else
   {
	return true;
   }
    }
    
	
private Object[][] data=new Object[1000][4];
private ArrayList<String> c;
private ArrayList<String> id;
private String stringaId="";
private ArchivioProdotti prod=new ArchivioProdotti();
private String[] columnNames = {"", "Nome", "Descrizione", "Quantità" };
private ArchivioComponenti comp=new ArchivioComponenti();
} 



