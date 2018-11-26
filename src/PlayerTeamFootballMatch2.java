import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ListIterator;
public class PlayerTeamFootballMatch2 {
	public static void main(String[] args) {
		// We create an empty ArrayList object of ArrayList type (that will be of FootballMatch type)
		ArrayList<ArrayList<FootballMatch>> LeagueArrayList = new ArrayList<ArrayList <FootballMatch>>();
		// We control that the user enters an argument at least
		if (args.length!=0) {
			ArrayList<Team2> teams = new ArrayList<Team2>();
			File teamsFile = new File("C:\\Users\\ik013043z1\\eclipse-workspace\\FootballMatch3\\src\\Teams.txt");
			Scanner teamsScanner;
			try {
				teamsScanner = new Scanner(teamsFile);
				while (teamsScanner.hasNext()) {
					String team=teamsScanner.nextLine();
					String[] teamInformation = team.split("::");
					Team2 thisTeam=new Team2();
					thisTeam.setTeamName(teamInformation[0]);
					thisTeam.setCoach(teamInformation[1]);
					File playersFile = new File ("C:\\Users\\ik013043z1\\eclipse-workspace\\FootballMatch3\\src\\Players.txt");
					Scanner playersScanner=new Scanner(playersFile);
					while (playersScanner.hasNext()) {
						String player=playersScanner.nextLine();
						String[] playerInformation = player.split("::");
						if (playerInformation[2].equals(teamInformation[0])) {
							Player captain =new Player();
							captain.setPlayerName(playerInformation[0]);
							captain.setAge(Integer.parseInt(playerInformation[1]));
							captain.setTeamName(playerInformation[2]);
							thisTeam.setCaptain(captain);
							break;
						}
					}
					playersScanner.close();
					teams.add(thisTeam);
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// We create an empty array of the type File with the length of the number of arguments the user entered
			File[] FileArray = new File[args.length];
			// We create an empty array of the type Scanner with the length of the number of arguments
			Scanner[] ScannerArray = new Scanner[args.length];
			// We open a loop for each argument
			for (int i=0;i<args.length;i++) {
				// We create a file object with a previously created text file
				FileArray[i]=new File("C:\\Users\\ik013043z1\\eclipse-workspace\\FootballMatch\\src\\" + args[i] + "Matches.txt");
				try {
					// We create a scanner object with the previously created file object
					ScannerArray[i] = new Scanner(FileArray[i]);
					// We create an empty ArrayList object of FootballMatch type
					ArrayList<FootballMatch> partidoFutbol = new ArrayList<FootballMatch>();
					// We create a loop where each line of the text file will be a match
					while (ScannerArray[i].hasNext()) {
						// We create a string object with the next line
						String match = ScannerArray[i].nextLine();
						// We create an array with the values of each part of the line, separated by "::"
						String[] information = match.split("::");
						// We convert the string values that represent goals to integer
						int GoalsLocal = Integer.parseInt(information[2]);
						int GoalsVisitor = Integer.parseInt(information[3]);
						Team LocalTeam = new Team();
						LocalTeam.setTeamName(information[0]);
						Team VisitorTeam = new Team();
						VisitorTeam.setTeamName(information[1]);
						// We create an empty FootballMatch object
						FootballMatch thisFootballMatch=new FootballMatch();
						// We add the information of the match to the FootballMatch object with the previously created methods
						thisFootballMatch.setLocalTeam(LocalTeam);
						thisFootballMatch.setVisitorTeam(VisitorTeam);
						thisFootballMatch.setGoalsLocal(GoalsLocal);
						thisFootballMatch.setGoalsVisitor(GoalsVisitor);
						// We add the FootballMatch object to the previously created ArrayList of FootballMatch type
						partidoFutbol.add(thisFootballMatch);
					}
					// We add the ArrayList object to the previously created ArrayList of ArrayList type;
					LeagueArrayList.add(partidoFutbol);
					ScannerArray[i].close();
				}
				catch (FileNotFoundException e) {
					e.printStackTrace();
					System.out.println("The file of the competition " + args[i] + " was not found.");
				}
			}
			// We create a loop for each ArrayList of the ArrayList
			for (int i=0;i<LeagueArrayList.size();i++) {
					// We create a iterator with the ArrayList of FootballMatch type
					ListIterator<FootballMatch> it = LeagueArrayList.get(i).listIterator();
					// We create a counter that will hold how many draws were in the matches
					int drawCounter=0;
					// We create a loop where each turn the iterator will hold the values of each match
					while (it.hasNext()) {
						// If there wasn't a draw in the match, we will print the information of the match
						if (it.next().getGoalsLocal()!=it.previous().getGoalsVisitor()) {
							Team localTeam=it.next().getLocalTeam();
							for (int j=0;j<teams.size();j++) {
								if (teams.get(j).getTeamName().equals(localTeam.getTeamName())) {
									System.out.print(teams.get(j).getTeamName() + "  " + teams.get(j).getCoach() + "  " + teams.get(j).getCaptain().getPlayerName() + "  " + teams.get(j).getCaptain().getAge() + "  ");
									break;
								}
							}
							Team visitorTeam=it.previous().getVisitorTeam();
							for (int j=0;j<teams.size();j++) {
								if (teams.get(j).getTeamName().equals(visitorTeam.getTeamName())) {
									System.out.print(teams.get(j).getTeamName() + "  " + teams.get(j).getCoach() + "  " + teams.get(j).getCaptain().getPlayerName() + "  " + teams.get(j).getCaptain().getAge() + "  ");
									break;
								}
							}
							System.out.print(it.next().getGoalsLocal() +  "  " + it.previous().getGoalsVisitor());
							System.out.println();
							// We go to the next match
							it.next();
						}
						// Otherwise we will increment the draw counter and remove the match from the ArrayList
						else {
							drawCounter++;
							it.remove();
						}
					}
					// We print how many matches have been printed and how many draws were
					System.out.println(LeagueArrayList.get(i).size() + " matches have been displayed on the screen because " + drawCounter + " draws were omitted");
					System.out.println();
			}
		// If the user has not entered any argument we ask him for it
		}
		else {
			System.out.println("Please try again, you didn't enter any competition");
		}
	}

}