package day25;

import java.math.BigInteger;

public class CryptDevice {

	private static final BigInteger M = BigInteger.valueOf(20201227);
	
	private BigInteger loopSize;
	
	private long publicKey;
	
	public CryptDevice(long publicKey) {
		this.publicKey = publicKey;
	}
	
	public long getPublicKey() {
		return publicKey;
	}
	
	private BigInteger transformSubject(BigInteger subject) {
		return subject.modPow(loopSize, M);
	}
	
	public int calcLoopSize() {
		BigInteger publicKey = BigInteger.valueOf(this.publicKey);
		this.loopSize = BigInteger.ONE;
		
		BigInteger seven = BigInteger.valueOf(7);
		while (!transformSubject(seven).equals(publicKey)) {
			this.loopSize = this.loopSize.add(BigInteger.ONE);
		}
		
		return loopSize.intValue();
	}
	
	public long calcEncryptionKey(long otherPubKey) {
		return transformSubject(BigInteger.valueOf(otherPubKey)).longValue();
	}
	
	public static void main(String[] args) {
		// 1965712
		// 19072108
		CryptDevice key = new CryptDevice(1965712);
		System.out.println(key.calcLoopSize());
		System.out.println(key.calcEncryptionKey(19072108));
	}
	
}
