package assignment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PLYMeshReader implements MeshReader{

	@Override
	public HashSet<Polygon> read(String filename) throws WrongFileFormatException {
		// TODO Auto-generated method stub
FileReader fr = null;
		
		HashSet<Polygon> p = new HashSet<Polygon>();
		
		Pattern patternVertex = Pattern.compile("^-?\\d+\\.?\\d*(E-?\\d+)?\\s+-?\\d+\\.?\\d*(E-?\\d+)?\\s+-?\\d+\\.?\\d*(E-?\\d+)?\\s*$"); 
		//Pattern patternFace2 = Pattern.compile("^\\d+\\s+\\d+\\s+\\d+\\s+\\d+\\s*$");
		Pattern patternFace = Pattern.compile("^\\d+(\\s+\\d+)+\\s*$");
		
		Pattern beginning1 = Pattern.compile("ply\\s*\n"
				+ "format ascii 1.0\\s*\n"
				+ "element vertex \\s*\\d+\\s*\n"
				+ "property float32 x\\s*\n"
				+ "property float32 y\\s*\n"
				+ "property float32 z\\s*\n"
				+ "element face \\s*\\d+\\s*\n"
				+ "property list uint8 int32 vertex_indices\\s*\n"
				+ "end_header\\s*\n");
 
									
		ArrayList<Vertex> vSet = new ArrayList<Vertex>();
		
		try {
			fr = new FileReader(filename);
			BufferedReader br = new BufferedReader(fr);
			String [] strVertexArr = null;
			String [] strFaceArr = null;
			String word = "";
			String [] otherV = null;
			String [] otherF = null;
			int faces=0;
			int vertices=0;
			int counter = 0;
			Matcher matcherV;
			Matcher matcherF;
			
			for (int i = 0 ; i < 9; i++) {
				String temp = br.readLine();
				if (temp == null) {
					br.close();
					throw new WrongFileFormatException("This is not correct format");
				}
				word += temp+"\n";
				if (i == 2) {
					otherV = temp.split(" ");
				}
				if (i == 6) {
					otherF = temp.split(" ");
				}
							
			}
			Matcher matcherB = beginning1.matcher(word);
			if(!matcherB.matches()) {
				br.close();
				throw new WrongFileFormatException("This is not correct format");
			}
			
			vertices = Integer.parseInt(otherV[otherV.length-1]);
			faces = Integer.parseInt(otherF[otherF.length-1]);
			
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
			
		    counter = 0;
			while (word != null) {
				matcherF = patternFace.matcher(word);
				if(!matcherF.matches()) {
					br.close();
					throw new WrongFileFormatException("This is not correct format");
				}
				strFaceArr = word.split("\\s+");
				
				if(Integer.parseInt(strFaceArr[0]) != (strFaceArr.length-1)) {
					br.close();
					throw new WrongFileFormatException("This is not correct format");
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
				
				if(p.add(new Polygon(tempSet))) {
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
