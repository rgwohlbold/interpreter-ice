package lexer;


import lexer.token.Token;
import lexer.token.TokenType;

public class Lexer {

	private String text;
	private int pos;
	private Character currentChar;
	
	public Lexer(String text) {
		this.text = text;
		this.pos = 0;
		this.currentChar = text.charAt(pos);
	}
	
	private void advance() {
		pos++;
		try {
			currentChar = this.text.charAt(pos);
		}
		catch (StringIndexOutOfBoundsException e) {
			currentChar = null;
		}
		
	}
	
	public void skipWhitespace() {
		while(currentChar != null && currentChar == ' ') {
			this.advance();
		}
	}
	
	public Token getNextToken() {
		
		this.skipWhitespace();
		
		// End of file handling
		if (currentChar == null) {
			return new Token(TokenType.T_EOF, null);
		}
		
		// Arithmetic operators
		if (currentChar == '+') {
			this.advance();
			return new Token(TokenType.T_PLUS, "+");
		}
		else if (currentChar == '-') {
			this.advance();
			return new Token(TokenType.T_MINUS, "-");
		}
		else if (currentChar == '*') {
			this.advance();
			return new Token(TokenType.T_MUL, "*");
		}
		else if (currentChar == '/') {
			this.advance();
			/*
			if (this.peek() == '/') {
				this.advance();
				return new Token(TokenType.T_IDIV, "//");
			}
			*/
			return new Token(TokenType.T_FDIV, "/");
		}
		
		if (currentChar == '(') {
			this.advance();
			return new Token(TokenType.T_LPARENT, "(");
		}
		else if (currentChar == ')') {
			this.advance();
			return new Token(TokenType.T_RPARENT, ")");
		}
		
		if (currentChar == '\n') {
			this.advance();
			return new Token(TokenType.T_NEWLINE, "\n");
		}
		
		if (currentChar == '{') {
			this.advance();
			return new Token(TokenType.T_LBRACK, "{");
		}
		else if (currentChar == '}') {
			this.advance();
			return new Token(TokenType.T_RBRACK, "}");
		}
		
		if (currentChar == '=') {
			this.advance();
			if (this.peek() == '=') {
				this.advance();
				return new Token(TokenType.T_CMPEQ, "==");
			}
			// TODO: Comparison operators
			return new Token(TokenType.T_ASSIGN, "=");
		}
		
		if (Character.isAlphabetic(currentChar)) {
			return identifier();
		}
		
		// Integer handling
		if (Character.isDigit(currentChar)) {
			return new Token(TokenType.T_INTEGER, this.integer());
		}
		
		throw new RuntimeException("Parser Error!");
	}
	
	private Character peek() {
		int peekPos = this.pos + 1;
		if (peekPos > text.length() - 1) {
			return null;
		}
		return text.charAt(peekPos);
	}
	
	private Token identifier() {
		String result = "";
		while (currentChar != null && (Character.isAlphabetic(currentChar) || Character.isDigit(currentChar))) {
			result += currentChar;
			this.advance();
		}
		
		return new Token(TokenType.T_ID, result);
	}
	
	private Integer integer() {
		String result = "";
		while(currentChar != null && Character.isDigit(currentChar)) {
			result += currentChar;
			this.advance();
		}
		return Integer.parseInt(result);
	}
}
