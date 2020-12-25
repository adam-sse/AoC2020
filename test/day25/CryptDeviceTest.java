package day25;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CryptDeviceTest {

	@Test
	public void breakLoopSize1() {
		CryptDevice cd = new CryptDevice(5764801);
		assertEquals(8, cd.calcLoopSize());
	}
	
	@Test
	public void breakLoopSize2() {
		CryptDevice cd = new CryptDevice(17807724);
		assertEquals(11, cd.calcLoopSize());
	}
	
	@Test
	public void calcEncryption() {
		CryptDevice key = new CryptDevice(5764801);
		CryptDevice door = new CryptDevice(17807724);
		
		key.calcLoopSize();
		door.calcLoopSize();
		
		assertEquals(14897079L, key.calcEncryptionKey(door.getPublicKey()));
		assertEquals(14897079L, door.calcEncryptionKey(key.getPublicKey()));
	}
	
}
