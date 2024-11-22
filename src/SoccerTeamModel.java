import java.time.LocalDate;
import java.util.List;

/**
 * SoccerTeamModel is an interface that defines the methods that
 * a SoccerTeamModel class must implement. The SoccerTeamModel class
 * is responsible for managing the data of the soccer team.
 * This includes generating the team, generating the starting lineup,
 * adding a player, removing a player,
 * and getting the players and team.
 */
public interface SoccerTeamModel {

  /**
   * Generates a team for the soccer team model.
   */
  public void generateTeam();

  /**
   * Generates a starting lineup for the team.
   */
  public void generateStartingLineup();

  /**
   * Adds a player to the team.
   *
   * @param lastName          the last name of the player
   * @param firstName         the first name of the player
   * @param birthdate         the birthdate of the player
   * @param preferredPosition the preferred position of the player
   * @param skillLevel        the skill level of the player
   */
  public void addPlayer(String lastName, String firstName, LocalDate birthdate,
                        Position preferredPosition,
                        int skillLevel);

  /**
   * Removes a player from the team.
   *
   * @param id the id of the player to remove
   */
  public void removePlayer(String id);

  /**
   * Returns the list of players in the soccer team model.
   *
   * @return the list of players in the soccer team model
   */
  public List<Player> getPlayers();

  /**
   * Returns the team.
   *
   * @return the team
   */
  public Team getTeam();
}
