package ExploreFiles;

import CalculateMetrics.*;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.google.common.base.Strings;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.swing.SingleSelectionModel;

public class ClassExplorer {
	
	public Set<String> allClassName=new HashSet<>();

    public void listClasses(File projectDir) {
        new DirExplorer((level, path, file) -> path.endsWith(".java"), (level, path, file) -> {
            System.out.println(path);
            System.out.println(Strings.repeat("=", path.length()));
            getAllClassName(projectDir);
            metricsCalculatorHandler handler=new metricsCalculatorHandler(file);
            
            try {
				handler.cyclomaticComplexityCaclHandler();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            handler.ATFDCalcHandler(new FileExplorer(projectDir).getDotjavaContainer(),allClassName);
            //compilationUnit(file);
            
        }).explore(projectDir);
    }
    
    public void getAllClassName(File projectDir) {
    	new DirExplorer((level, path, file) -> path.endsWith(".java"), (level, path, file) -> {
        
            try {
                new VoidVisitorAdapter<Object>() {
                    @Override
                    public void visit(ClassOrInterfaceDeclaration n, Object arg) {
                        super.visit(n, arg);
                        allClassName.add(n.getNameAsString());
                        
                    }
                }.visit(JavaParser.parse(file), null);
            } catch (IOException e) {
                new RuntimeException(e);
            }
        }).explore(projectDir);
    }
    
    
    
    

    public static void main(String[] args) {
        //File projectDir = new File("../SymbolSolverExperiment");
        //listClasses(projectDir);
    }
}

