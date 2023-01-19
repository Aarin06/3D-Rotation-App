package assignment;

public class Vertex extends GraphicalObject {
	double x;
	double y;
	double z;
	
	public Vertex(double a, double b, double c) {
		x=a;
		y=b;
		z=c;
	}
	
	@Override
	public int hashCode() {
		int mag = (int) Math.sqrt((x*x) + (y*y) + (z*z));
		return mag;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if(obj instanceof Vertex) {
			Vertex v = (Vertex)obj;
			if (x == v.x && y == v.y && z == v.z) {
				return true;
			}
		}
		return false;	
		
	}
	
	@Override
	public String toString() {
		return x+ " "+ y +" "+ z;
	}

	@Override
	protected void transform(double [][] matrix) {
		double xX, yY, zZ;
		xX = matrix[0][0]*x + matrix[0][1]*y + matrix[0][2]*z;
		yY = matrix[1][0]*x + matrix[1][1]*y + matrix[1][2]*z;
		zZ = matrix[2][0]*x + matrix[2][1]*y + matrix[2][2]*z;
		x=xX;
		y=yY;
		z=zZ;

	}
	
	
}
