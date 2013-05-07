package tdd;

public interface ITeleCommande {
	public void orienteToiAuNord ();
	public void orienteToiAuSud ();
	public void orienteToiALEst ();
	public void orienteToiALOuest ();
	public void avanceDe (int cases) throws CannotGoThereException ;
	public void avanceEtRebonditDe (int cases);
}
