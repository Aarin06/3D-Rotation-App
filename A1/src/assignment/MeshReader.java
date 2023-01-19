package assignment;

import java.util.*;

public interface MeshReader {
	
	public HashSet<Polygon> read(String filename) throws WrongFileFormatException;
	
}
