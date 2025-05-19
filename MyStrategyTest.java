import student.micro.*;
import static org.assertj.core.api.Assertions.*;
import student.micro.battleship.*;
import student.micro.battleship.BattleshipTestBase.*;

// -------------------------------------------------------------------------
/**
 *  This class tests a strategy to place hits on
 *  the opponents board. It also places ships on my own board
 *  and changes their location each time a new game is started
 *
 *  @author Victor Mukete (906540931)
 *  @version (2025.03.13)
 */
public class MyStrategyTest
    extends TestCase
{
    //~ Fields ................................................................
    private TestableGameState gameState;
    private TestableBoard myFleet;
    private TestableBoard oppFleet;
    private MyStrategy myStrat;

    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new MyStrategyTest test object.
     */
    public MyStrategyTest()
    {
        // The constructor is usually empty in unit tests, since it runs
        // once for the whole class, not once for each test method.
        // Per-test initialization should be placed in setUp() instead.
    }


    //~ Methods ...............................................................

    // ----------------------------------------------------------
    /**
     * Sets up the test fixture.
     * Called before every test case method.
     */
    public void setUp()
    {
        /*# Insert your own setup code here */
        gameState = new TestableGameState();
        myStrat = new MyStrategy();
        myFleet = gameState.getMyBoard();        
        oppFleet = gameState.getOpponentsBoard();
    }


    // ----------------------------------------------------------
    /*# Insert your own test methods here */
    /**
     * Tests the canPlayDeviouslyMethod
     * change position
     */
    public void testCanPlayDeviously()
    {
        assertThat(myStrat.canPlayDeviously()).isFalse();
    }
    
    /**
     * tests the callNextShotMethod
     */
    
    public void testCallNextShot()
    {
        CallShotMove move = myStrat.callNextShot(gameState);
        oppFleet.applyMove(move);
        // System.out.println(oppFleet);
        // System.out.println(move);
        assertThat(move.getX()).isEqualTo(0);
        assertThat(move.getY()).isEqualTo(0);
        
        myStrat.newGame();
        for (int i = 0; i < 11; i++)
        {
            myStrat.callNextShot(gameState);
        }
        CallShotMove move2 = myStrat.callNextShot(gameState);
        oppFleet.applyMove(move2);
        // System.out.println();
        // System.out.println(oppFleet);
        // System.out.println(move);
        assertThat(move.getX()).isEqualTo(0);
        assertThat(move.getY()).isEqualTo(0);
    }
    
    /**
     * tests the placeShips method
     * */
    public void testPlaceShips()
    {   
        ShipPlacementMove myShips = myStrat.placeShips(gameState);
        myFleet.applyMove(myShips);
        System.out.println(myShips);
        System.out.println(myFleet);
        assertThat(myShips.isValid()).isTrue();
              
        myStrat.newGame();
        ShipPlacementMove myShips2 = myStrat.placeShips(gameState);
        myFleet.applyMove(myShips2);
        System.out.println(myShips2);
        System.out.println(myFleet);
        assertThat(myShips.isValid()).isTrue();
        assertThat(myShips != myShips2).isTrue();
        
    }
    
    
    /**
     * Tests the newGame method
     */
    public void testNewGame()
    {
        myStrat.newGame();
        CallShotMove move = myStrat.callNextShot(gameState);
        assertThat(move.getX()).isEqualTo(0);
    }
    
    /**
     * Test the getName method
     */
    public void testGetName()
    {
        assertThat(myStrat.getName())
            .isEqualTo("Inevitable: 906540931");
    }
}
