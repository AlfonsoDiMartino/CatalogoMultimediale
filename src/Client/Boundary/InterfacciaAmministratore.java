package Client.Boundary;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import Client.BusinessLogic.OperazioniAmministratore;

public class InterfacciaAmministratore extends JFrame{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8119568587401269563L;
	private OperazioniAmministratore opAmministratore;
	private JPanel contentPane, pannelloPrincipale, pannelloInserimento, pannelloTipologia, pannelloAutore, pannelloCreaAutore, pannelloMese, pannelloSuccesso, pannelloErrore, pannelloPremium, pannelloRisorsa;
	private int sceltaOp=0;
	private static final int inserisciRisorsa=1, gestisciOfferte=2, generaReport=3, salvaAutore=4, menu=0;

	
	
	/**
	 * ------------    Elementi PANNELLO PRINCIPALE  ---------------- 
	 */
	private JLabel labelScegliFunzione;
	private JButton buttonInserisciRisorsa;
	private JButton buttonGestisciOfferte;
	private JButton buttonGeneraReport;
	
	
	
	/**
	 * ------------    Elementi PANNELLI Inserimento ----------------
	 */
	private JLabel labelInserisci, labelScegliNome, labelScegliAutore, labelScegliCostoAcq, labelScegliCostoNol, labelNomeAutore, labelCognomeAutore;
	private JLabel  labelSetNomeR, labelSetCostoAcq,labelSetCostoNol, labelSetBrowse, labelSetNomeA, labelSetCognomeA;
	private JTextField textNome, textCostoAcquisto, textCostoNoleggio, textNomeAutore, textCognomeAutore, textPath;
	private JButton buttonInserisciInfo, buttonRitornaSelezionaAutore, buttonSalvaAutore, buttonBrowse;
	private String nomeScelto, autoreScelto, costoAcqScelto, costoNolScelto, nomeAutoreScelto, cognomeAutoreScelto, file;
	private Float costoAcq, costoNol;
	private JFileChooser fileChooser;
	
	
	
	/**
	 * ------------    Elementi PANNELLI LISTA   ----------------
	 */
	private JLabel labelScegliTipologia, labelScegliMese, labelCreaAutore, labelSalvaAutore, labelScegliUtente, labelScegliRisorsa;
	private JList<String> lista1, lista2, lista4, lista5;
	private JList <Integer> lista3;
	private JScrollPane scrollLista1, scrollLista2, scrollLista3, scrollLista4, scrollLista5;
	private JButton buttonSelezionaTipologia,buttonSelezionaAutore, buttonCreaAutore, buttonSelezionaMese, buttonSelezionaUtente, buttonSelezionaRisorse;
	private DefaultListModel<String> listaTipologie, listaPremium, listaRisorse, listaAutori; 
	private DefaultListModel<Integer>  listaMese;
	private String tipologiaScelta, utenteScelto;
	private String[] risorse;
	private Integer meseScelto=0;
	private Float report;
	
	

	
		
	/**
	 * ------------    Elementi PANNELLI MESSAGGI ----------------
	 */
	
	private JLabel labelSuccesso, labelErrore;
	public JButton buttonMenu;
	
	
	/**
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfacciaAmministratore frame = new InterfacciaAmministratore();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	
	/**
	 * Create the application
	 */
	
	public InterfacciaAmministratore() {
		
		setResizable(false);
		setTitle("Interfaccia Amministratore");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 425, 325);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		listaTipologie = new DefaultListModel<String>();
		listaAutori = new DefaultListModel<String>();
		listaPremium = new DefaultListModel<String>();
		listaRisorse = new DefaultListModel<String>();
		listaMese = new DefaultListModel<Integer>();
		risorse= new String[3];
		report=new Float(0);
		costoAcq= new Float(0);
		costoNol = new Float(0);
		
		
		try {
			opAmministratore = new OperazioniAmministratore();
		} catch (RemoteException e1) {
			JDialog dialogError = new JDialog();
	        JLabel labelError;
	        labelError = new JLabel("<html><p align=center>"
	        						+ "<font color='red' size='5'>" + e1.getMessage() + "</font> ");
	                labelError.setHorizontalAlignment(JLabel.CENTER);

	        JPanel errorPanel = new JPanel();
	        errorPanel.setLayout(new BoxLayout(errorPanel, BoxLayout.LINE_AXIS));
	        errorPanel.add(Box.createHorizontalGlue());
	        errorPanel.setBorder(BorderFactory.createEmptyBorder(0,0,5,5));

	        JPanel contentPane = new JPanel(new BorderLayout());
	        contentPane.add(labelError, BorderLayout.CENTER);
	        contentPane.add(errorPanel, BorderLayout.PAGE_END);
	        contentPane.setOpaque(true);
	        dialogError.setTitle("Errore Operazioni Amministratore");
	        dialogError.setContentPane(contentPane);
	        
	        dialogError.setSize(new Dimension(600, 300));
	        dialogError.setVisible(true);
			pannelloPrincipale.setVisible(false);

	        e1.printStackTrace();
		} catch (NotBoundException e1) {
			JDialog dialogError = new JDialog();
	        JLabel labelError;
	        labelError = new JLabel("<html><p align=center>"
	        						+ "<font color='red' size='5'>" + e1.getMessage() + "</font> ");
	                labelError.setHorizontalAlignment(JLabel.CENTER);

	        JPanel errorPanel = new JPanel();
	        errorPanel.setLayout(new BoxLayout(errorPanel, BoxLayout.LINE_AXIS));
	        errorPanel.add(Box.createHorizontalGlue());
	        errorPanel.setBorder(BorderFactory.createEmptyBorder(0,0,5,5));

	        JPanel contentPane = new JPanel(new BorderLayout());
	        contentPane.add(labelError, BorderLayout.CENTER);
	        contentPane.add(errorPanel, BorderLayout.PAGE_END);
	        contentPane.setOpaque(true);
	        dialogError.setTitle("Errore Operazioni Amministratore");
	        dialogError.setContentPane(contentPane);
	        
	        dialogError.setSize(new Dimension(300, 150));
	        dialogError.setVisible(true);
			
			e1.printStackTrace();
			pannelloPrincipale.setVisible(false);

		}
		
		
		
		/*---------------------            INIZIALIZZA ELEMENTI PANNELLO PRINCIPALE ---------------------*/
		pannelloPrincipale = new JPanel();
		contentPane.add(pannelloPrincipale);
		pannelloPrincipale.setLayout(null);


		
		labelScegliFunzione = new JLabel("Scegli una funzione:");
		labelScegliFunzione.setFont(new Font("Tahoma", Font.BOLD, 15));
		labelScegliFunzione.setBounds(116, 44, 180, 19);
		pannelloPrincipale.add(labelScegliFunzione);
		
		
		buttonInserisciRisorsa= new JButton("Inserisci Risorsa");
		buttonInserisciRisorsa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sceltaOp=inserisciRisorsa;
				visualizzaTipologie();
			}
		});
		buttonInserisciRisorsa.setBounds(50, 91, 323, 36);
		pannelloPrincipale.add(buttonInserisciRisorsa);

		
		
		buttonGestisciOfferte= new JButton("Gestisci Offerte");
		buttonGestisciOfferte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sceltaOp=gestisciOfferte;
				visualizzaUtentiPremium();
			}
		});		
		buttonGestisciOfferte.setBounds(50, 143, 323, 36);
		pannelloPrincipale.add(buttonGestisciOfferte);

		
		
		buttonGeneraReport= new JButton("Genera Report");
		buttonGeneraReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sceltaOp=generaReport;
				visualizzaTipologie();
			}
		});
		buttonGeneraReport.setBounds(50, 202, 323, 36);
		pannelloPrincipale.add(buttonGeneraReport);
		pannelloPrincipale.setVisible(true);
		

		/*---------------------           INIZIALIZZA ELEMENTI PANNELLO INSERIMENTO ---------------------*/
		pannelloInserimento = new JPanel();
		contentPane.add(pannelloInserimento);
		pannelloInserimento.setLayout(null);
		pannelloInserimento.setVisible(false);
		
		labelInserisci = new JLabel("Inserisci informazioni risorsa: ");
		labelInserisci.setFont(new Font("Tahoma", Font.BOLD, 15));
		labelInserisci.setBounds(55, 24, 360, 19);
		pannelloInserimento.add(labelInserisci);
		
		
		labelScegliNome= new JLabel("Titolo risorsa:");
		labelScegliNome.setFont(new Font("Tahoma", Font.BOLD, 15));
		labelScegliNome.setBounds(12, 65, 169, 19);
		pannelloInserimento.add(labelScegliNome);
			
		labelScegliCostoAcq= new JLabel("Costo acquisto:");
		labelScegliCostoAcq.setFont(new Font("Tahoma", Font.BOLD, 15));
		labelScegliCostoAcq.setBounds(12, 109, 147, 19);
		pannelloInserimento.add(labelScegliCostoAcq);
		
		labelScegliCostoNol= new JLabel("Costo noleggio:");
		labelScegliCostoNol.setFont(new Font("Tahoma", Font.BOLD, 15));
		labelScegliCostoNol.setBounds(12, 149, 141, 19);
		pannelloInserimento.add(labelScegliCostoNol);
		
		
		labelSetNomeR = new JLabel("\u2714");
		labelSetNomeR.setBounds(375, 44, 26, 40);
		labelSetNomeR.setSize(40, 40);
		pannelloInserimento.add(labelSetNomeR);
		labelSetNomeR.setVisible(false);
		
		labelSetCostoAcq = new JLabel("\u2714");
		labelSetCostoAcq.setBounds(375, 98, 26, 40);
		labelSetCostoAcq.setSize(40, 40);
		pannelloInserimento.add(labelSetCostoAcq);
		labelSetCostoAcq.setVisible(false);
		
		labelSetCostoNol = new JLabel("\u2714");
		labelSetCostoNol.setBounds(375, 138, 45, 40);
		labelSetCostoNol.setSize(40, 40);
		pannelloInserimento.add(labelSetCostoNol);
		labelSetCostoNol.setVisible(false);
		
		labelSetBrowse = new JLabel("\u2714");
		labelSetBrowse.setBounds(375, 171, 26, 40);
		labelSetBrowse.setSize(40, 40);
		pannelloInserimento.add(labelSetBrowse);
		labelSetBrowse.setVisible(false);
		
		buttonMenu = new JButton("Menu principale");
		buttonMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuPrincipale();
			}
		});
		
		buttonMenu.setBounds(22, 254, 147, 23);
		pannelloInserimento.add(buttonMenu);
		
		buttonInserisciInfo= new JButton("Inserisci informazioni");
		buttonInserisciInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sceltaOp=inserisciRisorsa;
				successoOperazione();
			}
		});
		
		
		buttonBrowse = new JButton("Browse");
	    buttonBrowse.setBounds(37, 180, 87, 23);
	    pannelloInserimento.add(buttonBrowse);
		
		buttonInserisciInfo.setBounds(205, 254, 198, 23);
		pannelloInserimento.add(buttonInserisciInfo);
		buttonInserisciInfo.setEnabled(false);
		
		
		textNome = new JTextField("");
		textNome.setBounds(177,55,181,23);
	    pannelloInserimento.add(textNome);
	    textNome.setEnabled(true);
	    
	   
	    
	    textCostoAcquisto = new JTextField("0");
		textCostoAcquisto .setBounds(177,105,181,23);
	    pannelloInserimento.add(textCostoAcquisto );
	    textCostoAcquisto .setEnabled(false);
	    
	    
	    textPath = new JTextField("");
	    textPath.setBounds(177, 184, 183, 21);
	    pannelloInserimento.add(textPath);
	    //textPath.setColumns(10);
	    textPath.setEnabled(false);

	    
	    fileChooser = new JFileChooser();
		fileChooser.setSize(361, 159);
		fileChooser.setLocation(29, 67);
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));

	    
	    //Inserimento solo valori numerici
	    textCostoAcquisto.addKeyListener(new KeyAdapter() {
	         public void keyTyped(KeyEvent e) {
	           char c = e.getKeyChar();
	           if (!(Character.isDigit(c) ||
	              (c == KeyEvent.VK_BACK_SPACE) ||
	              (c == KeyEvent.VK_DELETE) ||  (c=='.'))) {
	                e.consume();
	              }
	         }
	       });
	    
	    textCostoNoleggio  = new JTextField("0");
		textCostoNoleggio.setBounds(176,147,181,23);
	    pannelloInserimento.add(textCostoNoleggio);
	    textCostoNoleggio.setEnabled(false);
	    
	    
	    //Inserimento solo valori numerici
	    textCostoNoleggio.addKeyListener(new KeyAdapter() {
	         public void keyTyped(KeyEvent e) {
	           char c = e.getKeyChar();
	           if (!(Character.isDigit(c) ||
	              (c == KeyEvent.VK_BACK_SPACE) ||
	              (c == KeyEvent.VK_DELETE) ||  (c=='.'))) {
	                e.consume();
	              }
	         }
	       });
	    
	    textNome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nomeScelto = textNome.getText();
				if (nomeScelto != null && !nomeScelto.isEmpty()) {
					textCostoAcquisto.setEnabled(true);
				    labelSetNomeR.setVisible(true);
				}
					
				else {
					labelSetNomeR.setVisible(false);
					textCostoAcquisto.setEnabled(false);
					textCostoNoleggio.setEnabled(false);
					textPath.setEnabled(false);
					buttonInserisciInfo.setEnabled(false);
				}
			}
		});
	    

	    
	    textCostoAcquisto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				costoAcqScelto = textCostoAcquisto.getText();
				if ((costoAcqScelto != null && !costoAcqScelto.isEmpty())& textCostoAcquisto.isEnabled()) {
					costoAcq = Float.parseFloat(costoAcqScelto);
					textCostoNoleggio.setEnabled(true);
					labelSetCostoAcq.setVisible(true);
				}
				else {
					textCostoNoleggio.setEnabled(false);
					textPath.setEnabled(false);
					buttonInserisciInfo.setEnabled(false);
					labelSetCostoAcq.setVisible(false);
				}
			}
		});
	    
	    
	    textCostoNoleggio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				costoNolScelto = textCostoNoleggio.getText();
				
				if ((costoNolScelto != null && !costoNolScelto.isEmpty())& textCostoNoleggio.isEnabled() & textCostoAcquisto.isEnabled() ) {
					costoNol= Float.parseFloat(costoNolScelto);
					textPath.setEnabled(true);
					labelSetCostoNol.setVisible(true);
				}
					
				else {
					textPath.setEnabled(false);
					buttonInserisciInfo.setEnabled(false);
					labelSetCostoNol.setVisible(false);
				}
			}
		});
	    
	    
	    textPath.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				file = textPath.getText();
				
				if ((file != null && !file.isEmpty())& textPath.isEnabled() & textCostoNoleggio.isEnabled() & textCostoAcquisto.isEnabled()  ) {
					buttonInserisciInfo.setEnabled(true);
					labelSetBrowse.setVisible(true);
					
				}
					
				else {
					textPath.setEnabled(false);
					buttonInserisciInfo.setEnabled(false);
					labelSetBrowse.setVisible(false);
				}
			}
		});
	    
	   
	         
	    buttonBrowse.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		        JFileChooser fileChooser = new JFileChooser();
		        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		        fileChooser.setAcceptAllFileFilterUsed(false);
		 
		        int rVal = fileChooser.showOpenDialog(null);
		        if (rVal == JFileChooser.APPROVE_OPTION) {
		          textPath.setText(fileChooser.getSelectedFile().toString());
		        }
		      }
		    });
	    
	
		/*---------------------           INIZIALIZZA ELEMENTI PANNELLO LISTA  TIPOLOGIA   ---------------------*/
		pannelloTipologia = new JPanel();
		contentPane.add(pannelloTipologia);
		pannelloTipologia.setLayout(null);
		pannelloTipologia.setVisible(false);
		
		labelScegliTipologia = new JLabel("Seleziona un elemento dalla lista tipologia: ");
		labelScegliTipologia.setFont(new Font("Tahoma", Font.BOLD, 15));
		labelScegliTipologia.setBounds(30, 32, 360, 19);
		pannelloTipologia.add(labelScegliTipologia);
		
		buttonMenu = new JButton("Menu principale");
		buttonMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuPrincipale();
			}
		});
		
		buttonMenu.setBounds(30, 226, 147, 23);
		pannelloTipologia.add(buttonMenu);
		
		buttonSelezionaTipologia= new JButton("Seleziona Tipologia");
		buttonSelezionaTipologia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				operazioniScelta(sceltaOp);
			}
		});
		
		buttonSelezionaTipologia.setBounds(209, 226, 181, 23);
		pannelloTipologia.add(buttonSelezionaTipologia);
		buttonSelezionaTipologia.setEnabled(false);
		
		
		lista1 =  new JList<String>(listaTipologie);
		lista1.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent arg0) {
				clicLista1(arg0);
			}
		});
		lista1.setBounds(130, 120, 197, 162);
		lista1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lista1.setSelectedIndex(-1);
		lista1.setVisibleRowCount(6);
		
		scrollLista1 = new JScrollPane(lista1);
		scrollLista1.setBounds(130, 85, 140, 114);
		pannelloTipologia.add(scrollLista1);
		
		
		
		
		/*---------------------           INIZIALIZZA ELEMENTI PANNELLO LISTA  AUTORI ---------------------*/
		pannelloAutore = new JPanel();
		contentPane.add(pannelloAutore);
		pannelloAutore.setLayout(null);
		pannelloAutore.setVisible(false);
		
		
		labelSalvaAutore= new JLabel("Inserisci nuovo autore");
		labelSalvaAutore.setFont(new Font("Tahoma", Font.BOLD, 15));
		labelSalvaAutore.setBounds(12, 201, 204, 29);
		pannelloAutore.add(labelSalvaAutore);
	
		
		buttonMenu = new JButton("Menu principale");
		buttonMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuPrincipale();
			}
		});
		
		buttonMenu.setBounds(128, 253, 154, 23);
		pannelloAutore.add(buttonMenu);
		
		buttonSelezionaAutore= new JButton("Seleziona Autore");
		buttonSelezionaAutore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inserisciRisorsa();
			}
		});
		
		buttonSelezionaAutore.setBounds(247, 93, 158, 23);
		pannelloAutore.add(buttonSelezionaAutore);
		buttonSelezionaAutore.setEnabled(false);
		
	
		
		buttonCreaAutore= new JButton("Crea Autore");
		buttonCreaAutore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				creaAutore();
			}
		});
		
		buttonCreaAutore.setBounds(247, 204, 158, 23);
		pannelloAutore.add(buttonCreaAutore);
		buttonCreaAutore.setEnabled(true);
		

		
		lista5 = new JList<String>(listaAutori);

		lista5.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent arg0) {
				clicLista5(arg0);
			}
		});
		lista5.setBounds(130, 62, 197, 162);
		lista5.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lista5.setSelectedIndex(-1);
		lista5.setVisibleRowCount(6);
		
		
		
		
		scrollLista5 = new JScrollPane(lista5);
		scrollLista5.setBounds(40, 59, 200, 114);
		pannelloAutore.add(scrollLista5);
		
		labelScegliAutore= new JLabel("Seleziona Autore dalla lista: ");
		labelScegliAutore.setBounds(82, 29, 231, 18);
		pannelloAutore.add(labelScegliAutore);
		labelScegliAutore.setFont(new Font("Tahoma", Font.BOLD, 15));

		
		/*---------------------           INIZIALIZZA ELEMENTI PANNELLO CREA  AUTORE ---------------------*/
		pannelloCreaAutore = new JPanel();
		contentPane.add(pannelloCreaAutore, "name_4314970943444");
		pannelloCreaAutore.setLayout(null);
		pannelloCreaAutore.setVisible(false);
		
		
		labelCreaAutore= new JLabel("Crea  Nuovo Autore:");
		labelCreaAutore.setFont(new Font("Tahoma", Font.BOLD, 15));
		labelCreaAutore.setBounds(123, 36, 230, 19);
		pannelloCreaAutore.add(labelCreaAutore);
		
		
		labelCognomeAutore= new JLabel("Cognome Autore:");
		labelCognomeAutore.setFont(new Font("Tahoma", Font.BOLD, 15));
		labelCognomeAutore.setBounds(12, 89, 165, 19);
		pannelloCreaAutore.add(labelCognomeAutore);
		
		labelNomeAutore= new JLabel("Nome Autore:");
		labelNomeAutore.setFont(new Font("Tahoma", Font.BOLD, 15));
		labelNomeAutore.setBounds(12, 149, 165, 19);
		pannelloCreaAutore.add(labelNomeAutore);
		
		labelSetNomeA = new JLabel("\u2714");
		labelSetNomeA.setBounds(386, 138, 26, 40);
		labelSetNomeA.setSize(40, 40);
		pannelloCreaAutore.add(labelSetNomeA);
		labelSetNomeA.setVisible(false);
		
		labelSetCognomeA = new JLabel("\u2714");
		labelSetCognomeA.setBounds(382, 78, 10, 19);
		labelSetCognomeA.setSize(40, 40);
		pannelloCreaAutore.add(labelSetCognomeA);
		labelSetCognomeA.setVisible(false);
		
		
		textNomeAutore = new JTextField("");
		textNomeAutore.setBounds(183,147,181,23);
	    pannelloCreaAutore.add(textNomeAutore);
	    textNomeAutore.setEnabled(false);
		
	    
	    textCognomeAutore = new JTextField("");
		textCognomeAutore.setBounds(183,87,181,23);
	    pannelloCreaAutore.add(textCognomeAutore);
	    textCognomeAutore.setEnabled(true);
		
		buttonSalvaAutore = new JButton("Salva autore");
		buttonSalvaAutore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sceltaOp=salvaAutore;
				successoOperazione();
			}
		});
		
		buttonSalvaAutore.setBounds(232, 221, 154, 23);
		pannelloCreaAutore.add(buttonSalvaAutore);
		buttonSalvaAutore.setEnabled(false);
		
		buttonRitornaSelezionaAutore = new JButton("Ritorna a lista autori");
		buttonRitornaSelezionaAutore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				visualizzaAutori();
			}
		});
		
		buttonRitornaSelezionaAutore.setBounds(12, 221, 181, 23);
		pannelloCreaAutore.add(buttonRitornaSelezionaAutore);
		
		textCognomeAutore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cognomeAutoreScelto = textCognomeAutore.getText();
				if ((cognomeAutoreScelto != null && !cognomeAutoreScelto.isEmpty())) {
					textNomeAutore.setEnabled(true);
					buttonSalvaAutore.setEnabled(false);
					labelSetCognomeA.setVisible(true);
				}
				else {
					textNomeAutore.setEnabled(false);
					buttonSalvaAutore.setEnabled(false);
					labelSetCognomeA.setVisible(false);
				}
					
				
				}
			});
		
		
		textNomeAutore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nomeAutoreScelto = textNomeAutore.getText();
				if ((nomeAutoreScelto != null && !nomeAutoreScelto.isEmpty() && textNomeAutore.isEnabled())) {
					buttonSalvaAutore.setEnabled(true);
					labelSetNomeA.setVisible(true);
				}
				else {
					buttonSalvaAutore.setEnabled(false);
					labelSetNomeA.setVisible(false);
				}
					
				
				}
			});
		
		

		
		
		
	
		/*---------------------           INIZIALIZZA ELEMENTI PANNELLO LISTA  UTENTI PREMIUM---------------------*/
		pannelloPremium = new JPanel();
		contentPane.add(pannelloPremium);
		pannelloPremium.setLayout(null);
		pannelloPremium.setVisible(false);
		
		labelScegliUtente= new JLabel("Seleziona utente premium:");
		labelScegliUtente.setFont(new Font("Tahoma", Font.BOLD, 15));
		labelScegliUtente.setBounds(97, 40, 240, 19);
		pannelloPremium.add(labelScegliUtente);
		
		buttonMenu = new JButton("Menu principale");
		buttonMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuPrincipale();
			}
		});
		
		buttonMenu.setBounds(30, 226, 154, 23);
		pannelloPremium.add(buttonMenu);
		
		buttonSelezionaUtente= new JButton("Seleziona Utente");
		buttonSelezionaUtente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				visualizzaRisorse();
			}
		});
		
		buttonSelezionaUtente.setBounds(227, 226, 165, 23);
		pannelloPremium.add(buttonSelezionaUtente);
		buttonSelezionaUtente.setEnabled(false);
		
		
		lista2 = new JList<String>(listaPremium);
		lista2.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent arg0) {
				clicLista2(arg0);
			}
		});
		lista2.setBounds(130, 62, 197, 162);
		lista2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lista2.setSelectedIndex(-1);
		lista2.setVisibleRowCount(6);
		
		
		
		
		scrollLista2 = new JScrollPane(lista2);
		scrollLista2.setBounds(130, 85, 140, 114);
		pannelloPremium.add(scrollLista2);
		

		

		
		

	
		
		
		/*---------------------           INIZIALIZZA ELEMENTI PANNELLO LISTA  MESE ---------------------*/
		pannelloMese = new JPanel();
		contentPane.add(pannelloMese);
		pannelloMese.setLayout(null);
		pannelloMese.setVisible(false);
		
		labelScegliMese= new JLabel("Scegli l'indice del mese:");
		labelScegliMese.setFont(new Font("Tahoma", Font.BOLD, 15));
		labelScegliMese.setBounds(101, 38, 210, 19);
		pannelloMese.add(labelScegliMese);
		
		buttonMenu = new JButton("Menu principale");
		buttonMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuPrincipale();
			}
		});
		
		buttonMenu.setBounds(30, 226, 154, 23);
		pannelloMese.add(buttonMenu);
		
		buttonSelezionaMese= new JButton("Seleziona Mese");
		buttonSelezionaMese.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				visualizzaReport();
			}
		});
		
		buttonSelezionaMese.setBounds(227, 226, 165, 23);
		pannelloMese.add(buttonSelezionaMese);
		buttonSelezionaMese.setEnabled(false);
		
		
		lista3 = new JList<Integer>(listaMese);
		lista3.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent arg0) {
				clicLista3(arg0);
			}
		});
		lista3.setBounds(130, 62, 197, 162);
		lista3.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lista3.setSelectedIndex(-1);
		lista3.setVisibleRowCount(6);
		
		
		
		
		scrollLista3 = new JScrollPane(lista3);
		scrollLista3.setBounds(130, 85, 140, 114);
		pannelloMese.add(scrollLista3);
		

		
		
		/*---------------------           INIZIALIZZA ELEMENTI PANNELLO LISTA  RISORSE ---------------------*/
		pannelloRisorsa = new JPanel();
		contentPane.add(pannelloRisorsa);
		pannelloRisorsa.setLayout(null);
		pannelloRisorsa.setVisible(false);
		
		labelScegliRisorsa= new JLabel("Seleziona tre risorse:");
		labelScegliRisorsa.setFont(new Font("Tahoma", Font.BOLD, 15));
		labelScegliRisorsa.setBounds(30, 38, 333, 19);
		pannelloRisorsa.add(labelScegliRisorsa);
		
		
		
		
		
		buttonMenu = new JButton("Menu principale");
		buttonMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuPrincipale();
			}
		});
		
		buttonMenu.setBounds(30, 226, 154, 23);
		pannelloRisorsa.add(buttonMenu);
		
		buttonSelezionaRisorse= new JButton("Seleziona Risorse");
		buttonSelezionaRisorse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				successoOperazione();
			}
		});
		
		buttonSelezionaRisorse.setBounds(227, 226, 165, 23);
		pannelloRisorsa.add(buttonSelezionaRisorse);
		buttonSelezionaRisorse.setEnabled(false);
		
		
		
		lista4 = new JList<String>(listaRisorse);
		lista4.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent arg0) {
				clicLista4(arg0);
			}
		});
		lista4.setBounds(130, 62, 197, 162);
		lista4.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		lista4.setSelectedIndex(-1);
		lista4.setVisibleRowCount(6);
		
		
		
		
		scrollLista4 = new JScrollPane(lista4);
		scrollLista4.setBounds(87, 85, 226, 114);
		pannelloRisorsa.add(scrollLista4);
		
		

		
		
		/*---------------------           INIZIALIZZA ELEMENTI PANNELLO SUCCESSO ---------------------*/
		pannelloSuccesso = new JPanel();
		contentPane.add(pannelloSuccesso);
		pannelloSuccesso.setLayout(null);
		pannelloSuccesso.setVisible(false);
		
		labelSuccesso= new JLabel("Operazione avvenuta con successo.");
		labelSuccesso.setFont(new Font("Tahoma", Font.BOLD, 15));
		labelSuccesso.setBounds(44, 29, 319, 19);
		pannelloSuccesso.add(labelSuccesso);
				
		buttonMenu = new JButton("Menu principale");
		buttonMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuPrincipale();
			}
		});
		
		buttonMenu.setBounds(133, 116, 147, 46);
		pannelloSuccesso.add(buttonMenu);
	
	
		/*---------------------           INIZIALIZZA ELEMENTI PANNELLO ERRORE ---------------------*/
		pannelloErrore = new JPanel();
		contentPane.add(pannelloErrore);
		pannelloErrore.setLayout(null);
		pannelloErrore.setVisible(false);
		
		labelErrore= new JLabel("ERRORE: operazione non eseguita.");
		labelErrore.setFont(new Font("Tahoma", Font.BOLD, 15));
		labelErrore.setBounds(55, 48, 319, 19);
		pannelloErrore.add(labelErrore);
				
		buttonMenu = new JButton("Menu principale");
		buttonMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuPrincipale();
			}
		});
		
		buttonMenu.setBounds(133, 135, 147, 46);
		pannelloErrore.add(buttonMenu);
	}

	
	
	
	/**
	 * ---------------------            AZIONI PANNELLO PRINCIPALE        ---------------------
	 */

	private void menuPrincipale() {
		
		//clear liste
		listaTipologie.clear();
		listaAutori.clear();
		listaRisorse.clear();
		listaPremium.clear();
		
		//set pannelli 
		pannelloPrincipale.setVisible(true);
		pannelloTipologia.setVisible(false);
		pannelloCreaAutore.setVisible(false);
		pannelloMese.setVisible(false);
		pannelloInserimento.setVisible(false);
		pannelloSuccesso.setVisible(false);
		pannelloErrore.setVisible(false);
		pannelloAutore.setVisible(false);
		pannelloPremium.setVisible(false);
		pannelloRisorsa.setVisible(false);
		
		//set button
		buttonSelezionaTipologia.setEnabled(false);
		buttonSelezionaMese.setEnabled(false);
		buttonSelezionaUtente.setEnabled(false);
		buttonSelezionaRisorse.setEnabled(false);
		buttonSelezionaAutore.setEnabled(false);
		buttonSalvaAutore.setEnabled(false);
		buttonInserisciInfo.setEnabled(false);

		
		//set text
		textNome.setText("");
		textCostoAcquisto.setText("0");
		textCostoNoleggio.setText("0");
		textNomeAutore.setText("");
		textCognomeAutore.setText("");
		textPath.setText("");
		textCostoAcquisto.setEnabled(false);
		textCostoNoleggio.setEnabled(false);
		textPath.setEnabled(false);
		
		//set label
		labelSetNomeR.setVisible(false);
		labelSetCostoAcq.setVisible(false);
		labelSetCostoNol.setVisible(false);
		labelSetBrowse.setVisible(false);
		labelSetNomeA.setVisible(false);
		labelSetCognomeA.setVisible(false);
		
		sceltaOp=menu;
	

	}
	
	
	private void successoOperazione() {
		pannelloTipologia.setVisible(false);
		pannelloInserimento.setVisible(false);
		pannelloPrincipale.setVisible(false);
		pannelloPremium.setVisible(false);
		pannelloRisorsa.setVisible(false);
		pannelloCreaAutore.setVisible(false);
		pannelloMese.setVisible(false);
		
		Boolean res= new Boolean(false);
		
		if (sceltaOp==inserisciRisorsa) {
			res=opAmministratore.inserisciRisorsa(tipologiaScelta,autoreScelto, nomeScelto, costoAcq, costoNol, file); //file
			if (res==true) {
				pannelloSuccesso.setVisible(true);
				pannelloErrore.setVisible(false);
				pannelloAutore.setVisible(false);
			}
			else {
				pannelloSuccesso.setVisible(false);
				pannelloErrore.setVisible(true);
				pannelloAutore.setVisible(false);
			}
				
		}
			
		else if (sceltaOp==gestisciOfferte) {
			res=opAmministratore.aggiungiRisorseACatalogoUtente(utenteScelto, risorse);
			if (res==true) {
				pannelloSuccesso.setVisible(true);
				pannelloErrore.setVisible(false);
				pannelloAutore.setVisible(false);
			}
			else {
				pannelloSuccesso.setVisible(false);
				pannelloErrore.setVisible(true);
				pannelloAutore.setVisible(false);
			}
		}
			
		else if (sceltaOp ==salvaAutore) {
		    res = opAmministratore.inserisciAutore( nomeAutoreScelto, cognomeAutoreScelto);
			if (res==true) {
				visualizzaAutori();
			}
			else {
				pannelloSuccesso.setVisible(false);
				pannelloErrore.setVisible(true);
				pannelloAutore.setVisible(false);
			}
		}
		
	}

	
	
	
	
	private void operazioniScelta(int scelta) {
		switch(scelta) {
		case 1:
			visualizzaAutori();
			break;
		case 2:
			menuPrincipale();
			break;
		case 3:
			visualizzaMese();
			break;
		default:
			menuPrincipale();
			break;
		}
	}
	
	
	/**
	 * ---------------------            AZIONI PANNELLO LISTA        ---------------------
	 */
	
	
	

	


	private void clicLista1(MouseEvent arg0){

		if(lista1.getSelectedIndex() != -1 ){
			buttonSelezionaTipologia.setEnabled(true);
		}
		
		if (buttonSelezionaTipologia.isEnabled() ) {
			tipologiaScelta = listaTipologie.getElementAt(lista1.getSelectedIndex());
			} 
		}
	
	
	private void clicLista2(MouseEvent arg0){
		if(lista2.getSelectedIndex() != -1){
			buttonSelezionaUtente.setEnabled(true);
		}
		
		if (buttonSelezionaUtente.isEnabled()) {
			utenteScelto = listaPremium.getElementAt(lista2.getSelectedIndex());
	
		}
		
	}
	
	private void clicLista3(MouseEvent arg0){
		if(lista3.getSelectedIndex() != -1){
			buttonSelezionaMese.setEnabled(true);
		}
		
		if (buttonSelezionaMese.isEnabled() ) {
			meseScelto = listaMese.getElementAt(lista3.getSelectedIndex());
			
		}
		
		
	}
	
	
	private void clicLista4(MouseEvent arg0){
		int[] index = new int[3];
		if(lista4.getSelectedIndices().length== 3){
			buttonSelezionaRisorse.setEnabled(true);
		}
		else 
			buttonSelezionaRisorse.setEnabled(false);
		
		if (buttonSelezionaRisorse.isEnabled()) {
			index = lista4.getSelectedIndices(); 
			for(int i=0; i<3; i++) {
				risorse[i] = listaRisorse.getElementAt(index[i]);
				
			}
		}
		
		
	}
	
	private void clicLista5(MouseEvent arg0){
		if(lista5.getSelectedIndex() != -1){
			buttonSelezionaAutore.setEnabled(true);
		}
		if (buttonSelezionaAutore.isEnabled()) {
			autoreScelto = listaAutori.getElementAt(lista5.getSelectedIndex());
		} 
	
	}
	

	
	private void visualizzaTipologie() {
		pannelloTipologia.setVisible(true);
		pannelloAutore.setVisible(false);
		pannelloMese.setVisible(false);
		pannelloSuccesso.setVisible(false);
		pannelloPrincipale.setVisible(false);
		pannelloCreaAutore.setVisible(false);
		pannelloPremium.setVisible(false);
		pannelloRisorsa.setVisible(false);
		pannelloErrore.setVisible(false);
		pannelloInserimento.setVisible(false);
		List<String> Tipologie = opAmministratore.getListaTipologie(); 
		listaTipologie.clear();
		
		for(String tipologia: Tipologie)
			listaTipologie.addElement(tipologia);
	}
	
	private void visualizzaAutori() {
		pannelloAutore.setVisible(true);
		pannelloTipologia.setVisible(false);
		pannelloMese.setVisible(false);
		pannelloSuccesso.setVisible(false);
		pannelloPrincipale.setVisible(false);
		pannelloCreaAutore.setVisible(false);
		pannelloPremium.setVisible(false);
		pannelloRisorsa.setVisible(false);
		pannelloErrore.setVisible(false);
		pannelloInserimento.setVisible(false);
		
		
		buttonSelezionaAutore.setEnabled(false);
		buttonSalvaAutore.setEnabled(false);
		textNomeAutore.setText("");
		textCognomeAutore.setText("");
		labelSetNomeA.setVisible(false);
		labelSetCognomeA.setVisible(false);
		
		
		List<String> Autori = opAmministratore.getListaAutori(); 
		listaAutori.clear();
		
		for(String autore: Autori)
			listaAutori.addElement(autore);
	}
	
	
	private void visualizzaUtentiPremium() {
		pannelloPremium.setVisible(true);
		pannelloAutore.setVisible(false);
		pannelloTipologia.setVisible(false);
		pannelloCreaAutore.setVisible(false);
		pannelloMese.setVisible(false);
		pannelloSuccesso.setVisible(false);
		pannelloRisorsa.setVisible(false);
		pannelloPrincipale.setVisible(false);
		pannelloErrore.setVisible(false);
		pannelloInserimento.setVisible(false);
		
		List<String> UtentiPremium = opAmministratore.getListaUtentiPremium(); 
		listaPremium.clear();
		
		for(String utente: UtentiPremium)
			listaPremium.addElement(utente);
	}
	
	
	private void visualizzaRisorse() {
		pannelloTipologia.setVisible(false);
		pannelloAutore.setVisible(false);
		pannelloCreaAutore.setVisible(false);
		pannelloMese.setVisible(false);
		pannelloSuccesso.setVisible(false);
		pannelloPrincipale.setVisible(false);
		pannelloPremium.setVisible(false);
		pannelloRisorsa.setVisible(true);
		pannelloErrore.setVisible(false);
		pannelloInserimento.setVisible(false);

		List<String> Risorse = opAmministratore.getRisorseNonPossedute(utenteScelto); 
		listaRisorse.clear();
		
		for(String risorsa: Risorse)
			listaRisorse.addElement(risorsa);
	}
	
	private void visualizzaMese() {
		pannelloTipologia.setVisible(false);
		pannelloAutore.setVisible(false);
		pannelloMese.setVisible(true);
		pannelloSuccesso.setVisible(false);
		pannelloPrincipale.setVisible(false);
		pannelloPremium.setVisible(false);
		pannelloRisorsa.setVisible(false);
		pannelloInserimento.setVisible(false);
		pannelloCreaAutore.setVisible(false);
		pannelloErrore.setVisible(false);

		List<Integer> Mesi = Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12);
		
		for(Integer mese: Mesi)
			listaMese.addElement(mese);
	}
	
	//parametri String tipologia
	private void inserisciRisorsa() {
		pannelloTipologia.setVisible(false);
		pannelloInserimento.setVisible(true);
		pannelloCreaAutore.setVisible(false);
		pannelloAutore.setVisible(false);
		pannelloSuccesso.setVisible(false);
		pannelloPrincipale.setVisible(false);
		pannelloPremium.setVisible(false);
		pannelloRisorsa.setVisible(false);
		pannelloErrore.setVisible(false);
		pannelloMese.setVisible(false);


	}
	
	
	private void creaAutore() {
		pannelloTipologia.setVisible(false);
		pannelloInserimento.setVisible(false);
		pannelloCreaAutore.setVisible(true);
		pannelloSuccesso.setVisible(false);
		pannelloPrincipale.setVisible(false);
		pannelloPremium.setVisible(false);
		pannelloRisorsa.setVisible(false);
		pannelloAutore.setVisible(false);
		pannelloErrore.setVisible(false);
		pannelloMese.setVisible(false);
	}
	
	private void visualizzaReport() {
		pannelloTipologia.setVisible(false);
		pannelloInserimento.setVisible(false);
		pannelloCreaAutore.setVisible(false);
		pannelloSuccesso.setVisible(false);
		pannelloPrincipale.setVisible(false);
		pannelloMese.setVisible(false);
		pannelloPremium.setVisible(false);
		pannelloRisorsa.setVisible(false);
		pannelloErrore.setVisible(false);
		pannelloAutore.setVisible(false);
		
		report=opAmministratore.generaReport(meseScelto, tipologiaScelta);

		
		JDialog dialog = new JDialog();
        JLabel label;
        
        label = new JLabel("<html><p align=center>"
                    + "Report incassi della tipologia <font size='4'>'" + tipologiaScelta + "'</font><br>"
                    + "nel mese <font size='4'>'" + meseScelto + "'</font><br><br>"
                    + "<font color='red' size='5'>" + report + "</font> euro <br>");
                label.setHorizontalAlignment(JLabel.CENTER);


        JButton closeButton = new JButton("Chiudi");
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	menuPrincipale();
                dialog.setVisible(false);
                dialog.dispose();
            }
        });
        JPanel closePanel = new JPanel();
        closePanel.setLayout(new BoxLayout(closePanel, BoxLayout.LINE_AXIS));
        closePanel.add(Box.createHorizontalGlue());
        closePanel.add(closeButton);
        closePanel.setBorder(BorderFactory.createEmptyBorder(0,0,5,5));

        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.add(label, BorderLayout.CENTER);
        contentPane.add(closePanel, BorderLayout.PAGE_END);
        contentPane.setOpaque(true);
        dialog.setTitle("Media consumi");
        dialog.setContentPane(contentPane);
        
        dialog.setSize(new Dimension(300, 150));
        dialog.setVisible(true);
        }
	
	
	
	
	}
	
