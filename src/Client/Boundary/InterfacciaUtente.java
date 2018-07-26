package Client.Boundary;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import Client.BusinessLogic.OperazioniUtente;

public class InterfacciaUtente extends JFrame{
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 3128910075645044404L;
	private OperazioniUtente opUtente;
	private JPanel contentPane, pannelloPrincipale, pannelloTipologia, pannelloAutore, pannelloSuccesso, pannelloErrore;
	private String utente= new String("Liguori Pietro");
	
	
	/**
	 * ------------    Elementi PANNELLO PRINCIPALE  ---------------- 
	 */
	private JLabel labelScegliFunzione;
	private JButton buttonRegistraPreferenza;
	
	/**
	 * ------------    Elementi PANNELLI LISTA   ----------------
	 */
	private JLabel labelScegliTipologia, labelScegliAutore;
	private JList<String> lista1, lista2;
	private JScrollPane scrollLista1, scrollLista2;
	private JButton buttonSelezionaTipologia, buttonSelezionaAutore;
	private DefaultListModel<String> listaTipologie, listaAutori;
	private String tipologiaScelta, autoreScelto;
	
	
		
	/**
	 * ------------    Elementi PANNELLO SUCCESSO ----------------
	 */
	
	private JLabel labelSuccesso, labelErrore;
	public JButton buttonMenu;
	
	
	/**
	
	
	
	/**
	 * Launch the application.
	 */

	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfacciaUtente frame = new InterfacciaUtente();
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
	
	public InterfacciaUtente() {
		setResizable(false);
		setTitle("Interfaccia Utente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 425, 325);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		listaTipologie = new DefaultListModel<String>();
		listaAutori = new DefaultListModel<String>();
		
		try {
			opUtente= new OperazioniUtente();
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
	        dialogError.setTitle("Errore Operazioni Utente");
	        dialogError.setContentPane(contentPane);
	        
	        dialogError.setSize(new Dimension(600, 300));
	        dialogError.setVisible(true);
			pannelloPrincipale.setVisible(false);
			e1.printStackTrace();
		}
		
		
		
		/*---------------------            INIZIALIZZA ELEMENTI PANNELLO PRINCIPALE        ---------------------*/
		pannelloPrincipale = new JPanel();
		contentPane.add(pannelloPrincipale);
		pannelloPrincipale.setLayout(null);
		
		labelScegliFunzione = new JLabel("Scegli una funzione:");
		labelScegliFunzione.setFont(new Font("Tahoma", Font.BOLD, 15));
		labelScegliFunzione.setBounds(116, 44, 180, 19);
		pannelloPrincipale.add(labelScegliFunzione);
		
		buttonRegistraPreferenza= new JButton("Registra Preferenza Utente");
		buttonRegistraPreferenza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				visualizzaTipologie();
			}
		});
		buttonRegistraPreferenza.setBounds(50, 137, 323, 36);
		pannelloPrincipale.add(buttonRegistraPreferenza);
		pannelloPrincipale.setVisible(true);
		
		
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
				visualizzaAutoriByTipologia();
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
		
		labelScegliAutore= new JLabel("Seleziona un elemento dalla lista autori:");
		labelScegliAutore.setFont(new Font("Tahoma", Font.BOLD, 15));
		labelScegliAutore.setBounds(30, 38, 333, 19);
		pannelloAutore.add(labelScegliAutore);
		
		buttonMenu = new JButton("Menu principale");
		buttonMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuPrincipale();
			}
		});
		
		buttonMenu.setBounds(30, 226, 154, 23);
		pannelloAutore.add(buttonMenu);
		
		buttonSelezionaAutore= new JButton("Seleziona Autore");
		buttonSelezionaAutore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				successoOperazione();
			}
		});
		
		buttonSelezionaAutore.setBounds(227, 226, 165, 23);
		pannelloAutore.add(buttonSelezionaAutore);
		buttonSelezionaAutore.setEnabled(false);
		
		
		lista2 = new JList<String>(listaAutori);

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
		pannelloAutore.add(scrollLista2);

		
		
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
		labelErrore.setBounds(60, 40, 319, 19);
		pannelloErrore.add(labelErrore);
				
		buttonMenu = new JButton("Menu principale");
		buttonMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuPrincipale();
			}
		});
		
		buttonMenu.setBounds(133, 138, 147, 46);
		pannelloErrore.add(buttonMenu);
	}
	
	
	
	/**
	 * ---------------------            AZIONI PANNELLO PRINCIPALE        ---------------------
	 */
	private void menuPrincipale() {
		listaTipologie.clear();
		listaAutori.clear();
		pannelloTipologia.setVisible(false);
		pannelloAutore.setVisible(false);
		pannelloSuccesso.setVisible(false);
		pannelloErrore.setVisible(false);
		pannelloPrincipale.setVisible(true);
		
		buttonSelezionaTipologia.setEnabled(false);
		buttonSelezionaAutore.setEnabled(false);
	}
	
	
	/**
	 * ---------------------            AZIONI PANNELLO LISTA        ---------------------
	 */
	
	
	
	private void successoOperazione() {
		listaTipologie.clear();
		listaAutori.clear();
		pannelloTipologia.setVisible(false);
		pannelloAutore.setVisible(false);
		pannelloPrincipale.setVisible(false);
		boolean res=false;
		
		res=opUtente.inserisciPreferenza(utente, tipologiaScelta, autoreScelto);
		if (res) {
			pannelloSuccesso.setVisible(true);
			pannelloErrore.setVisible(false);
		}
		else {
			pannelloSuccesso.setVisible(false);
			pannelloErrore.setVisible(true);
			
		}
			
		
	}
	


	private String clicLista1(MouseEvent arg0){

		if(lista1.getSelectedIndex() != -1 ){
			buttonSelezionaTipologia.setEnabled(true);
		}
		
		if (buttonSelezionaTipologia.isEnabled() ) {
			tipologiaScelta = listaTipologie.getElementAt(lista1.getSelectedIndex());
			return tipologiaScelta;
		} 
		return tipologiaScelta;
	}
	
	private void clicLista2(MouseEvent arg0){
		if(lista2.getSelectedIndex() != -1){
			buttonSelezionaAutore.setEnabled(true);
		}
		if (buttonSelezionaAutore.isEnabled() ) {
			autoreScelto = listaAutori.getElementAt(lista2.getSelectedIndex());
		} 
	
	}
	
	
	private void visualizzaTipologie() {
		pannelloTipologia.setVisible(true);
		pannelloAutore.setVisible(false);
		pannelloSuccesso.setVisible(false);
		pannelloPrincipale.setVisible(false);
		pannelloErrore.setVisible(false);

		List<String> Tipologie = opUtente.getListaTipologiaRisorse();
		listaTipologie.clear();
		
		for(String tipologia: Tipologie)
			listaTipologie.addElement(tipologia);
	}
	
	private void visualizzaAutoriByTipologia() {
		pannelloTipologia.setVisible(false);
		pannelloAutore.setVisible(true);
		pannelloSuccesso.setVisible(false);
		pannelloPrincipale.setVisible(false);
		pannelloErrore.setVisible(false);

		List<String> Autori = opUtente.getListaAutoriPerTipologia(tipologiaScelta);
		listaAutori.clear();
		
		for(String autore: Autori)
			listaAutori.addElement(autore);
	}
	


}
