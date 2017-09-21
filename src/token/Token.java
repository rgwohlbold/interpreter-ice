package token;
public class Token {

	private TokenType type;
	private Object value;
	
	public Token(TokenType type, Object value) {
		this.type = type;
		this.value = value;
	}

	@Override
	public String toString() {
		return "Token(" + type + ", " + value + ")";
	}
	
	public TokenType getType() {
		return type;
	}

	public Object getValue() {
		return value;
	}
	
}
