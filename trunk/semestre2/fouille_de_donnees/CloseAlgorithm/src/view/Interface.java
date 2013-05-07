package view;

import java.awt.BorderLayout;
import java.awt.FileDialog;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;

import model.BaseGeneration;
import model.CloseAlgorithm;
import model.ContextClosure;
import model.GeneratorManager;
import util.ConvertToHtmlTable;

/**
 * Interface de l'application.
 * @author ahounou & ayadi.
 * @date  20/03/2013 - 25/03/2013.
 *
 */
public class Interface extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Bouton pour lancer l'algorithme.
	 */
	private JButton startButton = new JButton("Start");
	/**
	 * Bouton pour choisir un fichier.
	 */
	private JButton chooseButton = new JButton("Choose");
	/**
	 * Bouton pour augmenter le support.
	 */
	private JButton plusButton = new JButton("+");
	/**
	 * Bouton pour diminuer le support.
	 */
	private JButton minusButton = new JButton("-");
	
	/**
	 * Champ de texte qui contient le path du fichier de donnees.
	 */
	private JTextField fileLocation = new JTextField();
	/**
	 * Champ de texte qui contient le support minimal.
	 */
	private JTextField supportTextField = new JTextField(6);
	/**
	 * Case a cocher pour savoir si on affiche la trace de l'algorithme close.
	 */
	private JCheckBox checkBox = new JCheckBox("Print close stack trace");
	private boolean closeTrace = false;
	/**
	 * Chemin du fichier.
	 */
	private String path;
	/**
	 * Zone de texte qui contient la trace de l'algorithme.
	 */
	private JTextPane textPane = new JTextPane();
	private JScrollPane scrollPane = new JScrollPane(textPane);
	
	/**
	 * Attributs pour la liste des traces
	 */
	private DefaultListModel listModel = new DefaultListModel();
	private JList list = new JList(listModel);
	private ArrayList<String> arrayTraceHTML = new ArrayList<String>();
	private ArrayList<String> arrayTrace = new ArrayList<String>();
	
	private JPopupMenu popupMenu = new JPopupMenu();
	
	private GeneratorManager generatorManager;
	private CloseAlgorithm close;
	private double supportMin = 0.0;
	private String file = "";
	
	/**
	 * Constructeur.
	 */
	public Interface(){
		
		super("Close Algorithm");
		setLayout(new BorderLayout());
		setSize(700, 300);
		
		// supprimer une trace de la liste des traces
		JMenuItem itemDelete = new JMenuItem("Delete");
		itemDelete.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				int index = list.getSelectedIndex();
				listModel.remove(index);
				arrayTraceHTML.remove(index);
				arrayTrace.remove(index);
			}
		});
		popupMenu.add(itemDelete);
		
		// enregistrer la trace dans un fichier
		JMenuItem saveTrace = new JMenuItem("Save Buffer");
		saveTrace.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				int index = list.getSelectedIndex();
				FileDialog save = new FileDialog(Interface.this, "Save to", FileDialog.SAVE);
				save.setFilenameFilter(new FilenameFilter() {
					@Override
					public boolean accept(File dir, String name) {
						return name.endsWith(".txt");
					}
			    });
				save.setVisible(true);
				if(save.getFile() != null){
					save.setFile(save.getDirectory() + save.getFile());
					PrintWriter printWriter;
					try {
						printWriter = new PrintWriter(new FileWriter(save.getFile()));
						printWriter.print(arrayTrace.get(index));
						printWriter.flush();
						printWriter.close();
					} catch (IOException e) {
						e.printStackTrace();
					} ;
				}
			}
		});
		popupMenu.add(saveTrace);
		
		// action sur la liste
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		MouseListener mouseListener = new MouseAdapter() {
			public void mouseClicked(MouseEvent mouseEvent) {
				JList theList = (JList) mouseEvent.getSource();
				if(mouseEvent.getButton() == 1){
			        int index = theList.locationToIndex(mouseEvent.getPoint());
			        if(index >= 0) {
			        	String traceHTML = arrayTraceHTML.get(index);
			        	textPane.setText(traceHTML);
			        }
				}else{
					if(mouseEvent.getButton() == 3){
						int index = theList.locationToIndex(mouseEvent.getPoint());
						theList.setSelectedIndex(index);
						popupMenu.show(mouseEvent.getComponent(), mouseEvent.getX(), 
								mouseEvent.getY());
						
					}
				}
		    }
		};
		list.addMouseListener(mouseListener);
		JScrollPane scrollPaneList = new JScrollPane(list);
		
		textPane.setContentType("text/html");
		
		// css textPane
		HTMLEditorKit kit = new HTMLEditorKit();
		textPane.setEditorKit(kit);
		StyleSheet styleSheet = kit.getStyleSheet();
	    styleSheet
	        .addRule("body {margin : 22px;}");
	    styleSheet
	        .addRule("body {color:#000000; font-family:Verdana,sans-serif;}");
	    styleSheet
        	.addRule("caption{color : red;}");
	    styleSheet
    		.addRule("h2 {text-decoration :underline;}");
	    styleSheet
			.addRule("td {text-align :center;}");
	    Document doc = kit.createDefaultDocument();
	    textPane.setDocument(doc);
		
		
		textPane.setEditable(false);
		
		// panel de choix de fichier de donnees
		JPanel northPanel = new JPanel(new BorderLayout());
		northPanel.setBorder(BorderFactory.createTitledBorder("DataFile"));
		
		// action de chooseButton
		chooseButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				FileDialog fd = new FileDialog(Interface.this, "Choose a file");
				fd.setVisible(true);
				
				if(fd.getFile() != null){
					path = fd.getDirectory() + fd.getFile();
					fileLocation.setText(path);
					file = fd.getFile();
					
					// reactivation des composants
					supportTextField.setEnabled(true);
					minusButton.setEnabled(true); plusButton.setEnabled(true);
				}
			}
		});
		
		fileLocation.setEditable(false);
		northPanel.add(chooseButton, BorderLayout.WEST);
		northPanel.add(fileLocation);
		
		final DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(2); //arrondi a 2 chiffres apres la virgules
		
		// panel de choix d'options
		JPanel optionsPanel = new JPanel(new BorderLayout());
		optionsPanel.setBorder(BorderFactory.createTitledBorder("Test Options"));
		
		JPanel tempPanel = new JPanel(new BorderLayout());
		tempPanel.add(new JLabel("Minimal Support : "));
		
		JPanel tempPanel1 = new JPanel(new BorderLayout());
		
		plusButton = new JButton("+");
		plusButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				if(!supportTextField.getText().equals("")){
					if(supportMin < 1.0){
						supportMin += 0.1;
						
						String str = df.format(supportMin);
						supportMin = Double.parseDouble(str.replace(',', '.'));
						supportTextField.setText(""+supportMin);
					}
				}else{
					supportMin = 0.1;
					supportTextField.setText(""+supportMin);
				}
				// reactivation des boutons start et stop
				startButton.setEnabled(true);
				
			}
			
		});
		
		minusButton = new JButton("-");
		minusButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				if(!supportTextField.getText().equals("")){
					if(supportMin > 0.0){
						supportMin -= 0.1;
						
						String str = df.format(supportMin);
						supportMin = Double.parseDouble(str.replace(',', '.'));
						supportTextField.setText(""+supportMin);
					}
				}else{
					supportMin = 1.0;
					supportTextField.setText(""+supportMin);
				}
				// reactivation des boutons start et stop
				startButton.setEnabled(true);
			}
			
		});
		
		supportTextField.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e){
		    	char c = e.getKeyChar();
		    	if(!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) ||
		    			(c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_SLASH) || 
		    			(c == KeyEvent.VK_PERIOD) || (c == KeyEvent.VK_ENTER))  
		    			 ) {
		        
		    		getToolkit().beep();
		    		e.consume();
		    		return;
		    	}
		    		
		    	// reactivation des boutons start et stop
				startButton.setEnabled(true);
		    }
		});
		
		tempPanel1.add(minusButton, BorderLayout.WEST);
		tempPanel1.add(supportTextField, BorderLayout.CENTER);
		tempPanel1.add(plusButton, BorderLayout.EAST);
		tempPanel.add(tempPanel1, BorderLayout.EAST);
		optionsPanel.add(tempPanel);
		
		JPanel tempPanel3 = new JPanel(new BorderLayout());
		checkBox.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				closeTrace = checkBox.isSelected();
			}
		});
		tempPanel3.add(checkBox);
		optionsPanel.add(tempPanel3, BorderLayout.SOUTH);
		
		// panel de bouton start
		JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
		startButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				// recuperation du support
				if(supportTextField.getText().contains("/")){
					double num = Double.parseDouble(supportTextField.getText().split("/")[0]);
					double den = Double.parseDouble(supportTextField.getText().split("/")[1]);
					supportMin = num/den;
				}else{
					supportMin = Double.parseDouble(supportTextField.getText());
				}
				
				
				generatorManager = new GeneratorManager(path);
				ContextClosure contextClosure = new ContextClosure(path);
				close = new CloseAlgorithm(supportMin, generatorManager,
						contextClosure);
				close.run();
				
				String traceNormal = "Close Algorithm with " + "support = " +close.getSupportMin()
				+"\n";
				
				String trace = "";
				ConvertToHtmlTable convert;
				String traceHTML = "<html>\n\t<body>\n\t\t" +"<h2>"+ "Close Algorithm with " +
				"support = " +close.getSupportMin() +"</h2>\n";
				
				if(closeTrace){
					trace = close.toString();
					traceNormal += trace;
					convert = new ConvertToHtmlTable(trace);
					traceHTML += convert.convert(1);
				}
				
				BaseGeneration base = new BaseGeneration(close.getArrayFF(), contextClosure);
				base.genBG();
				trace = base.traceBG();
				base.genRI();
				trace += base.traceRI();
				traceNormal += trace;
				
				convert = new ConvertToHtmlTable(trace);
				traceHTML += convert.convert(2);
				
				traceHTML += "\t</body>\n</html>";
				textPane.setText(traceHTML);
				
				java.util.GregorianCalendar calendar = new GregorianCalendar();
				
				StringBuilder stringBuilder = new StringBuilder();
				stringBuilder.append(calendar.get(Calendar.HOUR) +":");
				stringBuilder.append(calendar.get(Calendar.MINUTE) +":");
				stringBuilder.append(calendar.get(Calendar.SECOND));
				stringBuilder.append("   Trace ");
				stringBuilder.append((listModel.getSize()+1));
			
				stringBuilder.append("[ "+file +", support = " +supportMin + "]");
				listModel.addElement(stringBuilder.toString());
				arrayTraceHTML.add(traceHTML);
				arrayTrace.add(traceNormal);
			}
		});
		buttonPanel.add(startButton); buttonPanel.add(new JLabel());
		
		// panel englobant le panel d'options et de boutons
		JPanel tempPanel2 = new JPanel(new BorderLayout());
		tempPanel2.add(buttonPanel, BorderLayout.SOUTH);
		tempPanel2.add(optionsPanel);
		
		// panel qui contient la liste des resultats
		JPanel resultPanel = new JPanel(new GridLayout(1,1));
		resultPanel.add(scrollPaneList);
		resultPanel.setBorder(BorderFactory.createTitledBorder("Result List(Right & Left click " +
				"for options)"));
		
		// panel west
		JPanel westPanel = new JPanel(new BorderLayout());
		westPanel.add(tempPanel2, BorderLayout.NORTH);
		westPanel.add(resultPanel);
		
		
		// panel qui contient la trace de l'algo
		JPanel centerPanel = new JPanel(new GridLayout(1,1));
		centerPanel.setBorder(BorderFactory.createTitledBorder("Output"));
		centerPanel.add(scrollPane);
		
		// desactivation des composants
		startButton.setEnabled(false);
		supportTextField.setEnabled(false);
		minusButton.setEnabled(false); 
		plusButton.setEnabled(false);
		
		
		// ajout des panel au frame
		add(northPanel, BorderLayout.NORTH);
		add(westPanel, BorderLayout.WEST);
		add(centerPanel, BorderLayout.CENTER);
		
		//pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public static void main(String [] args){
		new Interface();
	}
	
}
