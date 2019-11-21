package pebblegame;


import java.io.SequenceInputStream;
import java.util.Random;

public class MockRandom extends Random{
	public int sequentialNum = 0;

	public MockRandom(){
		// call the actual random constructor
		super();
	}
	
	@Override
	public int nextInt(){
		// override the nextInt function and just return the sequential num incremented
		return sequentialNum++;
	}
	
	@Override
	public int nextInt(int bound){
		// override the nextInt function which takes a variable
		// if the sequential number is equal to the bound -1, reset it to 0
		if (sequentialNum >= bound - 1){
			sequentialNum = 0;
		} else {
			// otherwise increment it by 1
			sequentialNum++;
		}
		// return the sequential num
		return sequentialNum;
	}
}