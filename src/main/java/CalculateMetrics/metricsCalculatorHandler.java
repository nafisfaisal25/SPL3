package CalculateMetrics;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.symbolsolver.JavaSymbolSolver;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.JavaParserTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;

import ExploreFiles.FileExplorer;

public class metricsCalculatorHandler {
	File file;
	
	public metricsCalculatorHandler(File file) {
		this.file=file;
	}
	
	public void cyclomaticComplexityCaclHandler() throws FileNotFoundException{
		try {
            new VoidVisitorAdapter<Object>() {
                @Override
                public void visit(ClassOrInterfaceDeclaration n, Object arg) {
                    super.visit(n, arg);
                    for (MethodDeclaration method : n.getMethods()) {
            			CyclomaticComplexityCalculator c=new CyclomaticComplexityCalculator(method);
            			System.out.println("Method Name: "+ method.getName() + "\n" + "CYC: " +c.calculateComplexity());
            		}
                }
            }.visit(JavaParser.parse(file), null);
            System.out.println(); // empty line
        } catch (IOException e) {
            new RuntimeException(e);
        }
        
	}
	
	public void weightedMethodCountCalcHandler() throws FileNotFoundException{
		try {
            new VoidVisitorAdapter<Object>() {
                @Override
                public void visit(ClassOrInterfaceDeclaration n, Object arg) {
                    super.visit(n, arg);
                    WeightedMethodCountCalculator w=new WeightedMethodCountCalculator(n);
                    System.out.println("Class Name: " + n.getName() + "\n" + "WMC: " + w.calculateWeightedMethodCount());
                }
            }.visit(JavaParser.parse(file), null);
            System.out.println(); // empty line
        } catch (IOException e) {
            new RuntimeException(e);
        }
        
	}
	
	public void ATFDCalcHandler(Set <String> dotJavaContainer){
		
		System.out.println(dotJavaContainer);
		TypeSolver reflectionTypeSolver = new ReflectionTypeSolver();
        CombinedTypeSolver combinedSolver = new CombinedTypeSolver();
        combinedSolver.add(reflectionTypeSolver);
        for (String filePath : dotJavaContainer) {
        	//System.out.println(filePath);
			combinedSolver.add(new JavaParserTypeSolver(new File(filePath)));
		}
        
		JavaSymbolSolver symbolSolver = new JavaSymbolSolver(combinedSolver);
		JavaParser.getStaticConfiguration().setSymbolResolver(symbolSolver);
		try {
            new VoidVisitorAdapter<Object>() {
                @Override
                public void visit(CompilationUnit n, Object arg) {
                    super.visit(n, arg);
                    ATFDCalculator a=new ATFDCalculator(n);
                    a.doOperation();
                }
            }.visit(JavaParser.parse(file), null);
            System.out.println(); // empty line
        } catch (IOException e) {
            new RuntimeException(e);
        }
        
	}
	
	
	
/*	
	
	    public static void compilationUnit(File file) {
	    	
			
	        try {
	            new VoidVisitorAdapter<Object>() {
	                @Override
	                public void visit(CompilationUnit n, Object arg) {
	                    super.visit(n, arg);
	                    
	                    //ATFD
	                    ATFDCalculator a=new ATFDCalculator(n);
	                    a.doOperation();
	                }
	            }.visit(JavaParser.parse(file), null);
	            System.out.println(); // empty line
	        } catch (IOException e) {
	            new RuntimeException(e);
	        }
	    }
	    
	*/
}
