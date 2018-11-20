package CalculateMetrics;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.lang.model.element.VariableElement;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.symbolsolver.JavaSymbolSolver;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.JavaParserTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;

import ExploreFiles.ClassExplorer;
import ExploreFiles.FileExplorer;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.LocalVariableTypeAttribute;
import javassist.compiler.ast.Variable;

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
	
	public void ATFDCalcHandler(Set<String>allClassName,JavaParser parser,String className){
		
		try {
            new VoidVisitorAdapter<Object>() {
            	
                @Override
                public void visit(CompilationUnit n, Object arg) {
                    super.visit(n, arg);
                    ATFDCalculator a=new ATFDCalculator(n,className,allClassName);
                    a.doOperation();
                    int numberOfATFD=a.getATFD();
                   
                    System.out.println("Class Name: " + className + "\n" + "ATFD: " + numberOfATFD);
                
                    
                }
                
                
            }.visit(parser.parse(file), null);
            
        } catch (IOException e) {
            new RuntimeException(e);
        }
        
	}
	
	public void NOAVCalcHandler(Set<String>allClassName,JavaParser parser,String className) {
		
		try {
            new VoidVisitorAdapter<Object>() {
            
                @Override
                public void visit(ClassOrInterfaceDeclaration n, Object arg) {
                    super.visit(n, arg);
                    for (MethodDeclaration method : n.getMethods()) {
            			//CyclomaticComplexityCalculator c=new CyclomaticComplexityCalculator(method);
            			NOAVCalculator a=new NOAVCalculator(method,className,allClassName);
            			a.doOperation();
            			System.out.println("Method: " + method.getNameAsString() + "\n" + "NOAV: " + a.getNOAV());
                    	
                    }
                }
               
            }.visit(JavaParser.parse(file), null);
            
        } catch (IOException e) {
            new RuntimeException(e);
        }
	}
	
	public void LOCforMethodcalcHandler(Set<String>allClassName,JavaParser parser,String className) {
		
		try {
            new VoidVisitorAdapter<Object>() {
            
                @Override
                public void visit(ClassOrInterfaceDeclaration n, Object arg) {
                    super.visit(n, arg);
                    for (MethodDeclaration method : n.getMethods()) {
            			//CyclomaticComplexityCalculator c=new CyclomaticComplexityCalculator(method);
            			LOCforMethodCalculator a=new LOCforMethodCalculator(method);
            			a.doOperation();
            			System.out.println("Method: " + method.getNameAsString() + "\n" + "LOC: " + a.getLOC());
                    	
                    }
                }
               
            }.visit(JavaParser.parse(file), null);
            
        } catch (IOException e) {
            new RuntimeException(e);
        }
	}
	
	public void LOCforClassCalcHandler(Set<String>allClassName,JavaParser parser,String className) {
		
		try {
            new VoidVisitorAdapter<Object>() {
            
                @Override
                public void visit(ClassOrInterfaceDeclaration n, Object arg) {
                    super.visit(n, arg);
                    
                    LOCforClassCalculator a=new LOCforClassCalculator(n);
            		a.doOperation();
            		System.out.println("Class Name: " + n.getNameAsString() + "\n" + "LOC: " + a.getLOC());
                    	
                    
                }
               
            }.visit(JavaParser.parse(file), null);
            
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
