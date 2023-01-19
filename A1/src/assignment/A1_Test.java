package assignment;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class A1_Test {
	
	@Test
	void testReadWriteOFF() throws WrongFileFormatException {
		
		Mesh mesh = new Mesh(); 
		mesh.setReader(new OFFMeshReader()); 
		mesh.readFromFile("src/car.off");
		mesh.rotateXAxis(Math.PI/3);
		
		mesh.setWriter(new OFFMeshWriter());	
		mesh.writeToFile("src/car_Check.off");
	
		Mesh mesh2 = new Mesh();
		mesh2.setReader(new OBJMeshReader()); 
		mesh2.readFromFile("src/car_R.obj");
		
		assertTrue(mesh.equals(mesh2));
				
	}
	
	@Test
	void testReadWriteOBJ() throws WrongFileFormatException {
		
		Mesh mesh = new Mesh(); 
		mesh.setReader(new OBJMeshReader()); 
		mesh.readFromFile("src/car.obj");
		mesh.rotateYAxis(Math.PI/6);
	
		mesh.setWriter(new OBJMeshWriter());
		mesh.writeToFile("src/car_Check.obj");
		
		Mesh mesh2 = new Mesh();
		mesh2.setReader(new PLYMeshReader()); 
		mesh2.readFromFile("src/car_R.ply");
		
		assertTrue(mesh.equals(mesh2));
				
	}
	
	@Test
	void testReadWritePLY() throws WrongFileFormatException {
		
		Mesh mesh = new Mesh(); 
		mesh.setReader(new PLYMeshReader()); 
		mesh.readFromFile("src/car.ply");
		mesh.rotateZAxis((2*Math.PI)/3);
	
		mesh.setWriter(new PLYMeshWriter());
		mesh.writeToFile("src/car_Check.ply");
		
		Mesh mesh2 = new Mesh();
		mesh2.setReader(new OFFMeshReader()); 
		mesh2.readFromFile("src/car_R.off");
		
		assertTrue(mesh.equals(mesh2));
	
	}
	
	@Test
	void readingConstantOFFError() {
	
		try { 
			Mesh mesh = new Mesh(); 
			mesh.setReader(new OFFMeshReader()); 
			mesh.readFromFile("src/offtest1.off");
		}
		catch(WrongFileFormatException e) {
			assertEquals(e.message,"This is not correct format");	
		}
	
	}
	@Test
	void readingSizeOFFError(){
		try {
			Mesh mesh = new Mesh(); 
			mesh.setReader(new OFFMeshReader()); 
			mesh.readFromFile("src/offtest2.off");
		}
		catch(WrongFileFormatException e) {
			assertEquals(e.message,"This is not correct format");	
		}
	
	}
	
	@Test
	void readingVertexOFFError(){
		try {
			Mesh mesh = new Mesh(); 
			mesh.setReader(new OFFMeshReader()); 
			mesh.readFromFile("src/offtest3.off");
		}
		catch(WrongFileFormatException e) {
			assertEquals(e.message,"This is not correct format");	
		}
	
	}
	
	@Test
	void readingVertexRangeOFFError(){
		try {
			Mesh mesh = new Mesh(); 
			mesh.setReader(new OFFMeshReader()); 
			mesh.readFromFile("src/offtest4.off");
		}
		catch(WrongFileFormatException e) {
			assertEquals(e.message,"This is not correct format");	
		}
	
	}
	
	@Test
	void readingFaceRangeOFFError(){
		try {
			Mesh mesh = new Mesh(); 
			mesh.setReader(new OFFMeshReader()); 
			mesh.readFromFile("src/offtest5.off");
		}
		catch(WrongFileFormatException e) {
			assertEquals(e.message,"This is not correct format");	
		}
	
	}
	
	@Test
	void readingColorOFFError(){
		try {
			Mesh mesh = new Mesh(); 
			mesh.setReader(new OFFMeshReader()); 
			mesh.readFromFile("src/offtest6.off");
		}
		catch(WrongFileFormatException e) {
			assertEquals(e.message,"This is not correct format");	
		}
	
	}
	
	@Test
	void readingColor2OFFError(){
		try {
			Mesh mesh = new Mesh(); 
			mesh.setReader(new OFFMeshReader()); 
			mesh.readFromFile("src/offtest7.off");
		}
		catch(WrongFileFormatException e) {
			assertEquals(e.message,"This is not correct format");	
		}
	
	}
	
	@Test
	void readingSize2OFFError(){
		try {
			Mesh mesh = new Mesh(); 
			mesh.setReader(new OFFMeshReader()); 
			mesh.readFromFile("src/offtest8.off");
		}
		catch(WrongFileFormatException e) {
			assertEquals(e.message,"This is not correct format");	
		}
	
	}
	
	@Test
	void readingFaceVertexOFFError(){
		try {
			Mesh mesh = new Mesh(); 
			mesh.setReader(new OFFMeshReader()); 
			mesh.readFromFile("src/offtest9.off");
		}
		catch(WrongFileFormatException e) {
			assertEquals(e.message,"This is not correct format");	
		}
		
	}
	
	@Test
	void readingFaceRange2OFFError(){
		try {
			Mesh mesh = new Mesh(); 
			mesh.setReader(new OFFMeshReader()); 
			mesh.readFromFile("src/offtest10.off");
		}
		catch(WrongFileFormatException e) {
			assertEquals(e.message,"This is not correct format");	
		}
		
	}
	
	@Test
	void readingFaceRegexOFFError(){
		try {
			Mesh mesh = new Mesh(); 
			mesh.setReader(new OFFMeshReader()); 
			mesh.readFromFile("src/offtest11.off");
		}
		catch(WrongFileFormatException e) {
			assertEquals(e.message,"This is not correct format");	
		}
		
	}
	
	@Test
	void readingVertexRegexOBJError(){
		try {
			Mesh mesh = new Mesh(); 
			mesh.setReader(new OBJMeshReader()); 
			mesh.readFromFile("src/objtest1.obj");
		}
		catch(WrongFileFormatException e) {
			assertEquals(e.message,"This is not correct format");	
		}
	
	}
	
	@Test
	void readingFaceVertexOBJError(){
		try {
			Mesh mesh = new Mesh(); 
			mesh.setReader(new OBJMeshReader()); 
			mesh.readFromFile("src/objtest2.obj");
		}
		catch(WrongFileFormatException e) {
			assertEquals(e.message,"This is not correct format");	
		}
	
	}
	
	@Test
	void readingOrderOBJError(){
		try {
			Mesh mesh = new Mesh(); 
			mesh.setReader(new OBJMeshReader()); 
			mesh.readFromFile("src/objtest3.obj");
		}
		catch(WrongFileFormatException e) {
			assertEquals(e.message,"This is not correct format");	
		}
	
	}
		
	@Test
	void readingConstantPLYError(){
		try {
			Mesh mesh = new Mesh(); 
			mesh.setReader(new PLYMeshReader()); 
			mesh.readFromFile("src/plytest1.ply");
		}
		catch(WrongFileFormatException e) {
			assertEquals(e.message,"This is not correct format");	
		}
	
	}
	@Test
	void readingConstantRangePLYError(){
		try {
			Mesh mesh = new Mesh(); 
			mesh.setReader(new PLYMeshReader()); 
			mesh.readFromFile("src/plytest2.ply");
		}
		catch(WrongFileFormatException e) {
			assertEquals(e.message,"This is not correct format");	
		}
	
	}
	
	@Test
	void readingVertexRangePLYError(){
		try {
			Mesh mesh = new Mesh(); 
			mesh.setReader(new PLYMeshReader()); 
			mesh.readFromFile("src/plytest3.ply");
		}
		catch(WrongFileFormatException e) {
			assertEquals(e.message,"This is not correct format");	
		}
	
	}
	
	@Test
	void readingVertexRegexPLYError(){
		try {
			Mesh mesh = new Mesh(); 
			mesh.setReader(new PLYMeshReader()); 
			mesh.readFromFile("src/plytest4.ply");
		}
		catch(WrongFileFormatException e) {
			assertEquals(e.message,"This is not correct format");	
		}
	 
	}
	
	@Test
	void readingFaceRangePLYError(){
		try {
			Mesh mesh = new Mesh(); 
			mesh.setReader(new PLYMeshReader()); 
			mesh.readFromFile("src/plytest5.ply");
		}
		catch(WrongFileFormatException e) {
			assertEquals(e.message,"This is not correct format");	
		}
	
	}
	

	@Test
	void readingFaceRegexPLYError(){
		try {
			Mesh mesh = new Mesh(); 
			mesh.setReader(new PLYMeshReader()); 
			mesh.readFromFile("src/plytest6.ply");
		}
		catch(WrongFileFormatException e) {
			assertEquals(e.message,"This is not correct format");	
		}
	
	}
	
	@Test
	void readingFaceVertexPLYError(){
		try {
			Mesh mesh = new Mesh(); 
			mesh.setReader(new PLYMeshReader()); 
			mesh.readFromFile("src/plytest7.ply");
		}
		catch(WrongFileFormatException e) {
			assertEquals(e.message,"This is not correct format");	
		}
	
	}
	
	@Test
	void readingFaceSizePLYError(){
		try {
			Mesh mesh = new Mesh(); 
			mesh.setReader(new PLYMeshReader()); 
			mesh.readFromFile("src/plytest8.ply");
		}
		catch(WrongFileFormatException e) {
			assertEquals(e.message,"This is not correct format");	
		}
	
	}
	
	@Test
	void testMeshEquals() throws WrongFileFormatException {
		
		Mesh mesh = new Mesh(); 
		mesh.setReader(new PLYMeshReader()); 
		mesh.readFromFile("src/car.ply");
		mesh.rotateYAxis(Math.PI/3);
		
		Mesh mesh2 = new Mesh();
		mesh2.setReader(new OBJMeshReader()); 
		mesh2.readFromFile("src/car_rotated.obj");
		
		assertTrue(mesh.equals(mesh2));
		
	}
	@Test
	void testMeshEquals2() throws WrongFileFormatException {
		
		Mesh mesh = new Mesh(); 
		mesh.setReader(new PLYMeshReader()); 
		mesh.readFromFile("src/car.ply");
		mesh.rotateXAxis(Math.PI/3);
		
		Mesh mesh2 = new Mesh();
		mesh2.setReader(new OBJMeshReader()); 
		mesh2.readFromFile("src/car_rotated.obj");
		
		assertFalse(mesh.equals(mesh2));
		
	}
	
	@Test
	void toStringTest() throws WrongFileFormatException {
		
		Mesh mesh = new Mesh(); 
		mesh.setReader(new OBJMeshReader()); 
		mesh.readFromFile("src/string.obj");
		String s = "";
		for(Polygon p : mesh.polygons) {
			for(Vertex v : p.vertices) {
				s=v.toString();
				break;
			}
			break;
		}

		assertEquals(s,"5.1 1.2 0.3");
		
	}
	
	@Test
	void hashTest() throws WrongFileFormatException {
		
		Mesh mesh = new Mesh(); 
		mesh.setReader(new OBJMeshReader()); 
		mesh.readFromFile("src/testhash.obj");
		int a = mesh.hashCode();
		
		assertEquals(18,a);
		
	}
	
	@Test
	void DNE() throws WrongFileFormatException {
		
		Mesh mesh = new Mesh(); 
		mesh.setReader(new OBJMeshReader()); 
		mesh.readFromFile("src/DNE.obj");
		

		Mesh mesh2 = new Mesh(); 
		mesh2.setReader(new OFFMeshReader()); 
		mesh2.readFromFile("src/DNE.off");
		
		Mesh mesh3 = new Mesh(); 
		mesh3.setReader(new PLYMeshReader()); 
		mesh3.readFromFile("src/DNE.ply");
		
		
		
	}
	
	@Test
	void moreThan3() throws WrongFileFormatException {
		
		Mesh mesh = new Mesh(); 
		mesh.setReader(new OBJMeshReader()); 
		mesh.readFromFile("src/more.obj");
		

	}
	
	
	
	
	
		
}

