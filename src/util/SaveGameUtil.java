package util;

import storage.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class SaveGameUtil {
	 String saveFolder;
	
	public SaveGameUtil(String saveFolder){
		this.saveFolder = saveFolder;
	}
	
	public void saveNations(List<Nation> nations) throws FileNotFoundException,IOException{
		for(Nation n: nations){
			try(PrintWriter writer = new PrintWriter(new FileOutputStream(new File(saveFolder+"/nations/"+n.getName()+".txt")))){
				writer.print(n.toString());
				writer.close();
			}
		}
	}
	
	public void savePosition(int position, int round){
		try(PrintWriter writer = new PrintWriter(new FileOutputStream(new File(saveFolder+"/current nation.txt")))){
			writer.println("position: "+(position));
			writer.println("round: "+round);
			writer.close();
		} catch (IOException e) {
			System.out.println("ERROR: could not save to "+saveFolder+"/current nation.txt");
			e.printStackTrace();
		}
	}
}

