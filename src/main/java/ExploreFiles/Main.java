package ExploreFiles;

import java.io.File;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//File projectDir = new File("C:\\\\Users\\\\DELL\\\\Desktop\\\\MetricsTool\\\\JSettlers-Test-Data\\\\jsettlers-1.1.18-src");
		File projectDir = new File("C:\\\\Users\\\\DELL\\\\Desktop\\\\MetricsTool\\\\Proguard\\proguard3.11");

		//File projectDir = new File("C:\\Users\\DELL\\workspace\\SymbolSolverExperiment");
		
		ClassExplorer classexplorer=new ClassExplorer();
		classexplorer.doOperation(projectDir);
		//classexplorer.listClasses(projectDir);
	}

}
