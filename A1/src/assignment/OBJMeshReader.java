package assignment;

import java.io.*;
import java.util.*;
import java.util.regex.*;


public class OBJMeshReader implements MeshReader {

	@Override
	public HashSet<Polygon> read(String filename) throws WrongFileFormatException {
		FileReader fr = null;
		BufferedReader br = null;
		
		HashSet<Polygon> p = new HashSet<Polygon>();
	
		Pattern patternVertex = Pattern.compile("^v\\s+-?\\d+\\.?\\d*(E-?\\d+)?\\s+-?\\d+\\.?\\d*(E-?\\d+)?\\s+-?\\d+\\.?\\d*(E-?\\d+)?\\s*$"); 
		//Pattern patternFace2 = Pattern.compile("^f\\s+\\d+\\s+\\d+\\s+\\d+\\s*$");
		Pattern patternFace = Pattern.compile("^f(\\s+\\d+)+\s*$");
		
		ArrayList<Vertex> vSett = new ArrayList<Vertex>();
			 
		try {
			fr = new FileReader(filename);
			br = new BufferedReader(fr);
		
			String [] strVertexArr = null;
			String [] strFaceArr = null;
			String word = "";
			int check = 1;
			Matcher matcherV,matcherF; 
	
			word=br.readLine();
			while(word != null) {
				if (check == 1) {
					matcherV = patternVertex.matcher(word);
					if (matcherV.matches()) {
						strVertexArr = word.split("\\s+");
						Vertex v = new Vertex(Double.parseDouble(strVertexArr[1]),Double.parseDouble(strVertexArr[2]),Double.parseDouble(strVertexArr[3]));
						vSett.add(v);
					}
					else {
						check = 2;
					}
				}
			
				if (check == 2) {
					matcherF = patternFace.matcher(word);
					if(matcherF.matches()) {
						strFaceArr = word.split("\\s+");
						LinkedHashSet<Vertex> tempSet = new LinkedHashSet<Vertex>();
						for (int i = 1 ; i < strFaceArr.length ; i++) {
							if(Integer.parseInt(strFaceArr[i]) - 1 >= vSett.size()) {
								br.close();
								throw new WrongFileFormatException("This is not correct format");				
							}
							Vertex v = vSett.get(Integer.parseInt(strFaceArr[i]) - 1);
							Vertex copy = new Vertex(v.x,v.y,v.z);
							tempSet.add(copy);	
							
						}
						p.add(new Polygon(tempSet));		
					}
					else {
						br.close();
						throw new WrongFileFormatException("This is not correct format");
					}
				}
				word = br.readLine();
			}
				
			br.close();
			fr.close();
	
		} 
		catch (IOException e) {

			System.out.println(e);
		}
		
		
	
		return p;
	}
	
}
