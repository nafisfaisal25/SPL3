package ExploreFiles;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

import com.google.common.base.Strings;

public class LongMethodDetector {
	public LongMethodDetector() {
		
	}
	
	public LongMethodDetector(File projectDir) {
		calculateMetricValues(projectDir);
	}
	
	private void calculateMetricValues(File projectDir) {
		ClassExplorer classexplorer=new ClassExplorer();
		classexplorer.doOperation(projectDir);
		classexplorer.detectLongMethod(projectDir);
	}
	
	public void compareMetricWithThresholad(Map<String,ArrayList<Double>> map, ArrayList<String> methodNameList,String path) {
		int HIGH_class=65,HIGH_cyclo=4,MANY=7;
		
		ArrayList<Double> LOCForMethod = null,CYCLO = null,NOAV = null;
		if(map.get("LOCForMethod") != null)LOCForMethod=map.get("LOCForMethod");
		if(map.get("CYCLO") != null)CYCLO=map.get("CYCLO");
		if(map.get("NOAV") != null)NOAV=map.get("NOAV");
		
		for(int i=0;i<LOCForMethod.size();i++) {
			if(LOCForMethod.get(i)>HIGH_class && CYCLO.get(i) >=HIGH_cyclo && NOAV.get(i) > MANY ) {
				System.out.println(path);
	            System.out.println(Strings.repeat("=", path.length()));
				System.out.println(methodNameList.get(i));
			}
		}

		
	}
}
