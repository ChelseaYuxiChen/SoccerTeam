import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * This class represents a soccer team model.
 * A soccer team model has a list of players and a team.
 */
public class SoccerTeamModelImpl implements SoccerTeamModel {

  public static final int TEAM_UPPER_LIMIT = 20;
  public static final int TEAM_LOWER_LIMIT = 10;
  public static final int LINEUP_LIMIT = 7;
  public static final int NUMBER_OF_GOALIE = 1;
  public static final int NUMBER_OF_DEFENDERS = 2;
  public static final int NUMBER_OF_MIDFIELDERS = 3;
  public static final int NUMBER_OF_FORWARD = 1;
  private List<Player> players;
  private Team team;

  /**
   * Creates a soccer team model with an empty list of players and a team.
   */
  public SoccerTeamModelImpl() {
    this.team = new Team();
    this.players = new ArrayList<>();
  }

  /**
   * Adds a player to the soccer team model.
   *
   * @param lastName          the last name of the player
   * @param firstName         the first name of the player
   * @param birthdate         the birthdate of the player
   * @param preferredPosition the preferred position of the player
   * @param skillLevel        the skill level of the player
   * @throws IllegalArgumentException if the player details are invalid
   */
  @Override
  public void addPlayer(String lastName, String firstName, LocalDate birthdate,
                        Position preferredPosition, int skillLevel) {
    // validate player details
    if (lastName == null || lastName.trim().isEmpty()
        || firstName == null || firstName.trim().isEmpty()
        || birthdate == null || preferredPosition == null) {
      throw new IllegalArgumentException("Invalid player details provided.");
    }
    if (skillLevel < 1 || skillLevel > 5) {
      throw new IllegalArgumentException("Skill level must be between 1 and 5.");
    }
    Player player = new Player(lastName, firstName, birthdate, preferredPosition, skillLevel);
    this.getPlayers().add(player);
  }

  /**
   * Removes a player from the soccer team model.
   *
   * @param id the ID of the player to remove
   * @throws IllegalArgumentException if the ID is null or empty
   * @throws NullPointerException     if the player with the given ID is not found
   */
  @Override
  public void removePlayer(String id) throws IllegalArgumentException {
    // validate ID
    if (id == null || id.trim().isEmpty()) {
      throw new IllegalArgumentException("ID cannot be null or empty");
    }

    // remove player from players list
    boolean removedFromPlayers = this.getPlayers().removeIf(player -> player.getId().equals(id));
    // remove player from team
    boolean removedFromTeamPlayers = false;
    if (this.getTeam().getPlayers() != null) {
      removedFromTeamPlayers =
          this.getTeam().getPlayers().removeIf(player -> player.getId().equals(id));
    }
    // remove player from starting lineup
    boolean removedFromStartingLineup = false;
    if (this.getTeam().getStartingLineup() != null) {
      removedFromStartingLineup =
          this.getTeam().getStartingLineup().removeIf(player -> player.getId().equals(id));
    }

    // throw exception if player not found
    if (!removedFromPlayers && !removedFromTeamPlayers && !removedFromStartingLineup) {
      throw new NullPointerException("Player with ID " + id + " not found.");
    }
  }

  /**
   * Returns the list of players in the soccer team model.
   *
   * @return the list of players in the soccer team model
   */
  @Override
  public List<Player> getPlayers() {
    return players;
  }

  /**
   * Generates a team for the soccer team model with assigned jersey number.
   *
   * @throws IllegalStateException if size of players is less than the 10.
   */
  @Override
  public void generateTeam() throws IllegalStateException {
    // clear the team players if old team exists
    if (!this.getTeam().getPlayers().isEmpty()) {
      this.getTeam().getPlayers().clear();
      this.getTeam().getStartingLineup().clear();
    }

    // get the total players
    List<Player> players = this.getPlayers();

    // check minimum player requirement
    if (players.size() < TEAM_LOWER_LIMIT) {
      throw new IllegalStateException(" Team cannot be created unless more players are added");
    } else {
      // sort players by skill level
      players.sort(Comparator.comparingInt(Player::getSkillLevel).reversed());
      // remove jersey number if players exceed the team upper limit
      if (players.size() > TEAM_UPPER_LIMIT) {
        for (int i = TEAM_UPPER_LIMIT; i < players.size(); i++) {
          players.get(i).setJerseyNumber(null);
        }
      }

      // assign players to team
      List<Player> needJerseyNumberPlayers = new ArrayList<>();
      for (int i = 0; i < Math.min(TEAM_UPPER_LIMIT, players.size()); i++) {
        Player player = players.get(i);
        this.getTeam().addPlayer(player);
        // check if player has jersey number, if not add to needJerseyNumberPlayers
        if (player.getJerseyNumber() == null) {
          needJerseyNumberPlayers.add(player);
        }
      }

      // generate available jersey number
      List<Integer> jerseyNumbers = new ArrayList<>();
      for (int i = 0; i < Math.min(TEAM_UPPER_LIMIT, players.size()); i++) {
        jerseyNumbers.add(i + 1);
      }

      // remove already assigned jersey numbers
      for (Player player : this.getPlayers()) {
        if (player.getJerseyNumber() != null) {
          jerseyNumbers.remove(player.getJerseyNumber());
        }
      }

      // assign jersey number to players who don't have jersey number
      for (int i = 0; i < needJerseyNumberPlayers.size(); i++) {
        needJerseyNumberPlayers.get(i).setJerseyNumber(jerseyNumbers.get(i));
      }
    }
  }

  /**
   * Generates a starting lineup for the team with assigned position.
   */
  @Override
  public void generateStartingLineup() {
    // check if team is generated
    if (this.getTeam().getPlayers().isEmpty()) {
      generateTeam();
    }
    List<Player> players = this.getTeam().getPlayers();

    // get the first 7 availablePlayers in the team
    List<Player> availablePlayers = new ArrayList<>();
    for (int i = 0; i < LINEUP_LIMIT; i++) {
      availablePlayers.add(players.get(i));
    }

    // store the available positions
    Map<Position, Integer> availablePositions = new HashMap<>();
    availablePositions.put(Position.GOALIE, NUMBER_OF_GOALIE);
    availablePositions.put(Position.DEFENDER, NUMBER_OF_DEFENDERS);
    availablePositions.put(Position.MIDFIELDER, NUMBER_OF_MIDFIELDERS);
    availablePositions.put(Position.FORWARD, NUMBER_OF_FORWARD);

    // assign positions based on preferred position
    List<Player> startingLineup = new ArrayList<>();
    assignPosition(startingLineup, availablePlayers, Position.GOALIE, availablePositions);
    assignPosition(startingLineup, availablePlayers, Position.DEFENDER, availablePositions);
    assignPosition(startingLineup, availablePlayers, Position.MIDFIELDER, availablePositions);
    assignPosition(startingLineup, availablePlayers, Position.FORWARD, availablePositions);

    // fill remaining positions with available players
    while (!availablePlayers.isEmpty()) {
      for (Map.Entry<Position, Integer> entry : availablePositions.entrySet()) {
        Position position = entry.getKey();
        Integer count = entry.getValue();
        for (int i = 0; i < count; i++) {
          assignPosition(startingLineup, availablePlayers, position, availablePositions, true);
        }
      }
    }
    this.getTeam().setStartingLineup(startingLineup);
  }

  /**
   * Assigns a position to a player.
   *
   * @param startingLineup     the starting lineup
   * @param availablePlayers   the available players
   * @param position           the position to assign
   * @param availablePositions the available positions
   */
  private void assignPosition(List<Player> startingLineup, List<Player> availablePlayers,
                              Position position, Map<Position, Integer> availablePositions) {
    assignPosition(startingLineup, availablePlayers, position, availablePositions, false);
  }

  /**
   * Assigns a position to a player.
   *
   * @param startingLineup     the starting lineup
   * @param availablePlayers   the available players
   * @param position           the position to assign
   * @param availablePositions the available positions
   * @param forceAssign        whether to force assign the position
   */

  private void assignPosition(List<Player> startingLineup, List<Player> availablePlayers,
                              Position position, Map<Position, Integer> availablePositions,
                              boolean forceAssign) {
    Iterator<Player> iterator = availablePlayers.iterator();
    while (iterator.hasNext()) {
      Player player = iterator.next();
      if ((availablePositions.containsKey(position) && availablePositions.get(position) > 0) && (
          player.getPreferredPosition().equals(position) || forceAssign)) {
        player.setAssignedPosition(position);
        startingLineup.add(player);
        iterator.remove();
        availablePositions.put(position, availablePositions.get(position) - 1);
      }
    }
  }

  /**
   * Gets the soccer team.
   *
   * @return the soccer team
   */
  @Override
  public Team getTeam() {
    return team;
  }
}
