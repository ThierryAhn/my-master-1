package formulaire;

public class TestFormulaire {
	public static void main(String ... args){
		IFormulaire formHtml = new FormulaireHTML();
		IFormulaire formPdf = new FormulairePDF();
		
		Formulaire formFrenchHtml = new FormulaireFrancais(formHtml, "Fr HTML");
		Formulaire formFrenchPdf = new FormulaireFrancais(formPdf, "Fr PDF");
		
		Formulaire formEnglishHtml = new FormulaireEnglish(formHtml, "En HTML");
		Formulaire formEnglishPdf = new FormulaireEnglish(formPdf, "En PDF");
		
		System.out.println(formFrenchHtml.send());
		
		System.out.println("\n" + formEnglishHtml.send());
		
		System.out.println("\n" + formFrenchPdf.send());
		
		System.out.println("\n" + formEnglishPdf.send());
		
	}	
}
