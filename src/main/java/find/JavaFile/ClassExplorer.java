package find.JavaFile;


import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseException;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.ForStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.google.common.base.Strings;

import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.LocalVariableTypeAttribute;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.swing.SingleSelectionModel;

public class ClassExplorer {

    public static void listClasses(File projectDir) {
        new DirExplorer((level, path, file) -> path.endsWith(".java"), (level, path, file) -> {
            System.out.println(path);
            System.out.println(Strings.repeat("=", path.length()));
            try {
                new VoidVisitorAdapter<Object>() {
                    @Override
                    public void visit(ClassOrInterfaceDeclaration n, Object arg) {
                        super.visit(n, arg);
                        
                        for (MethodDeclaration method : n.getMethods()) {
                			CyclomaticComplexityCalculator c=new CyclomaticComplexityCalculator(method);
                			System.out.println("Method Name: "+ method.getName() + "\n" + "CYC: " +c.calculateComplexity());
                		}
                        
                        WeightedMethodCountCalculator w=new WeightedMethodCountCalculator(n);
                        System.out.println("Class Name: " + n.getName() + "\n" + "WMC: " + w.calculateWeightedMethodCount());
                        
                        
					
                    
                    }
                }.visit(JavaParser.parse(file), null);
                System.out.println(); // empty line
            } catch (IOException e) {
                new RuntimeException(e);
            }
        }).explore(projectDir);
    }

    public static void main(String[] args) {
        File projectDir = new File("Circle");
        listClasses(projectDir);
    }
}

