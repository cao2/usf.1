package platu.util;

import java.util.*;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileIO {
	//* Class data member section
	FileOutputStream out;
	
	
	//* Class function member section
	
	public void openFile(String fileName) 
	{
        try{
            out = new FileOutputStream(fileName);
        }
        catch ( IOException e ){
            System.out.println("Error open file " + fileName);
            System.exit(0);
        }
        System.out.println("opened file " + fileName);
	}
	
	public void closeFile() 
	{
        try{
            out.close();
        }
        catch ( IOException e ){
            System.out.println("Unable to close file ");
            System.exit(0);
        }
	}
	
	public void write(int input) throws IOException {
		byte[] byteArray = FileIO.int2ByteArray(input);
        System.out.print("write ");

        try {
            for (int i = 3; i >= 0; i--) {
                this.out.write(byteArray[i]);
                System.out.print(byteArray[i] + " ");
            }
            System.out.println();
        } 
        catch ( IOException e ){
            System.out.println("Error writing to file");
            System.exit(0);
        }
    }
	
	private static byte[] int2ByteArray(int input) 
	{ 
		byte[] byteArray = new byte[4];
		for (int i = 0; i < 4; i++) {
			byteArray[i] = (byte)(input >>> (i * 8));
		}
		return byteArray;
	}
}
