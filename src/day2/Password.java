package day2;

public class Password {
	
	private int pos1;
	
	private int pos2;
	
	private char letter;
	
	private String password;
	
	public Password(String line) {
		int dashIndex = line.indexOf('-');
		int spaceIndex = line.indexOf(' ');
		int colonIndex = line.indexOf(':');
		
		String min = line.substring(0, dashIndex);
		String max = line.substring(dashIndex + 1, spaceIndex);
		
		this.pos1 = Integer.parseInt(min);
		this.pos2 = Integer.parseInt(max);
		
		this.letter = line.charAt(spaceIndex + 1);
		this.password = line.substring(colonIndex + 2);
	}
	
	public boolean isValid() {
		char c1 = password.charAt(pos1 - 1);
		char c2 = password.charAt(pos2 - 1);
		
		return c1 == letter ^ c2 == letter;
	}
	
}
