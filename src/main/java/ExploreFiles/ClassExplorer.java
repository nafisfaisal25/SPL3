package ExploreFiles;

import CalculateMetrics.*;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseException;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.ForStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.symbolsolver.JavaSymbolSolver;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.JavaParserTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;
import com.google.common.base.Strings;

import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.LocalVariableTypeAttribute;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.swing.SingleSelectionModel;

public class ClassExplorer {

    public static void listClasses(File projectDir) {
        new DirExplorer((level, path, file) -> path.endsWith(".java"), (level, path, file) -> {
            System.out.println(path);
            System.out.println(Strings.repeat("=", path.length()));
            metricsCalculatorHandler handler=new metricsCalculatorHandler(file);
            try {
				handler.cyclomaticComplexityCaclHandler();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
        }).explore(projectDir);
    }
    
    public static void compilationUnit(File file) {
    	TypeSolver reflectionTypeSolver = new ReflectionTypeSolver();
        TypeSolver javaParserTypeSolver = new JavaParserTypeSolver(new File("../Account/src"));
        reflectionTypeSolver.setParent(reflectionTypeSolver);
        
        CombinedTypeSolver combinedSolver = new CombinedTypeSolver();
        combinedSolver.add(reflectionTypeSolver);
        combinedSolver.add(javaParserTypeSolver);
        
        //TypeSolver typeSolver = new CombinedTypeSolver();
		JavaSymbolSolver symbolSolver = new JavaSymbolSolver(combinedSolver);
		JavaParser.getStaticConfiguration().setSymbolResolver(symbolSolver);
		
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
    
    

    public static void main(String[] args) {
        File projectDir = new File("../Account");
        listClasses(projectDir);
    }
}

