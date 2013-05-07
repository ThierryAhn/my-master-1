package observer;

import java.awt.*;
import java.awt.event.*;

class IntEditor extends Frame {
	private static int counter=1;
	private TextField tf = new TextField(10);
	private Button incButton = new Button("+");
	private Button decButton = new Button("-");
	private Button randomButton = new Button("Aléatoire");

	protected void showValue(int val) {
		tf.setText(""+val);
	}

	protected void setIncListener(ActionListener al) {
		incButton.addActionListener(al);
	}

	protected void setDecListener(ActionListener al) {
		decButton.addActionListener(al);
	}

	protected void setRandomListener(ActionListener al) {
		randomButton.addActionListener(al);
	}

		
	public IntEditor() {
		tf.setEditable(false);

		Panel tfPanel = new Panel();
		tfPanel.add(tf);
		tfPanel.add(incButton);
		tfPanel.add(decButton);
		tfPanel.add(randomButton);

		add("North", tfPanel);

		setSize(300, 75);
		setTitle("Éditeur d'entier n°"+(counter++));
		showValue(11);
		
		// Closing any view will quit :
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		setVisible(true);		
	}
}

