

/**
 * The main class for the Soccer Team Management application.
 */
public class SoccerTeamApp {
  /**
   * The main method for the Soccer Team Management application.
   *
   * @param args the command-line arguments
   */
  public static void main(String[] args) {
    // Create the model, view, and controller
    SoccerTeamModel model = new SoccerTeamModelImpl();
    SoccerTeamView view = new SoccerTeamView();
    SoccerTeamController controller = new SoccerTeamController(model, view);

    // Set the view visible
    view.setVisible(true);
  }
}
