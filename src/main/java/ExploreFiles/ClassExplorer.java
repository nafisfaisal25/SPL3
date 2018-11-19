package ExploreFiles;

import CalculateMetrics.*;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.symbolsolver.JavaSymbolSolver;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.JavaParserTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;
import com.google.common.base.Strings;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Handler;

import javax.swing.SingleSelectionModel;

public class ClassExplorer {
	
	public Set<String> allClassName=new HashSet<>();
	public JavaParser parser=new JavaParser();
	private String className;
	private String packageName;
	
	
	public void doOperation(File projectDir) {
		getAllClassName(projectDir);
		//System.out.println(allClassName.size());
		prepareJavaParser(new FileExplorer(projectDir).getDotjavaContainer());
		listClasses(projectDir);
	}

    public void listClasses(File projectDir) {
        new DirExplorer((level, path, file) -> path.endsWith(".java"), (level, path, file) -> {
            System.out.println(path);
            System.out.println(Strings.repeat("=", path.length()));
            metricsCalculatorHandler handler=new metricsCalculatorHandler(file);
            
            /*
            try {
				handler.cyclomaticComplexityCaclHandler();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/  
            //handler.ATFDCalcHandler(allClassName,parser,getPackageName(file)+"."+getClassName(file));
            handler.NOAVCalcHandler(allClassName, parser, getPackageName(file)+"."+getClassName(file));
            
        }).explore(projectDir);
    }
    
    public void prepareJavaParser(Set <String> dotJavaContainer) {
		TypeSolver reflectionTypeSolver = new ReflectionTypeSolver();
        CombinedTypeSolver combinedSolver = new CombinedTypeSolver();
        combinedSolver.add(reflectionTypeSolver);
       
        //combinedSolver.add(new JavaParserTypeSolver(new File("C:\\Users\\DELL\\workspace\\SymbolSolverExperiment\\src\\first")));
        //combinedSolver.add(new JavaParserTypeSolver(new File("C:\\Users\\DELL\\Desktop\\MetricsTool\\JSettlers-Test-Data\\jsettlers-1.1.18-src\\src\\java\\soc\\client")));

        
        for (String filePath : dotJavaContainer) {
        	System.out.println(filePath);
			combinedSolver.add(new JavaParserTypeSolver(new File(filePath)));
		}
        
		JavaSymbolSolver symbolSolver = new JavaSymbolSolver(combinedSolver);
		JavaParser.getStaticConfiguration().setSymbolResolver(symbolSolver);
	}
    
    public void getAllClassName(File projectDir) {
    	new DirExplorer((level, path, file) -> path.endsWith(".java"), (level, path, file) -> {
        
            try {
                new VoidVisitorAdapter<Object>() {
                    @Override
                    public void visit(ClassOrInterfaceDeclaration n, Object arg) {
                        super.visit(n, arg);
                        allClassName.add(getPackageName(file)+"."+getClassName(file));
                        
                    }
                }.visit(JavaParser.parse(file), null);
            } catch (IOException e) {
                new RuntimeException(e);
            }
        }).explore(projectDir);
    }
    
    public String getClassName(File file) {
    	
        
            try {
                new VoidVisitorAdapter<Object>() {
                   
                    @Override
                    public void visit(ClassOrInterfaceDeclaration n, Object arg) {
                        super.visit(n, arg);
                        className=n.getNameAsString();
                        
                    }
                }.visit(JavaParser.parse(file), null);
            } catch (IOException e) {
                new RuntimeException(e);
            }
            return className;
       
    }
    
    public String getPackageName(File file) {
    	
        
        try {
            new VoidVisitorAdapter<Object>() {
            	@Override
                public void visit(CompilationUnit n, Object arg) {
                    super.visit(n, arg);
                    packageName=n.getPackageDeclaration().get().getNameAsString();
                    
                }
            }.visit(JavaParser.parse(file), null);
        } catch (IOException e) {
            new RuntimeException(e);
        }
        return packageName;
   
}
    
    
    
    

    public static void main(String[] args) {
        //File projectDir = new File("../SymbolSolverExperiment");
        //listClasses(projectDir);
    }
}

