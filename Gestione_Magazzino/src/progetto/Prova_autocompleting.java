package progetto;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JWindow;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

	
	public class Prova_autocompleting implements ActionListener{

		private Connection connect;
		private Statement statement;	
		private ResultSet resultSet;
		private String stringa="";
		private ArrayList<String> a=new ArrayList<String>();
		private JRadioButton CompoButton;
		private JRadioButton ProdButton;
		private JFrame frame;
		private JTextField f;
		private JButton ok;
		private ArchivioComponenti archivioC=new ArchivioComponenti();
		private ArchivioProdotti archivioP=new ArchivioProdotti();
		JPanel p2 = new JPanel();
	    public Prova_autocompleting() {

	        frame = new JFrame();
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setTitle("Elimina");
	        frame.setSize(300,250);
	        frame.setLocationRelativeTo(null); //schermata al centro dello schermo


	        f = new JTextField(20);
	        ok= new JButton("Elimina");
	        ok.addActionListener(this);
	        
	        CompoButton   = new JRadioButton("Componente");
	        CompoButton.addActionListener(this);
	        ProdButton    = new JRadioButton("Prodotto");
	        ProdButton.addActionListener(this);
	        
	        ButtonGroup bgroup = new ButtonGroup();
	        bgroup.add(CompoButton);
	        bgroup.add(ProdButton);
	       
	        JPanel radioPanel = new JPanel();
	        radioPanel.setLayout(new GridLayout(1, 2));
	        radioPanel.add(CompoButton);
	        radioPanel.add(ProdButton);
	        

	       

	        JPanel p = new JPanel();
	        JPanel p1 = new JPanel();
	        
	        
	        p.add(f);
	        p1.add(radioPanel);
	        p2.add(ok);

	        frame.add(p,BorderLayout.CENTER);
	        frame.add(p1,BorderLayout.NORTH);
	        frame.add(p2,BorderLayout.SOUTH);

	      
	        frame.setVisible(true);
	    }
	    @Override
		public void actionPerformed(ActionEvent e) {
	    	// JRadioButton button = (JRadioButton) e.getSource();
			
	    	 if(CompoButton.isSelected()){
				
				 AutoSuggestor autoSuggestor = new AutoSuggestor(f, frame, null, Color.WHITE.brighter(), Color.black, Color.red, 0.75f) {
			            @Override
			            boolean wordTyped(String typedWord) {

			                //create list for dictionary this in your case might be done via calling a method which queries db and returns results as arraylist
			                ArrayList<String> words = new ArrayList<>();
			                
		//****
			              //prendo tutti i dati dal db componenti
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
			        		      resultSet=statement.executeQuery("select id,nome,cod_materiale from componenti;");
			        		      //resultSet = statement.getResultSet();
			        		      
			        		      while (resultSet.next()) {
			        		    	  String id = resultSet.getString("id");
			        		          String  nome = resultSet.getString("nome");		          
			        		          String cod  = resultSet.getString("cod_materiale");
			    //++**HO PRESO ID E COD PER FARE UN CONFRONTO NEL DATABASE ED PRODURRE/ELIMINARE LA COSA GIUSTA, MA POI HO PENSATO CHE IL NOME **+++**    	                
			        	               System.out.println(id+", "+nome+", "+cod);
			        	                stringa = id+"/"+nome+"/"+cod;
			        	                a.add(stringa);	                
			        	            }
			        		      resultSet.close();
			        		      statement.close();
			        		      connect.close();
			        		      
			        		      
			        		  }catch(Exception e){
			        			  System.out.println("errore select dal database componenti");
			        			  
			        			
			        		  }
			        					        		
			        		for(int i=0; i<a.size();i++){
			        			
			        			String tokens[]=a.get(i).split("/");
			        		        words.add(tokens[1]);
			        		
			        		}
			             

		//****
			                setDictionary(words);
			                //addToDictionary("bye");//adds a single word

			                return super.wordTyped(typedWord);//now call super to check for any matches against newest dictionary
			            }
			        };
			}
	    	 if(ProdButton.isSelected()){
				
					 AutoSuggestor autoSuggestor = new AutoSuggestor(f, frame, null, Color.WHITE.brighter(), Color.black, Color.red, 0.75f) {
				            @Override
				            boolean wordTyped(String typedWord) {

				                //create list for dictionary this in your case might be done via calling a method which queries db and returns results as arraylist
				                ArrayList<String> words = new ArrayList<>();
				                
			//****
				              //prendo tutti i dati dal db componenti
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
				        		      resultSet=statement.executeQuery("select id,nome from prodotti;");
				        		      //resultSet = statement.getResultSet();
				        		      
				        		      while (resultSet.next()) {
				        		    	  String id = resultSet.getString("id");
				        		          String  nome = resultSet.getString("nome");		          
				        		          
				    //++**HO PRESO ID E COD PER FARE UN CONFRONTO NEL DATABASE ED PRODURRE/ELIMINARE LA COSA GIUSTA, MA POI HO PENSATO CHE IL NOME **+++**    	                
				        	               System.out.println(id+", "+nome);
				        	                stringa = id+"/"+nome;
				        	                a.add(stringa);	                
				        	            }
				        		      resultSet.close();
				        		      statement.close();
				        		      connect.close();
				        		      
				        		      
				        		  }catch(Exception e){
				        			  System.out.println("errore select dal database componenti");
				        			  
				        			
				        		  }
				        		//assegno all'arraylist keywords i nomi dei componenti
				        		
				        		for(int i=0; i<a.size();i++){
				        			
				        			String tokens[]=a.get(i).split("/");
				        		        words.add(tokens[1]);
				        		       
				        		}
				             
			//****
				                setDictionary(words);
				                //addToDictionary("bye");//adds a single word

				                return super.wordTyped(typedWord);//now call super to check for any matches against newest dictionary
				            }
				        };
				}
	    	 if(e.getSource()==ok){
	    		 String nom=f.getText();
	    		 nom=nom.substring(0, nom.length()-1);
	    		 System.out.println("|"+nom+"|");
	    		 if(CompoButton.isSelected()){
		    		 try {
						archivioC.EliminaCompo(nom);
						JOptionPane.showMessageDialog(p2,"Componente eliminato correttamente", "eliminato correttamente", JOptionPane.INFORMATION_MESSAGE);
						frame.setVisible(false);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	    		 }
	    		 if(ProdButton.isSelected()){
	    			 try {
							archivioP.EliminaProd(nom);
							JOptionPane.showMessageDialog(p2,"Prodotto eliminato correttamente", "eliminato correttamente", JOptionPane.INFORMATION_MESSAGE);
							frame.setVisible(false);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	    		 }
	    	 }
				
		}
	    public static void main(String[] args) {
	        SwingUtilities.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	                new Prova_autocompleting();
	            }
	        });
	    }

		
	}

	class AutoSuggestor {

	    private final JTextField textField;
	    private final Window container;
	    private JPanel suggestionsPanel;
	    private JWindow autoSuggestionPopUpWindow;
	    private String typedWord;
	    private final ArrayList<String> dictionary = new ArrayList<>();
	    private int currentIndexOfSpace, tW, tH;
	    private DocumentListener documentListener = new DocumentListener() {
	        @Override
	        public void insertUpdate(DocumentEvent de) {
	            checkForAndShowSuggestions();
	        }

	        @Override
	        public void removeUpdate(DocumentEvent de) {
	            checkForAndShowSuggestions();
	        }

	        @Override
	        public void changedUpdate(DocumentEvent de) {
	            checkForAndShowSuggestions();
	        }
	    };
	    private final Color suggestionsTextColor;
	    private final Color suggestionFocusedColor;

	    public AutoSuggestor(JTextField textField, Window mainWindow, ArrayList<String> words, Color popUpBackground, Color textColor, Color suggestionFocusedColor, float opacity) {
	        this.textField = textField;
	        this.suggestionsTextColor = textColor;
	        this.container = mainWindow;
	        this.suggestionFocusedColor = suggestionFocusedColor;
	        this.textField.getDocument().addDocumentListener(documentListener);

	        setDictionary(words);

	        typedWord = "";
	        currentIndexOfSpace = 0;
	        tW = 0;
	        tH = 0;

	        autoSuggestionPopUpWindow = new JWindow(mainWindow);
	        autoSuggestionPopUpWindow.setOpacity(opacity);

	        suggestionsPanel = new JPanel();
	        suggestionsPanel.setLayout(new GridLayout(0, 1));
	        suggestionsPanel.setBackground(popUpBackground);

	       
	    }

	    
	   

	    private void setFocusToTextField() {
	        container.toFront();
	        container.requestFocusInWindow();
	        textField.requestFocusInWindow();
	    }

	    public ArrayList<SuggestionLabel> getAddedSuggestionLabels() {
	        ArrayList<SuggestionLabel> sls = new ArrayList<>();
	        for (int i = 0; i < suggestionsPanel.getComponentCount(); i++) {
	            if (suggestionsPanel.getComponent(i) instanceof SuggestionLabel) {
	                SuggestionLabel sl = (SuggestionLabel) suggestionsPanel.getComponent(i);
	                sls.add(sl);
	            }
	        }
	        return sls;
	    }

	    private void checkForAndShowSuggestions() {
	        typedWord = getCurrentlyTypedWord();
//++++++++******* DEVE FARE IL REMOVE ALL MA NON LO Fà, QUINDI STAMPA LA PAROLA AD OGNI LETTERA CHE INSERISCO***++++++++++
	        suggestionsPanel.removeAll();//remove previos words/jlabels that were added
	        
	        //used to calcualte size of JWindow as new Jlabels are added
	        tW = 0;
	        tH = 0;

	        boolean added = wordTyped(typedWord);

	        if (!added) {
	            if (autoSuggestionPopUpWindow.isVisible()) {
	                autoSuggestionPopUpWindow.setVisible(false);
	            }
	        } else {
	            showPopUpWindow();
	            setFocusToTextField();
	        }
	    }

	    protected void addWordToSuggestions(String word) {
	        SuggestionLabel suggestionLabel = new SuggestionLabel(word, suggestionFocusedColor, suggestionsTextColor, this);

	        calculatePopUpWindowSize(suggestionLabel);

	        suggestionsPanel.add(suggestionLabel);
	    }

	    public String getCurrentlyTypedWord() {//get newest word after last white spaceif any or the first word if no white spaces
	        String text = textField.getText();
	        String wordBeingTyped = "";
	        if (text.contains(" ")) {
	            int tmp = text.lastIndexOf(" ");
	            if (tmp >= currentIndexOfSpace) {
	                currentIndexOfSpace = tmp;
	                wordBeingTyped = text.substring(text.lastIndexOf(" "));
	            }
	        } else {
	            wordBeingTyped = text;
	        }
	        return wordBeingTyped.trim();
	    }

	    private void calculatePopUpWindowSize(JLabel label) {
	        //so we can size the JWindow correctly
	    	
	        if (tW < label.getPreferredSize().width) {
	            tW = label.getPreferredSize().width;
	        }
	        tH += label.getPreferredSize().height;
	    }

	    private void showPopUpWindow() {
	    	autoSuggestionPopUpWindow.repaint();
	        autoSuggestionPopUpWindow.getContentPane().add(suggestionsPanel);
	        autoSuggestionPopUpWindow.setMinimumSize(new Dimension(textField.getWidth(), 30));
	        autoSuggestionPopUpWindow.setSize(tW, tH);
	        autoSuggestionPopUpWindow.setVisible(true);

	        int windowX = 0;
	        int windowY = 0;

	        windowX = container.getX() + textField.getX() + 5;
	        if (suggestionsPanel.getHeight() > autoSuggestionPopUpWindow.getMinimumSize().height) {
	        	//posizione della finestra
	            windowY = /*container.getY()*/ 280 + textField.getY() + textField.getHeight() + autoSuggestionPopUpWindow.getMinimumSize().height;
	        } else {
	            windowY = /*container.getY()*/ 260 + textField.getY() + textField.getHeight() + autoSuggestionPopUpWindow.getHeight();
	        }
	        System.out.println("textField.getY():"+textField.getY()+" container.getY():"+container.getY()+" textField.getHeight():"+textField.getHeight());
	        autoSuggestionPopUpWindow.setLocation(windowX, windowY);
	        autoSuggestionPopUpWindow.setMinimumSize(new Dimension(textField.getWidth(), 30));
	        autoSuggestionPopUpWindow.revalidate();
	        autoSuggestionPopUpWindow.repaint();

	    }

	    public void setDictionary(ArrayList<String> words) {
	        dictionary.clear();
	        if (words == null) {
	            return;//so we can call constructor with null value for dictionary without exception thrown
	        }
	        for (String word : words) {
	            dictionary.add(word);
	        }
	    }

	    public JWindow getAutoSuggestionPopUpWindow() {
	        return autoSuggestionPopUpWindow;
	    }

	    public Window getContainer() {
	        return container;
	    }

	    public JTextField getTextField() {
	        return textField;
	    }

	    public void addToDictionary(String word) {
	        dictionary.add(word);
	    }

	    boolean wordTyped(String typedWord) {

	        if (typedWord.isEmpty()) {
	            return false;
	        }
	       // System.out.println("Typed word: " + typedWord);

	        boolean suggestionAdded = false;

	        for (String word : dictionary) {//get words in the dictionary which we added
	            boolean fullymatches = true;
	           
	            for (int i = 0; i < typedWord.length(); i++) {//each string in the word
	                if (!typedWord.toLowerCase().startsWith(String.valueOf(word.toLowerCase().charAt(i)), i)) {//check for match
	                    fullymatches = false;
	                    break;
	                }
	            }
	            if (fullymatches) {
//++++****AVEVO PENSATO DI FARE UN CONTROLLO QUì,SE INSERIRE O MENO LA PAROLA A SECONDA SE SIA UGUALE ALLA PRIMA PAROLA DEL 'SUGGESTIONPANEL' MA NON MI RIESCE+++***	            	
		            	//if(!suggestionsPanel.get(i).equals(word)){
		            	//if(word.equals()){
		                addWordToSuggestions(word);
		                suggestionAdded = true;
		            	//}
	            	
	            }
	        }
	        return suggestionAdded;
	    }
	}

	class SuggestionLabel extends JLabel {

	    private boolean focused = false;
	    private final JWindow autoSuggestionsPopUpWindow;
	    private final JTextField textField;
	    private final AutoSuggestor autoSuggestor;
	    private Color suggestionsTextColor, suggestionBorderColor;

	    public SuggestionLabel(String string, final Color borderColor, Color suggestionsTextColor, AutoSuggestor autoSuggestor) {
	        super(string);

	        this.suggestionsTextColor = suggestionsTextColor;
	        this.autoSuggestor = autoSuggestor;
	        this.textField = autoSuggestor.getTextField();
	        this.suggestionBorderColor = borderColor;
	        this.autoSuggestionsPopUpWindow = autoSuggestor.getAutoSuggestionPopUpWindow();

	        initComponent();
	    }

	    private void initComponent() {
	        setFocusable(true);
	        setForeground(suggestionsTextColor);

	        addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent me) {
	                super.mouseClicked(me);

	                replaceWithSuggestedText();

	                autoSuggestionsPopUpWindow.setVisible(false);
	            }
	        });

	        getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true), "Enter released");
	        getActionMap().put("Enter released", new AbstractAction() {
	            @Override
	            public void actionPerformed(ActionEvent ae) {
	                replaceWithSuggestedText();
	                autoSuggestionsPopUpWindow.setVisible(false);
	            }
	        });
	    }

	    public void setFocused(boolean focused) {
	        if (focused) {
	            setBorder(new LineBorder(suggestionBorderColor));
	        } else {
	            setBorder(null);
	        }
	        repaint();
	        this.focused = focused;
	    }

	    public boolean isFocused() {
	        return focused;
	    }

	    private void replaceWithSuggestedText() {
	        String suggestedWord = getText();
	        String text = textField.getText();
	        String typedWord = autoSuggestor.getCurrentlyTypedWord();
	        String t = text.substring(0, text.lastIndexOf(typedWord));
	        String tmp = t + text.substring(text.lastIndexOf(typedWord)).replace(typedWord, suggestedWord);
	        textField.setText(tmp + " ");
	    }
	}
