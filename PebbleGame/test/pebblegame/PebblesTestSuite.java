package pebblegame;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import pebblegame.BlackBag;
import pebblegame.WhiteBag;
import pebblegame.FileHelpers;
import pebblegame.PebbleGame;

@RunWith(Suite.class)
@Suite.SuiteClasses({BlackBagTest.class, WhiteBagTest.class, FileHelpersTest.class, PebbleGameTest.class})
public class PebblesTestSuite {
// empty , as the class is just a holder
// for the annotations above
} 