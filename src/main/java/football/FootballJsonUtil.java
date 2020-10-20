package football;

import com.fasterxml.jackson.databind.ObjectMapper;
import football.TeamData;
import football.FootballJson;
import request.Request;

import java.io.IOException;

public class FootballJsonUtil {

    private static int countGoalsFromJsonData(String uri, int year, String t, String team) throws IOException {
        int totalGoals = 0;
        String teamEncoded = team.replace("\\s", "%20");
        String query = "?year="+year+"&"+t+"="+teamEncoded+"&page="+1;
        String response = Request.makeRequest(uri, query);

        ObjectMapper mapper = new ObjectMapper();
        try {
            FootballJson page1 = mapper.readValue(response, FootballJson.class);
            totalGoals += countTeamGoals(year, team, page1);

            int totalPages = page1.getTotal_pages();

            for(int i=2;i<=totalPages;i++){
                query = "?year="+year+"&"+t+"="+teamEncoded+"&page="+i;
                response = Request.makeRequest(uri, query);
                FootballJson pageData = mapper.readValue(response, FootballJson.class);
                totalGoals += countTeamGoals(year, team, pageData);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return totalGoals;
    }

    private static int countTeamGoals(int year, String team, FootballJson pageData){
        TeamData[] teamData = pageData.getData();
        int totalGoals = 0;
        for(TeamData t: teamData) {
            if(Integer.parseInt(t.getYear())==year && t.getTeam1().equals(team)) {
                totalGoals += Integer.parseInt(t.getTeam1goals());
            } else if(Integer.parseInt(t.getYear())==year && t.getTeam2().equals(team)) {
                totalGoals += Integer.parseInt(t.getTeam2goals());
            }
        }
        return totalGoals;
    }


}
