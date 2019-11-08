public class WhiteBag extends Bag{
	private String fileLocation;
	private String[] pebbleArr;

	public WhiteBag(int bagNum){
		Scanner sc = new Scanner(System.in);
        System.out.printf("Enter location of bag number %d to load: ", bagNum);
        fileLocation = scan.nextLine();
		sc.close();
	}

	public void AddPebble(int newPebbleWeight){
		String[] tmpArr = String[pebbleArr.length + 1];
		for(int i = 0; i < pebbleArr.length; i++){
			tmpArr[i] = pebbleArr[i];
		}
		tmpArr[-1] = String.parseString(newPebbleWeight);
		pebbleArr = tmpArr;

		savePebbles(fileLocation, pebbleArr);
	}

	

}