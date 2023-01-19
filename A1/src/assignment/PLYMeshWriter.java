package assignment;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class PLYMeshWriter implements MeshWriter{

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
			
			bw.write("ply\n"
					+ "format ascii 1.0\n"
					+ "element vertex "+alreadyAdded.size()+"\n"
					+ "property float32 x\n"
					+ "property float32 y\n"
					+ "property float32 z\n"
					+ "element face "+polygons.size()+"\n"
					+ "property list uint8 int32 vertex_indices\n"
					+ "end_header\n");
				
			for (Vertex v : alreadyAdded) {
				bw.write(v.x+" "+v.y+" "+v.z+" \n");
			}
						
			for (Polygon polygon : polygons) {
				bw.write(polygon.vertices.size()+" ");
				for(Vertex v : polygon.vertices) {
					bw.write(alreadyAdded2.indexOf(v)+" ");
				}
				bw.newLine();
				
			}
			
			bw.close();
			fw.close();

			
		}catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
