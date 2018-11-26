public class Team2 {
	
	private String teamName;
	private String coach;
	private Player captain;
	
	public String getTeamName() {
		return this.teamName;
	}
	
	public String getCoach() {
		return this.coach;
	}
	
	public void setTeamName(String TeamName) {
		this.teamName=TeamName;
	}
	
	public void setCoach(String Coach) {
		this.coach=Coach;
	}
	
	public Player getCaptain() {
		return this.captain;
	}
	
	public void setCaptain(Player Captain) {
		this.captain=Captain;
	}
	
}