/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pebblegame;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author samra
 */
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
