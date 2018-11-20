package CalculateMetrics;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.expr.ConditionalExpr;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.ObjectCreationExpr;
import com.github.javaparser.ast.stmt.BreakStmt;
import com.github.javaparser.ast.stmt.ContinueStmt;
import com.github.javaparser.ast.stmt.DoStmt;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.ReturnStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class LOCforClassCalculator {
	private ClassOrInterfaceDeclaration clazz;
	private int lineOfCodes;
	private int numberOfStatements;
	
	public LOCforClassCalculator(ClassOrInterfaceDeclaration clazz) {
		this.clazz=clazz;
	}
	
	public void doOperation() {
		LineCounter();
		//countStatements();
	}
	
	public void LineCounter(){
          lineOfCodes = clazz.getRange().get().end.line-clazz.getRange().get().begin.line+1;
    }
	
	private void countStatements() {
        clazz.accept(new VoidVisitorAdapter<Void>() {
            @Override
            public void visit(AssignExpr n, Void arg) {
                numberOfStatements++;
                super.visit(n, arg);
            }

            @Override
            public void visit(ContinueStmt n, Void arg) {
                numberOfStatements++;
                super.visit(n, arg);
            }

            @Override
            public void visit(BreakStmt n, Void arg) {
                numberOfStatements++;
                super.visit(n, arg);
            }

            @Override
            public void visit(DoStmt n, Void arg) {
                numberOfStatements++;
                super.visit(n, arg);
            }

            @Override
            public void visit(ReturnStmt n, Void arg) {
                numberOfStatements++;
                super.visit(n, arg);
            }

            @Override
            public void visit(ObjectCreationExpr n, Void arg) {
                numberOfStatements++;
                super.visit(n, arg);
            }

            @Override
            public void visit(MethodCallExpr n, Void arg) {
                numberOfStatements++;
                super.visit(n, arg);
            }

            @Override
            public void visit(IfStmt n, Void arg) {
                numberOfStatements++;
                super.visit(n, arg);
            }

            @Override
            public void visit(ConditionalExpr n, Void arg) {
                numberOfStatements++;
                super.visit(n, arg);
            }
        }, null);
    }
	
	public int getLOC() {
		return lineOfCodes;
	}
	
	public int getStatements() {
		return numberOfStatements;
	}
}
