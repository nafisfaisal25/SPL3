package ExploreFiles;

import java.io.File;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File projectDir = new File("../SymbolSolverExperiment");
		
		ClassExplorer classexplorer=new ClassExplorer();
		classexplorer.listClasses(projectDir);
	}

}
