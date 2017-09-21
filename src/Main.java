import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {

	public static final File fileName = new File("source.rs");
	
	public static void main(String[] args) throws IOException {
		FileReader fr = new FileReader(fileName);
		BufferedReader br = new BufferedReader(fr);
		
		String result = "";
		String read = br.readLine();
		while(read != null) {
			result += read + "\n";
			read = br.readLine();
		}
		br.close();
		
		System.out.println(result);
		
		Lexer lexer = new Lexer(result);
		Parser parser = new Parser(lexer);
		Interpreter interpreter = new Interpreter(parser);
		interpreter.interpret();
		System.out.println(interpreter.variables);
		/*
		Token currentToken = lexer.getNextToken();
		while (currentToken.getType() != TokenType.T_EOF) {
			System.out.println(currentToken);
			currentToken = lexer.getNextToken();
		}
		*/
	}
}
