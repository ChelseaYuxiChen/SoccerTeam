import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * This class represents a team in a soccer league.
 * A team has a list of players.
 */
public class Team {
  private List<Player> players;
  private List<Player> startingLineup;

  /**
   * Creates a team with an empty list of players.
   */
  public Team() {
    this.players = new ArrayList<>();
    this.startingLineup = new ArrayList<>();
  }

  /**
   * Returns the list of players in the team.
   *
   * @return the list of players in the team
   */
  public List<Player> getPlayers() {
    return players;
  }

  /**
   * Returns the list of players in the team sorted by last name.
   *
   * @return the list of players sorted by last name
   */
  public List<Player> getPlayersSortedByLastName() {
    List<Player> sortedPlayers = new ArrayList<>(players);
    sortedPlayers.sort(Comparator.comparing(Player::getLastName));
    return sortedPlayers;
  }

  /**
   * Adds a player to the team.
   *
   * @param player the player to add
   */
  public void addPlayer(Player player) {
    if (player == null) {
      throw new IllegalArgumentException("Player cannot be null");
    }
    this.players.add(player);
  }

  /**
   * Removes a player from the team.
   *
   * @param player the player to remove
   */
  public void removePlayer(Player player) {
    if (player == null) {
      throw new IllegalArgumentException("Player cannot be null");
    }
    if (!this.players.remove(player)) {
      throw new IllegalArgumentException("Player not found in the team");
    }
  }

  /**
   * Sets the starting lineup of the team.
   *
   * @param startingLineup the starting lineup of the team
   */
  public void setStartingLineup(List<Player> startingLineup) {
    if (startingLineup == null) {
      throw new IllegalArgumentException("Starting lineup cannot be null");
    }
    this.startingLineup = startingLineup;
  }

  /**
   * Returns the starting lineup of the team.
   *
   * @return the starting lineup of the team
   */
  public List<Player> getStartingLineup() {
    return startingLineup;
  }

  /**
   * Returns the starting lineup sorted by position (goalie, defender, midfielder, forward)
   * and alphabetically within each position.
   *
   * @return the sorted list of players in the starting lineup
   */
  public List<Player> getSortedStartingLineup() {
    startingLineup.sort(Comparator.comparing((Player p) -> p.getAssignedPosition().ordinal())
        .thenComparing(Player::getLastName)
        .thenComparing(Player::getFirstName));
    return startingLineup;
  }

  /**
   * Returns a string representation of the team.
   *
   * @return a string representation of the team
   */
  @Override
  public String toString() {
    return "Team{"
        + "players: " + players
        + '}';
  }
}
