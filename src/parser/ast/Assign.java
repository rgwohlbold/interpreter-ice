package parser.ast;

import lexer.token.Token;

public class Assign extends ASTNode {

	private ASTNode left, right;
	private Token op;
	
	public Assign(ASTNode left, Token op, ASTNode right) {
		this.left = left;
		this.right = right;
		this.op = op;
	}

	public ASTNode getLeft() {
		return left;
	}

	public ASTNode getRight() {
		return right;
	}

	public Token getOp() {
		return op;
	}
	
	
}
