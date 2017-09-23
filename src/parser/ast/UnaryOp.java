package parser.ast;

import lexer.token.Token;

public class UnaryOp extends ASTNode {

	private Token op;
	private ASTNode factor;
	
	public UnaryOp(Token op, ASTNode expr) {
		this.op = op;
		this.factor = expr;
	}

	public Token getOp() {
		return op;
	}

	public ASTNode getFactor() {
		return factor;
	}
	
	
}
