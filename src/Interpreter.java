import java.util.HashMap;
import java.util.Map;

import ast.ASTNode;
import ast.Assign;
import ast.BinOp;
import ast.Compound;
import ast.NoOp;
import ast.Num;
import ast.UnaryOp;
import ast.Var;
import token.TokenType;

public class Interpreter extends NodeVisitor {

	private Parser parser;
	public Map<String, Object> variables;
	
	public Interpreter(Parser parser) {
		this.parser = parser;
		this.variables = new HashMap<String, Object>();
	}
	
	public Object visit_BinOp(BinOp node) {
		
		Integer left = (Integer) this.visit(node.getRight());
		Integer right = (Integer) this.visit(node.getLeft());
		
		if (node.getOp().getType() == TokenType.T_PLUS) {
			return left + right;
		}
		else if (node.getOp().getType() == TokenType.T_MINUS) {
			return left - right;
		}
		else if (node.getOp().getType() == TokenType.T_MUL) {
			return left * right;
		}
		else if (node.getOp().getType() == TokenType.T_FDIV) {
			return left / right;
		}
		/*
		else if (node.getOp().getType() == TokenType.T_IDIV) {
			return (Integer)this.visit(node.getLeft()) / (Integer)this.visit(node.getRight());
		}
		*/
		
		return null;
	}
	
	public Integer visit_UnaryOp(UnaryOp node) {
		if (node.getOp().getType() == TokenType.T_PLUS) {
			return (Integer)this.visit(node.getFactor());
		}
		else if (node.getOp().getType() == TokenType.T_MINUS) {
			return -(Integer)this.visit(node.getFactor());
		}
		
		return null;
	}
	
	public Object visit_Compound(Compound node) {
		for (ASTNode child : node.getChildren()) {
			this.visit(child);
		}
		return null;
	}
	
	public Object visit_NoOp(NoOp node) {
		return null;
	}
	
	public Object visit_Var(Var node) {
		String varName = node.getName();
		Object value = variables.get(varName);
		if (value == null) {
			throw new InvalidIdentifierException(varName);
		}
		return value;
	}
	
	public Object visit_Assign(Assign node) {
		String var_name = ((Var)node.getLeft()).getName();
		Object result = this.visit(node.getRight());
		if (this.variables.containsKey(var_name)) {
			this.variables.remove(var_name);
		}
		this.variables.put(var_name, result);
		return null;
	}
	
	public Integer visit_Num(Num node) {
		return node.getValue();
	}
	
	public Object interpret() {
		ASTNode tree = parser.parse();
		return this.visit(tree);
	}
}
