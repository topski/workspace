package CMSC335_Project1;

import java.util.Scanner;

class CreatureType {
	int minEmpathy = 0;
	int rangeEmpathy = 100;
	int minFear = 0;
	int rangeFear = 100;
	int minCapacity = 0;
	int rangeCapacity = 300;
	double minAge = 12.0D;
	double rangeAge = 500.0D;
	double minHeight = 20.0D;
	double rangeHeight = 300.0D;
	double minWeight = 0.0D;
	double rangeWeight = 1000.0D;
	String type;
	char sex;

	public CreatureType(Scanner ss) {
		this.type = ss.next();
		this.sex = ss.next().charAt(0);
	}
}