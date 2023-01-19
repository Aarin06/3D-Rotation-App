package assignment;

import java.util.*;

public class Polygon extends GraphicalObject {
	LinkedHashSet<Vertex> vertices;
	
	public Polygon(LinkedHashSet<Vertex> v) {
		vertices = new LinkedHashSet<Vertex>(); 
		vertices.addAll(v);
	}
	
	@Override
	public int hashCode() {
		int mag = 0;;
		for (Vertex v : vertices) {
			mag += (int)Math.sqrt((v.x*v.x) + (v.y*v.y) + (v.z*v.z));
		}
		
		return mag;
	}
		
	@Override
	public boolean equals(Object obj) {
		
		if(obj instanceof Polygon) {
			Polygon p = (Polygon)obj;
	        Iterator<Vertex> it1 = p.vertices.iterator();
	        Iterator<Vertex> it2 = vertices.iterator();
	        while (it1.hasNext()) {
	            if (!it1.next().equals(it2.next())){
	                return false;
	            }
	        }	
	        return true;
		}
		
		return false;
	
   
    }
	@Override
	protected void transform(double [][] matrix) {
		
		for(Vertex v : vertices) {
			v.transform(matrix);
		}
		
	}
	
	
	
	
}
