package pebblegame;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author samra
 */
public class PebbleGame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }

    public class Player{
        private List<Integer> playerPebbles = new ArrayList<>();

        public Player(BlackBag bBag){
            for(int i = 0; i<10; i++){
                drawFromBag(bBag);
            }
        }

        public void drawFromBag(BlackBag bBag){
            playerPebbles.add(bBag.takeRandomPebble());
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
    
    
}
