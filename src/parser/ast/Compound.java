package parser.ast;

import java.util.ArrayList;
import java.util.List;

public class Compound extends ASTNode {

	private List<ASTNode> children;
	
	public Compound() {
		this.children = new ArrayList<ASTNode>();
	}
	
	public List<ASTNode> getChildren() {
		return this.children;
	}
}
