package CalculateMetrics;

import java.util.List;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.expr.FieldAccessExpr;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.resolution.types.ResolvedType;
import com.github.javaparser.symbolsolver.JavaSymbolSolver;
import com.github.javaparser.symbolsolver.javaparser.Navigator;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;

import ExploreFiles.FileExplorer;

public class ATFDCalculator extends VoidVisitorAdapter<Void> {
	private CompilationUnit cu;
	
	public ATFDCalculator(CompilationUnit cu) {
		//System.out.println(" * " + declaredClass.getName());
		this.cu=cu;
	}
	public void doOperation() {
		
		//visit(cu,null);
		//List<FieldDeclaration> a =cu.findAll(FieldDeclaration.class);
		//getTypeofReferences();
		variableCallFinder();
		
	}
	
	@Override
	public void visit(MethodCallExpr n, Void arg) {
        
        super.visit(n, arg);
        System.out.println(n);
    }
	
	public void variableCallFinder() {
		List<FieldAccessExpr> fieldCallList=cu.findAll(FieldAccessExpr.class);
		for (FieldAccessExpr fieldAccessExpr : fieldCallList) {
			System.out.println(fieldAccessExpr.getScope().calculateResolvedType());
		}
	}
	
	

	public void getTypeofReferences() {
		/*
		declaredClass.findAll(AssignExpr.class).forEach(ae -> {
			ResolvedType resolvedType = ae.calculateResolvedType();
			System.out.println(ae.toString() + " is a: " + resolvedType);
			
		});
		System.out.println(declaredClass);*/
		List<FieldDeclaration> fieldDeclaration = cu.findAll(FieldDeclaration.class);
		//System.out.println("Field type: " + fieldDeclaration.getVariables().get(0).getType().resolve().asReferenceType().getQualifiedName());
		if(fieldDeclaration.size()!=0) {
			System.out.println("Field type: " + fieldDeclaration.get(0).getVariables().get(0).getType().resolve().asReferenceType().getQualifiedName());
			System.out.println(fieldDeclaration.get(0).getVariables().get(0).getType().resolve().asReferenceType());
		}	 
	}
	
	
	
}
