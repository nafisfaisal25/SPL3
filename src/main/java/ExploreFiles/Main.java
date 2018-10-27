package ExploreFiles;

import java.io.File;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File projectDir = new File("../Account");
        System.out.println(new FileExplorer(projectDir).getDotjavaContainer());
	}

}
