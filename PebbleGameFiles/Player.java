import java.util.*;
public class Player{
    private List<Integer> playerPebbles = new ArrayList<>();
    public Player(BlackBag bBag){
        for(int i = 0; i<10; i++){
            drawFromBag(bBag);
        }
    }
    public void drawFromBag(BlackBag bBag){
        playerPebbles.add(bBag.takeRandPebble());
    }
    public void removePebble(int weight, WhiteBag wBag){
        wBag.addPebble(weight);
        int weightIndex = playerPebbles.indexOf(weight);
        playerPebbles.remove(weightIndex);
    }
    public Boolean checkWeight(){
        int totalWeight = 0;
        for(int weight: playerPebbles){
            totalWeight += weight;
        }
        if(totalWeight == 100){
            return true;
        }
        return false;
    }

}