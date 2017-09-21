package ast;

import token.Token;

public class Num extends ASTNode {

	private int value;
	private Token token;
	
	public Num(Token token) {
		this.token = token;
		this.value = (int) token.getValue();
	}

	public int getValue() {
		return value;
	}

	public Token getToken() {
		return token;
	}
}
