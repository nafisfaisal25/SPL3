package ExploreFiles;

import java.io.File;

import SmellDetector.DataClassDetector;
import SmellDetector.DuplicatedCodeDetector;
import SmellDetector.FeatureEnvyDetector;
import SmellDetector.GodClassDetector;
import SmellDetector.LongMethodDetector;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//File projectDir = new File("C:\\\\Users\\\\DELL\\\\Desktop\\\\MetricsTool\\\\JSettlers-Test-Data\\\\jsettlers-1.1.18-src");
		//File projectDir = new File("C:\\Users\\DELL\\Desktop\\MetricsTool\\Proguard\\proguard3.11");
		File projectDir = new File("C:\\\\Users\\\\DELL\\\\workspace\\\\clone");
		//File projectDir = new File("C:\\\\Users\\\\DELL\\\\workspace\\\\RandomForest");


		//File projectDir = new File("C:\\Users\\DELL\\workspace\\SymbolSolverExperiment");
		//File projectDir = new File("C:\\Users\\DELL\\workspace\\SPL3\\src");
		
		
		//ClassExplorer classexplorer=new ClassExplorer();
		//classexplorer.doOperation(projectDir);
		//classexplorer.listClasses(projectDir);
		
		//new FeatureEnvyDetector(projectDir);
		//new LongMethodDetector(projectDir);
		//new DataClassDetector(projectDir);
		//new GodClassDetector(projectDir);
		new DuplicatedCodeDetector(projectDir);
		
		

	}

}
