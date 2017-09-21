import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import ast.ASTNode;

public class NodeVisitor {

	public Object visit(ASTNode node) {
		String method_name = "visit_" + node.getClass().getSimpleName();
		Method visitor = null;
		try {
			visitor = this.getClass().getMethod(method_name, node.getClass());
			return visitor.invoke(this, node);
		} catch (NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
		
	}
}
