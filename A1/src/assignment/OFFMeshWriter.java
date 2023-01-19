package assignment;

import java.io.*;
import java.util.*;

public class OFFMeshWriter implements MeshWriter{

	@Override
	public void write(String filename, HashSet<Polygon> polygons) {
		// TODO Auto-generated method stub
		FileWriter fw = null;
		LinkedHashSet<Vertex> alreadyAdded = new LinkedHashSet<Vertex>();
		ArrayList<Vertex> alreadyAdded2 = new ArrayList<Vertex>();
		
		try {
			fw = new FileWriter(filename);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("OFF");
			bw.newLine();
			
			for (Polygon polygon : polygons) {
				for (Vertex v : polygon.vertices) {
					alreadyAdded.add(v);
				}
			}
			alreadyAdded2.addAll(alreadyAdded);
			
			bw.write(alreadyAdded.size()+" ");
			bw.write(polygons.size()+" ");
			bw.write("0");
			bw.newLine();
			
			
			for (Vertex v : alreadyAdded) {
				bw.write(v.x+" "+v.y+" "+v.z);
				bw.newLine();
			}
				
			for (Polygon polygon : polygons) {
				bw.write(polygon.vertices.size()+" ");
				for(Vertex v : polygon.vertices) {
					bw.write(alreadyAdded2.indexOf(v)+" ");
				}
				for(int i = 0 ; i < polygon.vertices.size();i++) {
					bw.write(" 220");
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
