<h1>interpreter-ice</h1>
<h3>Description</h3>
This is a simple Java interpreter and parser, inspired by Ruslan Spivak's tutorials on writing a Pascal interpreter in Python. 
The language syntax (currently named iCe) is just C without semicolons (who needs those anyway?). The interpreter currently supports arithmetic expressions and variable assignments and prints out all assigned variables.
<h3>Structure</h3>
The Program is divided into three parts:
<ol>
<li>The <b>Lexer</b>, also called <b>Tokenizer</b>, reads in the Text and converts it into Tokens with defined TokenTypes.</li>
<li>The <b>Parser</b> receives the Tokens from the Lexer and creates an <b>Abstract Syntax Tree</b> (<b>AST</b>)</li>
<li>The <b>Interpreter</b> gets the Abstract Syntax Tree from the Parser and recursively "visits" every Node and executes the actions specified.</li>
</ol>
<h3>Links</h3>
<a href="https://ruslanspivak.com/lsbasi-part1/">Ruslan Spivak's tutorials</a>
