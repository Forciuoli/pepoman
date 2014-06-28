package progetto;
//rocco
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class AddComponentePanel  extends JDialog implements ActionListener{
	private static final long serialVersionUID = 1L;

	JFrame frame=new JFrame("Aggiungi componente");
	double size[][] = {{10, 75, 75, 75, 75, 75, 10}, // Columns
            {10, 75, 75, 75, 75, 75, 10}}; // Rows


     String label[] = {"(1,1)", "(1,5)", "(1,3)", "(5,3)", "(3,3)"};
     JButton button[] = new JButton[label.length];
	
	JPanel p1=new JPanel(new GridBagLayout());
	JPanel p2=new JPanel(new GridBagLayout());
	JPanel p3=new JPanel(new GridBagLayout());
	
	JLabel info=new JLabel("AGGIUNGI UN COMPONENTE");
	
	JTextField nome= new JTextField(20);
	JTextArea descrizione= new JTextArea(4, 20);
	JTextField quantità= new JTextField(20);
	
	JLabel nome1=new JLabel("   Nome   ");
	JLabel descrizione1=new JLabel("Descrizione");
	JLabel quantità1=new JLabel("Quantità");
		
	JButton ok= new JButton("OK");
	
	
	public AddComponentePanel(){
		
		setSize(500,350);
		setResizable(false);
		
		
		ok.addActionListener(this);
		
		
		
		
		
		p2.add(info);
		//p3.add(nome);
		//p3.add(descrizione);
		//p3.add(quantità);
		p1.add(ok);
				
		
		GridBagConstraints gbc=new GridBagConstraints();
		gbc.insets=new Insets(15, 15, 15, 15);
		
		gbc.gridx=0;
		gbc.gridy=0;
		p3.add(nome1,gbc);
		gbc.gridx=1;
		gbc.gridy=0;
		p3.add(nome,gbc);
		gbc.gridx=0;
		gbc.gridy=1;
		p3.add(descrizione1,gbc);
		gbc.gridx=1;
		gbc.gridy=1;
		p3.add(descrizione,gbc);
		gbc.gridx=0;
		gbc.gridy=2;
		p3.add(quantità1,gbc);
		gbc.gridx=1;
		gbc.gridy=2;
		p3.add(quantità,gbc);
		//gbc.gridx=0;
		//gbc.gridy=3;
		//p3.add(descrizione,gbc);
		
		
		
		add(p2,BorderLayout.NORTH);
		add(p3,BorderLayout.CENTER);
		add(p1,BorderLayout.SOUTH);
		
	}
	
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==ok){
			String nom=(String) nome.getText();
			String desc=(String) descrizione.getText();
			String quant=(String) quantità.getText();
			int q=Integer.parseInt(quant);
				try {
					archivio.addToDatabase(new Componente(nom, desc, q));
					JOptionPane.showMessageDialog(p3,"Inserimento avvenuto correttamente",
						    "inserimento corretto",
						    JOptionPane.INFORMATION_MESSAGE);
					dispose();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(p3,"Uno dei campi è vuoto.",
						    "Campo vuoto",
						    JOptionPane.WARNING_MESSAGE);
					e1.printStackTrace();
				}
				
		}
	}
private ArchivioComponenti archivio=new ArchivioComponenti();
}
