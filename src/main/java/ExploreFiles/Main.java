package ExploreFiles;

import java.io.File;

import SmellDetector.FeatureEnvyDetector;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//File projectDir = new File("C:\\\\Users\\\\DELL\\\\Desktop\\\\MetricsTool\\\\JSettlers-Test-Data\\\\jsettlers-1.1.18-src");
		File projectDir = new File("C:\\\\Users\\\\DELL\\\\Desktop\\\\MetricsTool\\\\Proguard\\proguard3.11");

		//File projectDir = new File("C:\\Users\\DELL\\workspace\\SymbolSolverExperiment");
		//File projectDir = new File("C:\\Users\\DELL\\workspace\\SPL3\\src");
		
		
		//ClassExplorer classexplorer=new ClassExplorer();
		//classexplorer.doOperation(projectDir);
		//classexplorer.listClasses(projectDir);
		
		FeatureEnvyDetector f=new FeatureEnvyDetector(projectDir);
		//LongMethodDetector l=new LongMethodDetector(projectDir);
		//DataClassDetector d=new DataClassDetector(projectDir);
	}

}
