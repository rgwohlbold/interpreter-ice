package main;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import interpreter.Interpreter;
import lexer.Lexer;
import lexer.token.Token;
import lexer.token.TokenType;
import parser.Parser;

public class Main {

	public static final File fileName = new File("source.ice");
	
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
		
		//System.out.println(result);
		//printToken(lexer);
		
		Lexer lexer = new Lexer(result);
		Parser parser = new Parser(lexer);
		Interpreter interpreter = new Interpreter(parser);
		interpreter.interpret();
		System.out.println(interpreter.variables);

	}
	
	public static void printToken(Lexer lexer) {
		Token currentToken = lexer.getNextToken();
		while (currentToken.getType() != TokenType.T_EOF) {
			System.out.println(currentToken);
			currentToken = lexer.getNextToken();
		}
	}
}
