package token_facade;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 * Implementation of the interface ITokenInterface.
 * @author ahounfol
 * @date 16/10/12
 *
 */
public class FacadeToken implements IFacadeToken{
	
	private Vector<Token> vector;
	private Map<String, Token> map;
	
	public FacadeToken(){
		vector = new Vector<Token>();
		map = new HashMap<String, Token>();
	}
	
	/**
	 * Add a new token
	 * @param token
	 * @param annotation annotation of the token
	 * return the id of the token
	 */
	public int addNewToken(String token, String annotation) {
		Token temp = new Token(token, vector.size(), annotation);
		
		// if the token exist
		if(map.containsKey(token)){
			return -1;
		}
		
		// adding of the token with his id in the vector
		vector.add(temp);
		
		// adding of the token in the map
		map.put(token, temp);
		
		return vector.size();
	}
	
	
	/**
	 * Return token
	 * @param tokenId the id of the token
	 * return _token
	 */
	public String getToken(int tokenId) {
		return vector.get(tokenId).get_token();
	}
	
	/**
	 * Return the id of the token
	 * @param token
	 * return _tokenId
	 */
	public int getTokenId(String token) {
		return map.get(token).get_tokenId();
	}
	
	/**
	 * Return the annotation of the token
	 * @param tokenId the id of the token
	 * return _annotation
	 */
	public String getAnnotation(int tokenId) {
		return vector.get(tokenId).get_annotation();
	}
	
	/**
	 * Return the object token
	 * @param tokenId the id of the token
	 * return token
	 */
	public Token getTokenMapping(int tokenId) {
		return vector.get(tokenId);
	}
	
	
}
