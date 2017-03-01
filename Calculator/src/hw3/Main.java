/* Create an AST, then invoke our interpreter. */
//import expression.Interpreter;
package hw3;

import hw3.parser.Parser;
import hw3.lexer.Lexer;
import hw3.node.*;

import java.io.*;

public class Main {

	private static PrintWriter out;
	private static boolean verbose;

	public static void main(String[] args) {
		int fileCount = args.length;
		verbose = false;
		if (args.length < 1 || args.length > 3)
			usageErr();
		if (args[args.length - 1].equals("-v")) {
			verbose = true;
			fileCount--;
		} else {
			if (args.length == 3)
				usageErr();
		}

		File infile = new File(args[0]);
		if (!infile.canRead()) {
			System.err.println("Cannot read file: " + args[0]);
		}
		if (fileCount > 1) {
			try {
				out = new PrintWriter(args[1]);
			} catch (FileNotFoundException e) {
				System.out.println("Cannot open file for writing: " + args[1]);
				System.exit(1);
			}
		} else
			out = new PrintWriter(new OutputStreamWriter(System.out));
		process(infile);
		out.flush();
	}

	private static void process(File infile) {
		try {
			Parser parser = new Parser(new Lexer(new PushbackReader(
					new FileReader(infile), 1024)));
			Start start = parser.parse();
			PExprList list = start.getPExprList();
			while (list instanceof ASomeExprList) {
				ASomeExprList some = (ASomeExprList) list;
				out.println(evaluate(some.getFirst()));
				list = some.getRest();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private static double evaluate(Node n) {
		if (n instanceof AAddExpr) {
			AAddExpr e = (AAddExpr) n;
			double left = evaluate(e.getLeft());
			double right = evaluate(e.getRight());
			return left + right;
		}
		if (n instanceof ASubExpr) {
			ASubExpr e = (ASubExpr) n;
			double left = evaluate(e.getLeft());
			double right = evaluate(e.getRight());
			return left - right;
		}
		if (n instanceof ATermExpr) {
			ATermExpr e = (ATermExpr) n;
			return evaluate(e.getTerm());
		}
		if (n instanceof AMulTerm) {
			AMulTerm e = (AMulTerm) n;
			double left = evaluate(e.getLeft());
			double right = evaluate(e.getRight());
			return left * right;
		}
		if (n instanceof ADivTerm) {
			ADivTerm e = (ADivTerm) n;
			double left = evaluate(e.getLeft());
			double right = evaluate(e.getRight());
			return left / right;
		}
		if (n instanceof AAtomTerm) {
			AAtomTerm e = (AAtomTerm) n;
			return evaluate(e.getAtom());
		}
		if (n instanceof ANumberAtom) {
			ANumberAtom num = (ANumberAtom) n;
			return Double.parseDouble(num.getNumber().getText());
		}
		if (n instanceof AParenAtom) {
			AParenAtom p = (AParenAtom) n;
			return evaluate(p.getExpr());
		}
		throw new RuntimeException("Unexpected Node:" + n);
	}

	private static void usageErr() {
		System.err
				.println("usage: java wordCount.Main inputFile [outputFile] [-v]");
		System.exit(1);
	}
}