package assignment;

import java.util.*;

public class Mesh extends GraphicalObject {
	HashSet<Polygon> polygons;
	MeshReader reader;
	MeshWriter writer;
	
	public void setReader(MeshReader r) {
		reader = r;
	}
		
	public void setWriter(MeshWriter w) {
		writer = w;
	}
	
	public void readFromFile(String filename) throws WrongFileFormatException {
		polygons = reader.read(filename);
	}
	
	public void writeToFile(String filename) {
		writer.write(filename, polygons);
	}
	
	@Override
	public int hashCode() {
		double mag = 0;;
		for (Polygon p : polygons) {
			for (Vertex v : p.vertices) {
				mag += Math.sqrt((v.x*v.x) + (v.y*v.y) + (v.z*v.z));
			}
		}
		
		return (int)mag;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Mesh) {
			Mesh m = (Mesh)obj;
			if (m.polygons.containsAll(polygons)) {
				return true;
			}
		}
		return false;
	}

	@Override
	protected void transform(double[][] matrix) {		
		for (Polygon p : polygons) {
			p.transform(matrix);
		}
		
		
		
	}
	
}
