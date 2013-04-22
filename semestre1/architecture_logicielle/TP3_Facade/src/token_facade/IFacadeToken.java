package token_facade;

/**
 * Interface ITokenFacade
 * @author ahounfol
 * @date 16/10/12
 *
 */
public interface IFacadeToken {
	// add a new token
	int addNewToken(String token, String annotation);
	// return token identified by tokenId
	String getToken(int tokenId);
	// return tokenId identified by token
	int getTokenId(String token);
	// return the annotation of the token
	String getAnnotation(int tokenId);
	// return the object token identified by tokenId
	Token getTokenMapping(int tokenId);
}
