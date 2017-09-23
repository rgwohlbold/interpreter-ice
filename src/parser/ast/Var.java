package parser.ast;

import lexer.token.Token;

public class Var extends ASTNode {

	private Token token;
	private String name;
	
	public Var(Token token) {
		this.token = token;
		this.name = (String) token.getValue();
	}

	public Token getToken() {
		return token;
	}

	public String getName() {
		return name;
	}
}
