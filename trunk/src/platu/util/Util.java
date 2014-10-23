package platu.util;

public class Util {
	static public long intPair2Long(int left, int right) {
		long result = left;
		result = result << 32;
		result = result | right;
		return result;
	}

	static public Pair<Integer, Integer> long2IntPair(long arg) {
		int left = (int)(arg >>> 32);
		int right = (int) (arg & 0x00000000FFFFFFFF);
		return new Pair<Integer, Integer>(left, right);
	}
}
