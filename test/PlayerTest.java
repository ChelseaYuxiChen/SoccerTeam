import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

/**
 * This class contains tests for the Player class.
 */
public class PlayerTest {

  private Player player;

  /**
   * Sets up a player object before each test.
   */
  @Before
  public void setUp() {
    player = new Player("Doe", "John", LocalDate.of(2017, 1, 1), Position.FORWARD, 3);
  }

  /**
   * Tests the creation of a player with valid attributes.
   */
  @Test
  public void testPlayerCreationValid() {
    assertEquals("Doe", player.getLastName());
    assertEquals("John", player.getFirstName());
    assertEquals(LocalDate.of(2017, 1, 1), player.getBirthdate());
    assertEquals(Position.FORWARD, player.getPreferredPosition());
    assertEquals(3, player.getSkillLevel());
  }

  /**
   * Tests the creation of a player with an empty last name.
   * Expects an IllegalArgumentException to be thrown.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testPlayerCreationInvalidLastName() {
    new Player("", "John", LocalDate.of(2017, 1, 1), Position.FORWARD, 3);
  }

  /**
   * Tests setting a birthdate in the future.
   * Expects an IllegalArgumentException to be thrown.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSetBirthdateInFuture() {
    player.setBirthdate(LocalDate.now().plusDays(1));
  }

  /**
   * Tests setting a birthdate older than ten years.
   * Expects an IllegalArgumentException to be thrown.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSetBirthdateOlderThanTen() {
    player.setBirthdate(LocalDate.of(2000, 1, 1));
  }

  /**
   * Tests setting an invalid skill level.
   * Expects an IllegalArgumentException to be thrown.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSetInvalidSkillLevel() {
    player.setSkillLevel(6);
  }

  /**
   * Tests setting a valid jersey number.
   */
  @Test
  public void testSetValidJerseyNumber() {
    Integer jerseyNumber = 10;
    player.setJerseyNumber(jerseyNumber);
    assertEquals(jerseyNumber, player.getJerseyNumber());
  }

  /**
   * Tests setting an invalid jersey number (0).
   * Expects an IllegalArgumentException to be thrown.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSetInvalidJerseyNumber() {
    player.setJerseyNumber(0);
  }

  /**
   * Tests setting a null assigned position.
   * Expects an IllegalArgumentException to be thrown.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSetNullAssignedPosition() {
    player.setAssignedPosition(null);
  }

  /**
   * Tests the toString method of the Player class.
   * Verifies the string representation of the player object.
   */
  @Test
  public void testToString() {
    String expectedString = "Player{" + "lastName: 'Doe'" + ", firstName: 'John'"
        + ", birthdate: 2017-01-01" + ", preferredPosition: FORWARD"
        + ", skillLevel: 3" + ", jerseyNumber: null"
        + ", assignedPosition: null" + ", id: '" + player.getId()
        + "'" + '}';
    assertEquals(expectedString, player.toString());
  }
}
