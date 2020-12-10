package day02;

import java.util.ArrayList;
import java.util.List;

import util.Util;

public class PasswordChecker {

	public static void main(String[] args) {
		List<Password> passwords = new ArrayList<>(1100);
		
		Util.readInputFile("/day02/input.txt")
			.forEach((line) -> passwords.add(new Password(line)));
		
		int count = 0;
		for (Password pw : passwords) {
			if (pw.isValid()) {
				count++;
			}
		}
		System.out.println(count);
	}
	
}
