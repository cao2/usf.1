package platu.NativeLib;

import platu.common.SetIntTuple;

public class NativeBinaryTree extends SetIntTuple
{
	static
	{
		try
		{
			System.loadLibrary("NativeBinaryTree");
		}
		
		catch(Exception e)
		{
			
		}
		
		System.out.println();
	}	
	
	public NativeBinaryTree(){};
	
	public native int add(int[] IntArray);

	public native boolean contains(int[] IntArray);

	public native int size();

	public native String stats();

}
