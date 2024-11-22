import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

/**
 * This class contains unit tests for the Team class.
 */
public class TeamTest {

  private Team team;
  private Player player1;
  private Player player2;
  private Player player3;
  private Player player4;
  private Player player5;

  /**
   * Sets up the test fixtures.
   * Initializes the Team and Player instances before each test.
   */
  @Before
  public void setUp() {
    team = new Team();
    player1 = new Player("Doe", "John", LocalDate.of(2018, 1, 1), Position.FORWARD, 3);
    player2 = new Player("Smith", "Jane", LocalDate.of(2017, 5, 15), Position.DEFENDER, 4);
    player3 = new Player("Brown", "Alice", LocalDate.of(2016, 3, 1), Position.MIDFIELDER, 2);
    player4 = new Player("Johnson", "Bob", LocalDate.of(2017, 4, 1), Position.GOALIE, 5);
    player5 = new Player("Perry", "Ketty", LocalDate.of(2017, 4, 1), Position.MIDFIELDER, 5);
  }

  /**
   * Tests the addPlayer method.
   * Verifies that a player is correctly added to the team.
   */
  @Test
  public void testAddPlayer() {
    team.addPlayer(player1);
    assertTrue(team.getPlayers().contains(player1));
  }

  /**
   * Tests the addPlayer method with a null player.
   * Expects an IllegalArgumentException to be thrown.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddNullPlayer() {
    team.addPlayer(null);
  }

  /**
   * Tests the removePlayer method.
   * Verifies that a player is correctly removed from the team.
   */
  @Test
  public void testRemovePlayer() {
    team.addPlayer(player1);
    team.removePlayer(player1);
    assertFalse(team.getPlayers().contains(player1));
  }

  /**
   * Tests the removePlayer method with a null player.
   * Expects an IllegalArgumentException to be thrown.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveNullPlayer() {
    team.removePlayer(null);
  }

  /**
   * Tests the removePlayer method with a non-existing player.
   * Expects an IllegalArgumentException to be thrown.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveNonExistingPlayer() {
    team.removePlayer(player1);
  }

  /**
   * Tests the getPlayersSortedByLastName method.
   * Verifies that the players are correctly sorted by last name.
   */
  @Test
  public void testGetPlayersSortedByLastName() {
    team.addPlayer(player1);
    team.addPlayer(player2);
    List<Player> sortedPlayers = team.getPlayersSortedByLastName();
    assertEquals(player1, sortedPlayers.get(0));
    assertEquals(player2, sortedPlayers.get(1));
  }

  /**
   * Tests the setStartingLineup method.
   * Verifies that the starting lineup is correctly set for the team.
   */
  @Test
  public void testSetStartingLineup() {
    List<Player> startingLineup = new ArrayList<>();
    startingLineup.add(player1);
    team.setStartingLineup(startingLineup);
    assertEquals(startingLineup, team.getStartingLineup());
  }

  /**
   * Tests the setStartingLineup method with a null lineup.
   * Expects an IllegalArgumentException to be thrown.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSetStartingLineupToNull() {
    team.setStartingLineup(null);
  }

  /**
   * Tests the getSortedStartingLineup method.
   * Verifies that the starting lineup is correctly sorted by position.
   */
  @Test
  public void testGetSortedStartingLineup() {
    final List<Player> startingLineup = new ArrayList<>();
    player1.setAssignedPosition(Position.FORWARD);
    player2.setAssignedPosition(Position.DEFENDER);
    player3.setAssignedPosition(Position.MIDFIELDER);
    player4.setAssignedPosition(Position.GOALIE);
    player5.setAssignedPosition(Position.MIDFIELDER);
    startingLineup.add(player1);
    startingLineup.add(player2);
    startingLineup.add(player3);
    startingLineup.add(player4);
    startingLineup.add(player5);
    team.setStartingLineup(startingLineup);

    List<Player> sortedLineup = team.getSortedStartingLineup();
    assertEquals(player4, sortedLineup.get(0));
    assertEquals(player2, sortedLineup.get(1));
    assertEquals(player3, sortedLineup.get(2));
    assertEquals(player5, sortedLineup.get(3));
    assertEquals(player1, sortedLineup.get(4));
  }

  /**
   * Tests the toString method.
   * Verifies that the string representation of the team is correct.
   */
  @Test
  public void testToString() {
    team.addPlayer(player1);
    String expectedString = "Team{players: [" + player1.toString() + "]}";
    assertEquals(expectedString, team.toString());
  }
}
