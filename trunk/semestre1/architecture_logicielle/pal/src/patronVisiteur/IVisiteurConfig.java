package patronVisiteur;

import java.io.PrintWriter;

import patronComposite.*;


/*
 * IVisiteurConfig : l'interface du visiteur
 * */


public interface IVisiteurConfig {

    public void setSortie(PrintWriter Sortie);
    
    public String getUrl();
    
    public void setUrl(String url);
           
    public void visite(Category c) throws Exception;

    public void visite(Module m);

    public void visite(TextField tf);

    public void visite(CheckField cf);

    public void visite(ChoiceValue cv);

    public void visite(ChoiceField cf);

    public void visite(Info inf);

    public void visite(InfoLine il);
    
}
