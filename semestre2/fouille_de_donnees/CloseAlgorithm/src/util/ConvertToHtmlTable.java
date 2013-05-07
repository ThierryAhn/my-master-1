package util;

/**
 * Classe ConvertToHtmlTable, permet de formatter un tableau specifique en tableau html.
 * @author ahounou & ayadi.
 * @date  20/03/2013 - 25/03/2013.
 *
 */
public class ConvertToHtmlTable {
	/**
	 * Tableau a transformer en tableau html.
	 */
	private String content;
	
	
	public ConvertToHtmlTable(String content){
		this.content = content;
	}
	
	/**
	 * Retourne un tableau specifique sous forme de tableau html.
	 * @param type permet d'identifier le tableau des ffc, ff des tableaux des regles exactes et 
	 * approximatives.
	 * @return
	 */
	public String convert(int type){
		
		String allTables ="";
		int count = 0;
		
		String [] tab1 = content.split("\n______\n\n");
		
		for(int i = 0; i < tab1.length; i++){
			String buffer = "";
			if(count == 0){
				buffer +="\t\t<div>\n";
				buffer += "\t\t\t <table border=\"1\" class=\"left\">\n";
			}
			else{
				buffer += "\t\t\t <table border=\"1\">\n";
			}
			
			String temp = tab1[i];
			String []tab2 = temp.split("\n");
			String title = tab2[0];
			
			// recuperation du titre du tableau
			buffer += "\t\t\t\t<caption>" + title +"</caption>\n";
			
			
			// recuperaton des titres des colonnes
			buffer += "\t\t\t\t<tr>\n";
			String []tabTitle = tab2[2].split("\\|");
			for(int j = 1; j < tabTitle.length; j++){
				String content = tabTitle[j];
				buffer += "\t\t\t\t\t<th>"+content +"</th>\n";
			}
			buffer += "\t\t\t\t</tr>\n";
			
			// recuperaton des donnees des colonnes
			for(int j = 3; j <  tab2.length-1; j++){
				buffer += "\t\t\t\t<tr>\n";
				String []tabData = tab2[j].split("\\|");
				for(int k = 1; k < tabData.length; k++){
					String data = tabData[k];
					buffer += "\t\t\t\t\t<td>"+data +"</td>\n";
				}
				buffer += "\t\t\t\t</tr>\n";
			}
			
			buffer += "\t\t\t</table>\n";
			count += 1;
			if(type == 1){
				if(count == 2){
					buffer += "\t\t</div><div style=\"clear:left\"> </div>\n";
					count = 0;
				}
			}else{
				buffer += "\t\t</div><div style=\"clear:left\"> </div>\n";
			}
			allTables += buffer;
		}
		return allTables;
	}
}
