package token_facade;

/**
 * Class Token
 * @author Nicart
 * @date 16/10/12
 *
 */
public class Token {
	// Note:pour simplifier, les membres ci-dessous sont publiques !
	// La chaine de caracteres representant le token:
	private String	_token;
	// L'identificateur numerique du token:
	private int	_tokenId;
	// Une annotation quelconque:
	private String _annotation;
	
	public Token(String token, int tokenId, String annotation) {
		_token = token;
		_tokenId=tokenId;
		_annotation=annotation;
	}
	
	/**
	 * Return the token
	 * @return _token
	 */
	public String get_token() {
		return _token;
	}
	
	/**
	 * Modify the value of _token
	 * @param _token the new value
	 */
	public void set_token(String _token) {
		this._token = _token;
	}
	
	/**
	 * Return the id of the token
	 * @return _tokenId
	 */
	public int get_tokenId() {
		return _tokenId;
	}
	
	/**
	 * Modify the value of the id
	 * @param _tokenId the new value 
	 */
	public void set_tokenId(int _tokenId) {
		this._tokenId = _tokenId;
	}
	
	/**
	 * Return the value of the annotation
	 * @return _annotation
	 */
	public String get_annotation() {
		return _annotation;
	}
	
	/**
	 * Modify the value of the annotation
	 * @param _annotation the new value
	 */
	public void set_annotation(String _annotation) {
		this._annotation = _annotation;
	}
	
	
} 