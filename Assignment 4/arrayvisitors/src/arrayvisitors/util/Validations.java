package arrayvisitors.util;

public class Validations {
	public void validate(String input1, String input2, String output1, String output2) {
		if(input1.equals(input2)) {
			throw new IllegalArgumentException("Both input files have the same path and name  : Input 1: "+ input1 +", Input 2: " + input2);
		}
		if(output1.equals(output2)) {
			throw new IllegalArgumentException("Both output files have the same path and name  : Output 1: "+ output1 +", Output 2: " + output2);
		}
	}

	@Override
	public String toString() {
		return "Validations [getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}	
}
