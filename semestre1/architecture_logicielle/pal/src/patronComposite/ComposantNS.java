package patronComposite;

/*
 * Interface des Composants ayant le name et le settingKey comme attributs
 * */

public interface ComposantNS extends ComposantN {
    
    public String getSettingKey();
     
    public void setSettingKey(String settingKey);
    
}
