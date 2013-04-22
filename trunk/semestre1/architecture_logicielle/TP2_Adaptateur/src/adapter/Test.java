package adapter;

import java.util.*;

/**
 * Main class for test.
 * @author ahounfol
 * @date 9/10/2012
 *
 */
public class Test {

	
	public static void afficheTokens ( Iterator<String> it ){
		int token_num=0;
		while (it.hasNext() ){
			String token=it.next();
			System.out.println(token_num+" : "+token ) ;
			token_num++;
		}
	}
	
	public static void main ( String [] args ){
		/* LinkedList<String> tokenList=new LinkedList<String >( ) ;
		tokenList.add("Ceci") ;
		tokenList.add("est") ;
		tokenList.add("une") ;
		tokenList.add("liste") ;
		tokenList.add("de") ;
		tokenList.add("Tokens") ;
		afficheTokens(tokenList.iterator()) ; */
		
		StringTokenizer st = new StringTokenizer("Ceci est un texte a decouper");
		afficheTokens(new Adapter(st));
	}

}
