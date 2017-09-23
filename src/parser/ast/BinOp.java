package parser.ast;

import lexer.token.Token;

public class BinOp extends ASTNode {

	private ASTNode left, right;
	private Token op;
	
	public BinOp(ASTNode left, Token op, ASTNode right) {
		this.left = left;
		this.op = op;
		this.right = right;
	}

	public ASTNode getLeft() {
		return left;
	}

	public Token getOp() {
		return op;
	}

	public ASTNode getRight() {
		return right;
	}	
}
