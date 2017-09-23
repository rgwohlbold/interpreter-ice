package parser.ast;

public class Program extends ASTNode {

	private ASTNode statementList;
	
	public Program(ASTNode statementList) {
		this.statementList = statementList;
	}

	public ASTNode getStatementList() {
		return statementList;
	}
	
	
}
