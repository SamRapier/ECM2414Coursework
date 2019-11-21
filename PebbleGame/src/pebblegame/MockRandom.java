package pebblegame;


import java.io.SequenceInputStream;
import java.util.Random;

public class MockRandom extends Random{
	public int sequentialNum = 0;

	public MockRandom(){
		super();
	}
	
	@Override
	public int nextInt(){
		return sequentialNum++;
	}
	
	@Override
	public int nextInt(int bound){
		if (sequentialNum >= bound - 1){
			sequentialNum = 0;
		} else {
			sequentialNum++;
		}
		return sequentialNum;
	}
}