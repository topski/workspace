package CMSC335_Project1;

import java.util.Scanner;

class ArtifactType {
	String type;
	String name;

	public ArtifactType(Scanner ss, String pn) {
		this.name = ss.next();
		this.type = pn;
	}
}