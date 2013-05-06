package component.view;

import java.awt.*;
import javax.swing.*;

import helper.UResource;

public class SimulationFrame extends JDialog{
	
	private static final long serialVersionUID = 1L;
	
	JPanel content;
	Dimension dimImg;
	public SimulationFrame(Component owner,String image){
		
		super((Frame)owner,true);
		this.setUndecorated(true);
		
		final ImageIcon icone = UResource.createIcon(image, "resource"); 
		
		content = new JPanel(){
			
			private static final long serialVersionUID = 1L;

			@Override
			public void paintComponent(Graphics cr){
				super.paintComponent(cr);
				cr.drawImage(icone.getImage(), 0, 0, icone.getIconWidth(), icone.getIconHeight(), this);
			}
		};
		
		Container container = this.getContentPane();
		
		container.add(content);
		dimImg = new Dimension(icone.getIconWidth(), icone.getIconHeight());
		content.setPreferredSize(dimImg);
		
		this.setSize(dimImg);
		this.setVisible(false);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);		
	}
	
	
	public Dimension getDimImg(){
		return dimImg;
	}
	
	public void showDialog(int x, int y){
		this.setSize(dimImg);
		this.setLocation(x,y);
		this.setVisible(true);
	}
	
	
	
}
