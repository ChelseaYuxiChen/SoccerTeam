# Soccer Team Management

## Overview
The `SoccerTeam` application is designed to manage a soccer team efficiently. It allows users to add and remove players, generate a team with assigned jersey numbers, and create a starting lineup based on players' skills and preferences. This application simplifies the process of team management, making it easier for coaches and managers to handle their teams effectively.

## List of Features
- **Add Player**: Add a player to the team with details including first name, last name, birthdate, preferred position, and skill level.
- **Remove Player**: Remove a player from the team by selecting the player from the table.
- **Generate Team**: Automatically generate a team with assigned jersey numbers. The list of players is sorted in alphabetical order by last name.
- **Generate Starting Lineup**: Generate a starting lineup with assigned positions based on player skills and preferences.

## How To Run the Program
- Open a terminal or command prompt.
- Navigate to the directory where the .jar file is located.
- Run the following command:
`java -jar SoccerTeam.jar`
- No additional arguments are needed to run the jar file.

## How to Use the Program
### Add Player
- Click the "Add Player" button.
- Enter the player's first name, last name, birthdate, preferred position, and skill level in the provided form.
- Click "Submit" to add the player to the team.

### Remove Player
- Select a player from the table.
- Click the "Delete Player" button to remove the selected player from the team.

### Generate Team
- Click the "Generate Team" button.
- The team will be generated, and players will be assigned jersey numbers. The list will be displayed in alphabetical order by last name.

### Generate Starting Lineup
- Click the "Generate Starting Lineup" button.
- The starting lineup will be generated based on player skills and preferences. The lineup will be displayed in a new window.


## Assumptions
- I am assuming that newborn can also join the team.

## Limitations
- The application does not support saving and loading data. All data is lost when the application is closed.
- The application does not support editing player details. To update player information, the user must remove the player and add them again with the new details.
- The graphical interface is basic and may need enhancements for better user experience.

## Citations
- No external resources or references were used for this project. 
