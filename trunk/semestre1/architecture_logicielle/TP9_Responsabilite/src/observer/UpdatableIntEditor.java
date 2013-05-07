package observer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class UpdatableIntEditor extends IntEditor implements Observer{
	
	private ObservableIntModel model;
	
	public UpdatableIntEditor(final ObservableIntModel o){
		model = o;
		
		model.addObserver(this);
		
		setIncListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				model.inc();
			}
			
		});
	}
	
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		showValue(model.getValue());
	}

}
