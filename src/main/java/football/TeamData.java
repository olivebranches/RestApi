package football;

import java.util.Objects;

public class TeamData {
    private String competition;
    private String year;
    private String round;
    private String team1;
    private String team2;
    private String team1goals;
    private String team2goals;

    @Override
    public String toString() {
        return "combined.football.Data{" +
                "competition='" + competition + '\'' +
                ", year='" + year + '\'' +
                ", round='" + round + '\'' +
                ", team1='" + team1 + '\'' +
                ", team2='" + team2 + '\'' +
                ", team1goals='" + team1goals + '\'' +
                ", team2goals='" + team2goals + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeamData teamData = (TeamData) o;
        return Objects.equals(competition, teamData.competition) &&
                Objects.equals(year, teamData.year) &&
                Objects.equals(round, teamData.round) &&
                Objects.equals(team1, teamData.team1) &&
                Objects.equals(team2, teamData.team2) &&
                Objects.equals(team1goals, teamData.team1goals) &&
                Objects.equals(team2goals, teamData.team2goals);
    }

    @Override
    public int hashCode() {
        return Objects.hash(competition, year, round, team1, team2, team1goals, team2goals);
    }

    public String getYear() {
        return year;
    }

    public String getTeam1() {
        return team1;
    }

    public String getTeam2() {
        return team2;
    }

    public String getTeam1goals() {
        return team1goals;
    }

    public String getTeam2goals() {
        return team2goals;
    }

    TeamData(){
    }

    TeamData(String competition, String year, String round, String team1, String team2, String team1goals, String team2goals){
        this.competition = competition;
        this.year = year;
        this.round = round;
        this.team1 = team1;
        this.team2 = team2;
        this.team1goals = team1goals;
        this.team2goals = team2goals;
    }
}
