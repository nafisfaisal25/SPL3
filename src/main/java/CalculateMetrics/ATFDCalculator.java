package CalculateMetrics;

import java.util.List;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.AssignExpr;
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
	private CompilationUnit declaredClass;
	
	public ATFDCalculator(CompilationUnit declaredClass) {
		//System.out.println(" * " + declaredClass.getName());
		this.declaredClass=declaredClass;
	}
	public void doOperation() {
		
		visit(declaredClass,null);
		List<FieldDeclaration> a =declaredClass.findAll(FieldDeclaration.class);
		getTypeofReferences();
		
	}
	
	@Override
	public void visit(MethodCallExpr n, Void arg) {
        
        super.visit(n, arg);
        //n.getN
    }
	
	

	public void getTypeofReferences() {
		
		declaredClass.findAll(AssignExpr.class).forEach(ae -> {
			ResolvedType resolvedType = ae.calculateResolvedType();
			System.out.println(ae.toString() + " is a: " + resolvedType);
			
		});
		//System.out.println(declaredClass);
		//List<FieldDeclaration> fieldDeclaration = declaredClass.findAll(FieldDeclaration.class);
		//System.out.println("Field type: " + fieldDeclaration.getVariables().get(0).getType().resolve().asReferenceType().getQualifiedName());
		//if(fieldDeclaration.size()!=0)
		//System.out.println("Field type: " + fieldDeclaration.get(0).getVariables().get(0).getType().resolve());
			 
	}
	
	
	
}
