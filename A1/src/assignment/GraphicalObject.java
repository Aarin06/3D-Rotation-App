package assignment;

public abstract class GraphicalObject {
	
	
	protected abstract void transform(double [][] matrix);
	
	protected void rotateXAxis(double angle) {
		double matrixX [][] = {{1, 0, 0}, {0, Math.cos(angle), -Math.sin(angle)}, {0, Math.sin(angle), Math.cos(angle)}};
		transform(matrixX);
	}
	
	protected void rotateYAxis(double angle) {
		double matrixY [][] = {{Math.cos(angle), 0, Math.sin(angle)}, {0, 1, 0}, {-Math.sin(angle), 0, Math.cos(angle)}};
		transform(matrixY);
	}
	
	protected void rotateZAxis(double angle) {
		double matrixZ [][] = {{Math.cos(angle), -Math.sin(angle), 0}, {Math.sin(angle), Math.cos(angle), 0}, {0, 0, 1}};
		transform(matrixZ);
	}
	
}
