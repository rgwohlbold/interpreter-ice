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
	
	public Integer visit_BinOp(BinOp node) {
		if (node.getOp().getType() == TokenType.T_PLUS) {
			return (Integer)this.visit(node.getLeft()) + (Integer)this.visit(node.getRight());
		}
		else if (node.getOp().getType() == TokenType.T_MINUS) {
			return (Integer)this.visit(node.getLeft()) - (Integer)this.visit(node.getRight());
		}
		else if (node.getOp().getType() == TokenType.T_MUL) {
			return (Integer)this.visit(node.getLeft()) * (Integer)this.visit(node.getRight());
		}
		else if (node.getOp().getType() == TokenType.T_DIV) {
			return (Integer)this.visit(node.getLeft()) / (Integer)this.visit(node.getRight());
		}
		
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
		return variables.get(varName);	
	}
	
	public Object visit_Assign(Assign node) {
		String var_name = ((Var)node.getLeft()).getName();
		this.variables.put(var_name, this.visit(node.getRight()));
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
