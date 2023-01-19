package assignment;

import java.io.*;
import java.util.*;
import java.util.regex.*;

public class OFFMeshReader implements MeshReader{

	@Override
	public HashSet<Polygon> read(String filename) throws WrongFileFormatException{
		FileReader fr = null;
		BufferedReader br = null;
		
		HashSet<Polygon> p = new HashSet<Polygon>();
		
		Pattern patternConstant = Pattern.compile("^OFF\\s*$");
		Pattern patternVertex = Pattern.compile("^-?\\d+\\.?\\d*(E-?\\d+)?\\s+-?\\d+\\.?\\d*(E-?\\d+)?\\s+-?\\d+\\.?\\d*(E-?\\d+)?\\s*$"); 
		//Pattern patternFace2 = Pattern.compile("^\\d+\\s+\\d+\\s+\\d+\\s+\\d+\\s+\\d+\\s+\\d+\\s+\\d+\\s*$");
		Pattern patternFace = Pattern.compile("^\\d+(\\s+\\d+)+\s*$");	
		
		
		ArrayList<Vertex> vSet = new ArrayList<Vertex>();
		
		try {
			fr = new FileReader(filename);
			br = new BufferedReader(fr);
			String [] strVertexArr = null;
			String [] strFaceArr = null;
			String word = "";
			String [] quantity = null;
			int faces, vertices;
			int counter = 0;
			Matcher matcherV,matcherC,matcherF;
			
			word = br.readLine();
			
			matcherC = patternConstant.matcher(word);
			if (!matcherC.matches()){
				br.close();
				throw new WrongFileFormatException("This is not correct format");
			}
			
			word = br.readLine();
			matcherV = patternVertex.matcher(word);
			
			if(!matcherV.matches()) {
				br.close();
				throw new WrongFileFormatException("This is not correct format");
			}
			
			quantity = word.split("\\s+");
			faces = Integer.parseInt(quantity[1]);
			vertices = Integer.parseInt(quantity[0]);
			if(Integer.parseInt(quantity[2]) != 0) {
				br.close();
				throw new WrongFileFormatException("This is not correct format");
			}
			
			counter = 0;
			word = br.readLine();
			while(counter != vertices && word != null) {
				matcherV = patternVertex.matcher(word);
				if(!matcherV.matches()) {
					br.close();
					throw new WrongFileFormatException("This is not correct format");
				}
				
				strVertexArr = word.split("\\s+");
				Vertex v = new Vertex(Double.parseDouble(strVertexArr[0]),Double.parseDouble(strVertexArr[1]),Double.parseDouble(strVertexArr[2]));
				if(vSet.add(v)) {
					counter++;
				}
				word = br.readLine();
			}
			
			
			if(counter != vertices) {
				br.close();
				throw new WrongFileFormatException("This is not correct format");
			}
			
			counter = 0;
			while(word != null) {
				
				matcherF = patternFace.matcher(word);
				if(!matcherF.matches()) {
					br.close();
					throw new WrongFileFormatException("This is not correct format");
				}
				strFaceArr = word.split("\\s+");
				
				if(Integer.parseInt(strFaceArr[0]) != (strFaceArr.length-1)/2 || Integer.parseInt(strFaceArr[0]) == (strFaceArr.length-1)) {
 
					br.close();
					throw new WrongFileFormatException("This is not correct format");
				}
				
				for (int i = (strFaceArr.length-1)/2 +1 ; i < (strFaceArr.length); i++) {
					
					if (Integer.parseInt(strFaceArr[i]) >= 255 || Integer.parseInt(strFaceArr[i]) <= 0) {
						br.close();
						throw new WrongFileFormatException("This is not correct format");
					}
					
				}
				
				LinkedHashSet<Vertex> tempSet = new LinkedHashSet<Vertex>();
				 
				for (int j = 1 ; j <= Integer.parseInt(strFaceArr[0]) ; j++) {
					if(Integer.parseInt(strFaceArr[j]) >= vSet.size()) {
						br.close();
						throw new WrongFileFormatException("This is not correct format");
					}
					Vertex v = vSet.get(Integer.parseInt(strFaceArr[j]));
					Vertex copy = new Vertex(v.x,v.y,v.z);
					tempSet.add(copy);
							
				}
				
				if(p.add(new Polygon(tempSet))){
					counter++;
				}
			
				word = br.readLine();
				
			}
			
			if(counter != faces) {
				br.close();
				
				throw new WrongFileFormatException("This is not correct format");
			}
	
			fr.close();
			br.close();
			
			
		}
		catch(IOException e) {
			System.out.println(e);
		}
		
		return p;
		
		
	}

}
