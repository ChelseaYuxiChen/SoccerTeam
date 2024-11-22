import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.List;
import org.junit.Before;
import org.junit.Test;


/**
 * This class tests the SoccerTeamModelImpl class.
 */
public class SoccerTeamModelImplTest {

  private SoccerTeamModelImpl model;

  /**
   * Sets up the test fixture.
   * Initializes the SoccerTeamModelImpl instance before each test.
   */
  @Before
  public void setUp() {
    model = new SoccerTeamModelImpl();
  }

  /**
   * Tests the addPlayer method.
   * Verifies that a player is correctly added to the model.
   */
  @Test
  public void testAddPlayer() {
    model.addPlayer("Doe", "John", LocalDate.now().minusYears(5), Position.FORWARD, 5);
    List<Player> players = model.getPlayers();
    assertEquals(1, players.size());
    assertEquals("Doe", players.get(0).getLastName());
  }

  /**
   * Tests the addPlayer method with an invalid player.
   * Expects an IllegalArgumentException to be thrown.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddInvalidPlayer() {
    model.addPlayer(null, "John", LocalDate.now().minusYears(5), Position.FORWARD, 5);
  }

  /**
   * Tests the addPlayer method with an age above the limit.
   * Expects an IllegalArgumentException to be thrown.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddPlayerWithAgeAboveLimit() {
    model.addPlayer("Doe", "John", LocalDate.now().minusYears(11), Position.FORWARD, 5);
  }

  /**
   * Tests the removePlayer method.
   * Verifies that a player is correctly removed from the model.
   */
  @Test
  public void testRemovePlayer() {
    model.addPlayer("Doe", "John", LocalDate.now().minusYears(5), Position.FORWARD, 5);
    String id = model.getPlayers().get(0).getId();
    model.removePlayer(id);
    assertTrue(model.getPlayers().isEmpty());
  }

  /**
   * Tests the removePlayer method with a non-existent player ID.
   * Expects a NullPointerException to be thrown.
   */
  @Test(expected = NullPointerException.class)
  public void testRemoveNonExistentPlayer() {
    model.removePlayer("nonexistent-id");
  }

  /**
   * Tests the generateTeam method.
   * Verifies that a team with the correct number of players is generated.
   */
  @Test
  public void testGenerateTeam() {
    model.addPlayer("Doe", "John", LocalDate.now().minusYears(5), Position.FORWARD, 5);
    model.addPlayer("Smith", "Jane", LocalDate.now().minusYears(5), Position.DEFENDER, 4);
    model.addPlayer("Brown", "Charlie", LocalDate.now().minusYears(5), Position.MIDFIELDER, 3);
    model.addPlayer("Johnson", "Emily", LocalDate.now().minusYears(5), Position.GOALIE, 2);
    model.addPlayer("Williams", "Michael", LocalDate.now().minusYears(5), Position.DEFENDER, 3);
    model.addPlayer("Jones", "Olivia", LocalDate.now().minusYears(5), Position.MIDFIELDER, 4);
    model.addPlayer("Garcia", "David", LocalDate.now().minusYears(5), Position.MIDFIELDER, 5);
    model.addPlayer("Martinez", "Sophia", LocalDate.now().minusYears(5), Position.FORWARD, 2);
    model.addPlayer("Lopez", "Lucas", LocalDate.now().minusYears(5), Position.DEFENDER, 3);
    model.addPlayer("Miller", "Ava", LocalDate.now().minusYears(5), Position.MIDFIELDER, 4);
    model.generateTeam();
    assertEquals(10, model.getTeam().getPlayers().size());
  }

  /**
   * Tests the generateTeam method with insufficient players.
   * Expects an IllegalStateException to be thrown.
   */
  @Test(expected = IllegalStateException.class)
  public void testGenerateTeamWithInsufficientPlayers() {
    model.addPlayer("Doe", "John", LocalDate.now().minusYears(5), Position.FORWARD, 5);
    model.addPlayer("Smith", "Jane", LocalDate.now().minusYears(5), Position.DEFENDER, 4);
    model.addPlayer("Brown", "Charlie", LocalDate.now().minusYears(5), Position.MIDFIELDER, 3);
    model.addPlayer("Johnson", "Emily", LocalDate.now().minusYears(5), Position.GOALIE, 2);
    model.addPlayer("Williams", "Michael", LocalDate.now().minusYears(5), Position.DEFENDER, 3);
    model.generateTeam();
  }

  /**
   * Tests the generateStartingLineup method.
   * Verifies that the starting lineup is correctly generated.
   */
  @Test
  public void testGenerateStartingLineup() {
    model.addPlayer("Doe", "John", LocalDate.now().minusYears(5), Position.FORWARD, 5);
    model.addPlayer("Smith", "Jane", LocalDate.now().minusYears(5), Position.DEFENDER, 4);
    model.addPlayer("Brown", "Charlie", LocalDate.now().minusYears(5), Position.MIDFIELDER, 3);
    model.addPlayer("Johnson", "Emily", LocalDate.now().minusYears(5), Position.GOALIE, 2);
    model.addPlayer("Williams", "Michael", LocalDate.now().minusYears(5), Position.DEFENDER, 3);
    model.addPlayer("Jones", "Olivia", LocalDate.now().minusYears(5), Position.MIDFIELDER, 4);
    model.addPlayer("Garcia", "David", LocalDate.now().minusYears(5), Position.MIDFIELDER, 5);
    model.addPlayer("Martinez", "Sophia", LocalDate.now().minusYears(5), Position.FORWARD, 2);
    model.addPlayer("Lopez", "Lucas", LocalDate.now().minusYears(5), Position.DEFENDER, 3);
    model.addPlayer("Miller", "Ava", LocalDate.now().minusYears(5), Position.MIDFIELDER, 4);
    model.addPlayer("Cooper", "Sheldon", LocalDate.now().minusYears(5), Position.MIDFIELDER, 4);
    model.generateStartingLineup();
    List<Player> startingLineup = model.getTeam().getStartingLineup();

    // Check that the starting lineup has the right number of players for each position
    long goalies =
        startingLineup.stream().filter(p -> p.getAssignedPosition() == Position.GOALIE).count();
    long defenders =
        startingLineup.stream().filter(p -> p.getAssignedPosition() == Position.DEFENDER).count();
    long midfielders =
        startingLineup.stream().filter(p -> p.getAssignedPosition() == Position.MIDFIELDER).count();
    final long forwards =
        startingLineup.stream().filter(p -> p.getAssignedPosition() == Position.FORWARD).count();
    assertEquals(SoccerTeamModelImpl.NUMBER_OF_GOALIE, goalies);
    assertEquals(SoccerTeamModelImpl.NUMBER_OF_DEFENDERS, defenders);
    assertEquals(SoccerTeamModelImpl.NUMBER_OF_MIDFIELDERS, midfielders);
    assertEquals(SoccerTeamModelImpl.NUMBER_OF_FORWARD, forwards);
  }
}
