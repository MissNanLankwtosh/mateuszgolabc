package stablepoint;

/*
 * StablePoint class
 * It is a simple class: constructor + getters 
 */
public class StablePoint {
	
	private int preModuleID;
	private int postModuleID;
	private String pathToOutputFile;
	private String pathToInputFile;
	
	/*
	 * Consturctor
	 * Usage: 
	 * outputPath = "c:\\..\\myProgram\\resources\\outputFile0";
	 * inputPath = "c:\\..\\myProgram\\resources\\inputFile1";
	 * StablePoint(0, 1, outputPath, inputPath);
	 */
	public StablePoint(int pre, int post, String pathToOutput, String pathToInput) {
    	
    	this.preModuleID = pre;
    	this.postModuleID = post;
    	this.pathToOutputFile = pathToOutput;
    	this.pathToInputFile = pathToInput;
    }
	
	public int getPreModuleID() {
		
		return preModuleID;
	}
	
	public int getPostModuleID() {
		
		return postModuleID;
	}
	
	public String getPathToOutputFile() {
		
		return pathToOutputFile;
	}
	
	public String getPathToInputFile() {
		
		return pathToInputFile;
	}
	
}
