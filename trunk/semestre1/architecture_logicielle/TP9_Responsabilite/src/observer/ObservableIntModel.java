package observer;

import java.util.Observer;

public class ObservableIntModel extends IntModel {
	private DelegatedObservable d;
	
	public ObservableIntModel(){
		d = new DelegatedObservable();
	}
	
	public void addObserver(Observer o){
		d.addObserver(o);
	}
	
	public void removeObserver(Observer o){
		d.deleteObserver(o);
	}
	
	public void inc(){
		super.inc();
		d.setChanged();
		d.notifyObservers();
	}
}
