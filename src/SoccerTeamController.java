
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

/**
 * The controller class for the Soccer Team Management application.
 */
public class SoccerTeamController {
  private SoccerTeamModel model;
  private SoccerTeamView view;

  /**
   * Constructs a new SoccerTeamController object.
   *
   * @param model the model to use
   * @param view  the view to use
   */
  public SoccerTeamController(SoccerTeamModel model, SoccerTeamView view) {
    // Set the model and view
    this.model = model;
    this.view = view;

    // Set the listeners
    view.setAddPlayerListener(new AddPlayerListener());
    view.setDeletePlayerListener(new DeletePlayerListener());
    view.setGenerateTeamListener(new GenerateTeamListener());
    view.setGenerateLineupListener(new GenerateLineupListener());
  }

  /**
   * The listener for the Add Player button.
   */
  class AddPlayerListener implements ActionListener {
    /**
     * Shows a dialog to add a new player to the model and view.
     *
     * @param e the action event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
      // Create the birthdate spinner
      JSpinner birthdateSpinner = new JSpinner(new SpinnerDateModel());
      JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(birthdateSpinner, "yyyy-MM-dd");
      birthdateSpinner.setEditor(dateEditor);

      // Create the position and skill level combo boxes
      JComboBox<Position> positionComboBox = new JComboBox<>(Position.values());
      JComboBox<Integer> skillLevelComboBox = new JComboBox<>(new Integer[] {1, 2, 3, 4, 5});

      // Create the input fields
      JTextField firstNameField = new JTextField();
      JTextField lastNameField = new JTextField();

      // Create the panel
      JPanel panel = new JPanel(new GridLayout(0, 1));
      panel.add(new JLabel("First Name:"));
      panel.add(firstNameField);
      panel.add(new JLabel("Last Name:"));
      panel.add(lastNameField);
      panel.add(new JLabel("Birthdate:"));
      panel.add(birthdateSpinner);
      panel.add(new JLabel("Preferred Position:"));
      panel.add(positionComboBox);
      panel.add(new JLabel("Skill Level:"));
      panel.add(skillLevelComboBox);

      // Show the dialog
      int result =
          JOptionPane.showConfirmDialog(null, panel, "Add Player", JOptionPane.OK_CANCEL_OPTION,
              JOptionPane.PLAIN_MESSAGE);
      // If the user clicked OK, add the player to the model and view
      if (result == JOptionPane.OK_OPTION) {
        try {
          String firstName = firstNameField.getText();
          String lastName = lastNameField.getText();
          LocalDate birthdate =
              LocalDate.parse(dateEditor.getFormat().format(birthdateSpinner.getValue()),
                  DateTimeFormatter.ofPattern("yyyy-MM-dd"));
          Position position = (Position) positionComboBox.getSelectedItem();
          int skillLevel = (int) skillLevelComboBox.getSelectedItem();

          // Add the player to the model and view
          model.addPlayer(lastName, firstName, birthdate, position, skillLevel);
          view.addPlayerToTable(firstName, lastName, birthdate.format(DateTimeFormatter.ISO_DATE),
              position.name(), skillLevel);
        } catch (IllegalStateException ex) {
          view.showError("Invalid player details: " + ex.getMessage());
        }
      }
    }
  }

  /**
   * The listener for the Delete Player button.
   */
  class DeletePlayerListener implements ActionListener {
    /**
     * Removes the selected player from the model and view.
     *
     * @param e the action event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
      // Get the selected row
      int selectedRow = view.getSelectedPlayerIndex();
      // If a row is selected, remove the player from the model and view
      if (selectedRow >= 0) {
        String playerId = model.getPlayers().get(selectedRow).getId().toString();
        model.removePlayer(playerId);
        view.removePlayerFromTable(selectedRow);
      } else {
        view.showError("Please select a player to delete.");
      }
    }
  }

  /**
   * The listener for the Generate Team button.
   */
  class GenerateTeamListener implements ActionListener {
    /**
     * Generates a team and shows it in the view.
     *
     * @param e the action event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
      try {
        model.generateTeam();
        view.showTeam(new ArrayList<>(model.getTeam().getPlayers()));
      } catch (IllegalStateException ex) {
        view.showError(ex.getMessage());
      }
    }
  }

  /**
   * The listener for the Generate Lineup button.
   */
  class GenerateLineupListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      try {
        model.generateStartingLineup();
        List<Player> lineup = model.getTeam().getStartingLineup();
        view.showStartingLineup(new ArrayList<>(lineup));
      } catch (IllegalStateException ex) {
        view.showError(ex.getMessage());
      }
    }
  }
}
