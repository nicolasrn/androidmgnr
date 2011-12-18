package com.android.metier;

/**
 * classe qui permet de se repérer dans la grille
 */
public class Coordonnee 
{
	public int x;
	public int y;
	
	/**
	 * construit l'objet avec l'abscisse et l'ordonnée passé en parametre
	 * @param x
	 * @param y
	 */
	public Coordonnee(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	/**
	 * construit l'objet a partir d'une String sous la forme x;y 
	 * @param x
	 */
	public Coordonnee (String x)
	{
		this.x= Integer.parseInt(x.substring(0,x.indexOf(";")));
		this.y= Integer.parseInt(x.substring(x.indexOf(";")+1));
		
	}
	
	/**
	 * @return Coordonnee
	 */
	public Coordonnee CoordonneeGauche()
	{
		return new Coordonnee(x-1, y);
	}
	
	/**
	 * @return Coordonnee
	 */
	public Coordonnee CoordonneeDroite()
	{
		return new Coordonnee(x+1, y);
	}
	
	/**
	 * @return Coordonnee
	 */
	public Coordonnee CoordonneeHaut()
	{
		return new Coordonnee(x, y+1);
	}
	
	/**
	 * @return Coordonnee
	 */
	public Coordonnee CoordonneeBas()
	{
		return new Coordonnee(x, y-1);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return x + ";" + y ;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordonnee other = (Coordonnee) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
}
