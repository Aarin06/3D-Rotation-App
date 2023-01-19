package assignment;

import java.io.*;
import java.util.*;

public class OBJMeshWriter implements MeshWriter{

	@Override
	public void write(String filename, HashSet<Polygon> polygons) {
		// TODO Auto-generated method stub
		FileWriter fw = null;
		LinkedHashSet<Vertex> alreadyAdded = new LinkedHashSet<Vertex>();
		ArrayList<Vertex> alreadyAdded2 = new ArrayList<Vertex>();
		try {
			fw = new FileWriter(filename);
			BufferedWriter bw = new BufferedWriter(fw);
					
			for (Polygon polygon : polygons) {
				for (Vertex v : polygon.vertices) {
					alreadyAdded.add(v);
				}
			}
			
			alreadyAdded2.addAll(alreadyAdded);
			
			for (Vertex v : alreadyAdded) {
				bw.write("v "+v.x+" "+v.y+" "+v.z+" ");
				bw.newLine();
			}
			
			for (Polygon polygon : polygons) {
				bw.write("f");
				for(Vertex v : polygon.vertices) {
					bw.write(" "+ (alreadyAdded2.indexOf(v)+1) );
				}
				bw.newLine();
				
			}
		
			bw.close();
			fw.close();

			
		}catch (IOException e) {
			System.out.println(e);
		}
	
	}

}
