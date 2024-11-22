import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

/**
 * This class represents a player in a soccer team.
 * A player has a last name, a first name, a birthdate, a preferred position and a skill level.
 */
public class Player {
  private String lastName;
  private String firstName;
  private LocalDate birthdate;
  private Position preferredPosition;
  private int skillLevel;
  private Integer jerseyNumber;
  private Position assignedPosition;
  private final String id;

  /**
   * Creates a player with the given last name, first name, birthdate, preferred position and skill
   * level.
   *
   * @param lastName          the last name of the player
   * @param firstName         the first name of the player
   * @param birthdate         the birthdate of the player
   * @param preferredPosition the preferred position of the player
   * @param skillLevel        the skill level of the player
   */
  public Player(String lastName, String firstName, LocalDate birthdate, Position preferredPosition,
                int skillLevel) {
    setLastName(lastName);
    setFirstName(firstName);
    setBirthdate(birthdate);
    setPreferredPosition(preferredPosition);
    setSkillLevel(skillLevel);
    this.id = UUID.randomUUID().toString();
  }


  /**
   * Returns the last name of the player.
   *
   * @return the last name of the player
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * Sets the last name of the player.
   *
   * @param lastName the last name of the player
   * @throws IllegalArgumentException if the last name is null or empty
   */
  public void setLastName(String lastName) throws IllegalArgumentException {
    if (lastName == null || lastName.trim().isEmpty()) {
      throw new IllegalArgumentException("Last name cannot be null or empty");
    }
    this.lastName = lastName.trim();
  }

  /**
   * Returns the first name of the player.
   *
   * @return the first name of the player
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Sets the first name of the player.
   *
   * @param firstName the first name of the player
   * @throws IllegalArgumentException if the first name is null or empty
   */
  public void setFirstName(String firstName) throws IllegalArgumentException {
    if (firstName == null || firstName.trim().isEmpty()) {
      throw new IllegalArgumentException("First name cannot be null or empty");
    }
    this.firstName = firstName.trim();
  }

  /**
   * Returns the birthdate of the player.
   *
   * @return the birthdate of the player
   */
  public LocalDate getBirthdate() {
    return birthdate;
  }

  /**
   * Sets the birthdate of the player.
   *
   * @param birthdate the birthdate of the player
   * @throws IllegalArgumentException if the birthdate is null or in the future,
   *                                   or if the player's age is greater than 10 years
   */
  public void setBirthdate(LocalDate birthdate) throws IllegalArgumentException {
    if (birthdate == null || birthdate.isAfter(LocalDate.now())) {
      throw new IllegalArgumentException("Birthdate cannot be null or in the future");
    }
    if (Period.between(birthdate, LocalDate.now()).getYears() >= 10) {
      throw new IllegalArgumentException("Player's age must be less than 10 years");
    }
    this.birthdate = birthdate;
  }


  /**
   * Returns the skill level of the player.
   *
   * @return the skill level of the player
   */
  public int getSkillLevel() {
    return skillLevel;
  }

  /**
   * Sets the skill level of the player.
   *
   * @param skillLevel the skill level of the player
   * @throws IllegalArgumentException if the skill level is not between 1 and 5
   */
  public void setSkillLevel(int skillLevel) throws IllegalArgumentException {
    if (skillLevel < 1 || skillLevel > 5) {
      throw new IllegalArgumentException("Skill level must be between 1 and 5.");
    }
    this.skillLevel = skillLevel;
  }

  /**
   * Returns the jersey number of the player.
   *
   * @return the jersey number of the player
   */
  public Integer getJerseyNumber() {
    return jerseyNumber;
  }

  /**
   * Sets the jersey number of the player.
   *
   * @param jerseyNumber the jersey number of the player
   * @throws IllegalArgumentException if the jersey number is null or negative
   */
  public void setJerseyNumber(Integer jerseyNumber) throws IllegalArgumentException {
    if (jerseyNumber == null || jerseyNumber < 1 || jerseyNumber > 20) {
      throw new IllegalArgumentException("Jersey number cannot be null or negative");
    }
    this.jerseyNumber = jerseyNumber;
  }

  /**
   * Returns the preferred position of the player.
   *
   * @return the preferred position of the player
   */
  public Position getPreferredPosition() {
    return preferredPosition;
  }

  /**
   * Sets the preferred position of the player.
   *
   * @param preferredPosition the preferred position of the player
   * @throws IllegalArgumentException if the preferred position is null
   */
  public void setPreferredPosition(Position preferredPosition) throws IllegalArgumentException {
    if (preferredPosition == null) {
      throw new IllegalArgumentException("Preferred position cannot be null");
    }
    this.preferredPosition = preferredPosition;
  }

  /**
   * Returns the assigned position of the player.
   *
   * @return the assigned position of the player
   */
  public Position getAssignedPosition() {
    return assignedPosition;
  }

  /**
   * Sets the assigned position of the player.
   *
   * @param assignedPosition the assigned position of the player
   * @throws IllegalArgumentException if the assigned position is null
   */
  public void setAssignedPosition(Position assignedPosition) throws IllegalArgumentException {
    if (assignedPosition == null) {
      throw new IllegalArgumentException("Assigned position cannot be null");
    }
    this.assignedPosition = assignedPosition;
  }

  /**
   * Returns the unique identifier of the player.
   *
   * @return the unique identifier of the player
   */
  public String getId() {
    return id;
  }

  /**
   * Returns a string representation of the player.
   *
   * @return a string representation of the player
   */
  @Override
  public String toString() {
    return "Player{"
        + "lastName: '" + lastName + '\''
        + ", firstName: '" + firstName + '\''
        + ", birthdate: " + birthdate
        + ", preferredPosition: " + preferredPosition
        + ", skillLevel: " + skillLevel
        + ", jerseyNumber: " + jerseyNumber
        + ", assignedPosition: " + assignedPosition
        + ", id: '" + id + '\''
        + '}';
  }
}
