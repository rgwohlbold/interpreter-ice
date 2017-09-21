import ast.ASTNode;
import ast.Assign;
import ast.BinOp;
import ast.Compound;
import ast.NoOp;
import ast.UnaryOp;
import ast.Var;
import token.Token;
import token.TokenType;

public class Parser {

	private Lexer lexer;
	private Token currentToken;
	
	public Parser(Lexer lexer) {
		this.lexer = lexer;
		currentToken = lexer.getNextToken();
	}
	
	public void eat(TokenType type) {
		if (type == currentToken.getType()) {
			currentToken = lexer.getNextToken();
		}
		else {
			throw new RuntimeException("unexpected token");
		}
	}
	
	public ASTNode factor() {
		Token token = currentToken;
		if (currentToken.getType() == TokenType.T_INTEGER) {
			this.eat(TokenType.T_INTEGER);
			return new ast.Num(token);
		}
		else if (currentToken.getType() == TokenType.T_LPARENT) {
			this.eat(TokenType.T_LPARENT);
			ASTNode result = expr();
			this.eat(TokenType.T_RPARENT);
			return result;
		}
		else if (currentToken.getType() == TokenType.T_PLUS) {
			this.eat(TokenType.T_PLUS);
			ASTNode result = factor();
			result = new UnaryOp(token, result);
			return result;			
		}
		else if (currentToken.getType() == TokenType.T_MINUS) {
			this.eat(TokenType.T_MINUS);
			ASTNode result = factor();
			result = new UnaryOp(token, result);
			return result;
		}
		else if (currentToken.getType() == TokenType.T_ID) {
			return variable();
		}
		
		
		throw new RuntimeException("Parser Error: No matching token: " + currentToken.getType());
	}
	
	public ASTNode term() {
		// term the reuslt of a * / operation of 1+ factors
		ASTNode result = factor();
		
		while(tokenTypeIn(TokenType.T_MUL, TokenType.T_DIV)) {
			
			Token op = currentToken;
			
			if (op.getType() == TokenType.T_MUL) {
				eat(TokenType.T_MUL);
			}
			else if (op.getType() == TokenType.T_DIV) {
				eat(TokenType.T_DIV);
			}
			
			result = new BinOp(result, op, factor());
		}
		
		return result;
	}
	
	public ASTNode expr() {
		ASTNode result = term();	
		while (tokenTypeIn(TokenType.T_PLUS, TokenType.T_MINUS)) {
			Token op = currentToken;
			if (currentToken.getType() == TokenType.T_PLUS) {
				eat(TokenType.T_PLUS);
			}
			else if (currentToken.getType() == TokenType.T_MINUS) {
				eat(TokenType.T_MINUS);
			}
			result = new BinOp(result, op, term());
		}
		
		return result;
	}
	
	public ASTNode variable() {
		ASTNode node = new Var(currentToken);
		this.eat(TokenType.T_ID);
		return node;
	}
	
	public ASTNode assignmentStatement() {
		ASTNode left = this.variable();
		Token token = currentToken;
		this.eat(TokenType.T_ASSIGN);
		ASTNode right = this.expr();
		return new Assign(left, token, right);
	}
	
	public ASTNode empty() {
		return new NoOp();
	}
	
	public ASTNode statement() {
		if (this.currentToken.getType() == TokenType.T_ID) {
			return this.assignmentStatement();
		}
		return empty();
	}
	
	public ASTNode statementList() {
		Compound rootNode = new Compound();
		while (currentToken.getType() == TokenType.T_ID) {
			ASTNode node = this.statement();
			this.eat(TokenType.T_NEWLINE);
			rootNode.getChildren().add(node);
		}
		return rootNode;
		
	}
	
	public ASTNode program() {
		ASTNode result = statementList();
		return result;
	}
	
	private boolean tokenTypeIn(TokenType... types) {
		if (currentToken == null) {
			return false;
		}
		
		for (int i = 0; i < types.length; i++) {
			if (currentToken.getType() == types[i]) {
				return true;
			}
		}
		return false;
	}
	
	public ASTNode parse() {
		ASTNode root = this.program();
		//if (currentToken.getType() != TokenType.T_EOF) {
		//	throw new RuntimeException("Parser error");
		//}
		return root;
	}
}
