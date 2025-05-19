import student.micro.battleship.*;

//-------------------------------------------------------------------------
/**
 *  This class creates a strategy to place hits on
 *  the opponents board. It also places ships on my own board
 *  and changes their location each time a new game is started
 *
 *  @author Victor Mukete (906540931)
 *  @version (2025.03.10)
 */
public class MyStrategy
    implements BattleshipStrategy
{
    //~ Fields ................................................................
    private int nextRow;
    private int nextCol;
    private int turnCount;


    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Initializes a newly created MyStrategy object.
     */
    public MyStrategy()
    {
        super();
        /*# Do any work to initialize your class here. */
        nextRow = 0;
        nextCol = 0;
        turnCount = 0;
    }

    // use brute force
    //~ Methods ...............................................................
    /** this method uses the bruteforce strategy to call shots 
     * incrementally by row until a all rows and columns are filled
     * @param currentGameState determines at which point
     * the game is currently in
     * @return the method returns the position of where the next
     * shot is placed.
     */
    
    public  CallShotMove callNextShot(GameState currentGameState)
    {
        int row = nextRow;
        int col = nextCol;
        
        nextRow++;
        if (nextRow == 10)
        {
            nextRow = 0;
            nextCol++;
        }
        
        return new CallShotMove(row, col);
    }    
    
    /**
     * Returns the player name as Hokie ID
     *@return String determines the name of the strat
     */
    public String getName()
    {
        return "Inevitable: 906540931";
    }
    
    
    /** decides whether opponent ships have the ability to change
     * their location/position
     * @return boolean determines whether the opponent can change
     * the location of their ships
     */
    public boolean canPlayDeviously()
    {
        return false;
    }
    
    
    /** starts a new game/iteration of battleship 
     * 
     */
    public void newGame()
    {
        nextCol = 0;
        nextRow = 0;
        turnCount = (turnCount + 1) % 2;
    }  
    
    /** 
     * This method creates the ship placement and also alters the
     * ships depending on the number of times the game is played
     * @param currentGameState determines at which point
     * the game is currently in
     * @return the method returns the ship placement
     */
    public ShipPlacementMove placeShips(GameState currentGameState)
    {
        String ship1 = 
            ". . . . . . BBBB" +
            ". . DDD . . . . ." +
            ". . . . . . . . . ." +
            ". . . . . . . . PP" +
            "C . . . . . . . . ." +
            "C . . . . . . . . ." +
            "C . . . . . . . . ." +
            "C . . . . . . S . ." +
            "C . . . . . . S . ." +
            ". . . . . . . S . .";
        
        String ship2 =  
            "S . . DDD . . . B" +
            "S . . . . . . . B ." +
            "S . . . . . . . B ." +
            ". . . . . . . . B ." +
            ". . . . . . . . . ." +
            ". . . . . . . P . ." +
            ". . . . . . . P . ." +
            ". . . . . . . . . ." +
            ". . . . . . . . . ." +
            "CCCCC . . . . .";
        
        // turnCount++;
        if (turnCount % 2 == 0)
        {
            return new ShipPlacementMove(currentGameState, ship1);
        }
        else
        {
            return new ShipPlacementMove(currentGameState, ship2);
        }
    }
}
