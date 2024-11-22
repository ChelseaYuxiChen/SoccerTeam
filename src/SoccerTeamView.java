
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


/**
 * The view class for the Soccer Team Management application.
 */
public class SoccerTeamView {
  private JFrame frame;
  private JTable table;
  private DefaultTableModel tableModel;
  private JButton addButton;
  private JButton deleteButton;
  private JButton generateTeamButton;
  private JButton generateLineupButton;

  /**
   * Constructs a new SoccerTeamView object.
   */
  public SoccerTeamView() {
    // Create the frame
    frame = new JFrame("Soccer Team Management");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(800, 600);

    // Create the table
    tableModel = new DefaultTableModel(
        new Object[] {"First Name", "Last Name", "Birthdate", "Preferred Position", "Skill Level"},
        0);
    table = new JTable(tableModel);


    // Create the buttons
    addButton = new JButton("Add Player");
    deleteButton = new JButton("Delete Player");
    generateTeamButton = new JButton("Generate Team");
    generateLineupButton = new JButton("Generate Starting Lineup");
    JPanel buttonPanel = new JPanel();

    // Add the components to the frame
    buttonPanel.add(addButton);
    buttonPanel.add(deleteButton);
    buttonPanel.add(generateTeamButton);
    buttonPanel.add(generateLineupButton);

    // Set the layout
    JScrollPane scrollPane = new JScrollPane(table);
    frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
    frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
  }

  /**
   * Sets the listener for the Add Player button.
   *
   * @param listener the listener to set
   */
  public void setAddPlayerListener(ActionListener listener) {
    addButton.addActionListener(listener);
  }

  /**
   * Sets the listener for the Delete Player button.
   *
   * @param listener the listener to set
   */
  public void setDeletePlayerListener(ActionListener listener) {
    deleteButton.addActionListener(listener);
  }

  /**
   * Sets the listener for the Generate Team button.
   *
   * @param listener the listener to set
   */
  public void setGenerateTeamListener(ActionListener listener) {
    generateTeamButton.addActionListener(listener);
  }

  /**
   * Sets the listener for the Generate Starting Lineup button.
   *
   * @param listener the listener to set
   */
  public void setGenerateLineupListener(ActionListener listener) {
    generateLineupButton.addActionListener(listener);
  }


  /**
   * adds a player to the table.
   *
   * @param firstName  the first name of the player
   * @param lastName   the last name of the player
   * @param birthdate  the birthdate of the player
   * @param position   the position of the player
   * @param skillLevel the skill level of the player
   */
  public void addPlayerToTable(String firstName, String lastName, String birthdate, String position,
                               int skillLevel) {
    tableModel.addRow(new Object[] {firstName, lastName, birthdate, position, skillLevel});
  }

  /**
   * Gets the index of the selected player in the table.
   *
   * @return the index of the selected player
   */
  public int getSelectedPlayerIndex() {
    return table.getSelectedRow();
  }

  /**
   * Removes the player at the specified row index from the table.
   *
   * @param rowIndex the index of the row to remove
   */
  public void removePlayerFromTable(int rowIndex) {
    tableModel.removeRow(rowIndex);
  }

  /**
   * Shows an error message dialog.
   *
   * @param message the error message to show
   */
  public void showError(String message) {
    JOptionPane.showMessageDialog(frame, message, "Error", JOptionPane.ERROR_MESSAGE);
  }

  /**
   * Shows the team in a dialog.
   *
   * @param team the team to show
   */
  public void showTeam(List<Player> team) {
    String[] columnNames =
          {"First Name", "Last Name", "Birthdate", "Preferred Position",
              "Skill Level", "Jersey Number"};
    DefaultTableModel model = new DefaultTableModel(columnNames, 0);
    team.sort(Comparator.comparing(Player::getLastName));  // Sort by last name
    for (Player player : team) {
      model.addRow(new Object[] {
          player.getFirstName(),
          player.getLastName(),
          player.getBirthdate().format(DateTimeFormatter.ISO_DATE),
          player.getPreferredPosition(),
          player.getSkillLevel(),
          player.getJerseyNumber()
      });
    }
    JTable table = new JTable(model);
    JOptionPane.showMessageDialog(frame, new JScrollPane(table), "Team",
        JOptionPane.INFORMATION_MESSAGE);
  }

  /**
   * Shows the starting lineup in a dialog.
   *
   * @param lineup the starting lineup to show
   */
  public void showStartingLineup(List<Player> lineup) {
    String[] columnNames =
          {"First Name", "Last Name", "Birthdate", "Assigned Position",
              "Skill Level", "Jersey Number"};
    DefaultTableModel model = new DefaultTableModel(columnNames, 0);
    lineup.sort(
        Comparator.comparing(Player::getAssignedPosition).thenComparing(Player::getLastName));
    for (Player player : lineup) {
      model.addRow(new Object[] {
          player.getFirstName(),
          player.getLastName(),
          player.getBirthdate().format(DateTimeFormatter.ISO_DATE),
          player.getAssignedPosition().toString(),
          player.getSkillLevel(),
          player.getJerseyNumber()
      });
    }
    JTable table = new JTable(model);
    JOptionPane.showMessageDialog(frame, new JScrollPane(table), "Starting Lineup",
        JOptionPane.INFORMATION_MESSAGE);
  }

  /**
   * Sets the visibility of the view.
   *
   * @param visible true to show the view, false to hide it
   */
  public void setVisible(boolean visible) {
    frame.setVisible(visible);
  }
}
