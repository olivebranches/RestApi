package parseUtility;

import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import request.Request;

public class ParseJson {
    private static String URL = "https://jsonmock.hackerrank.com/api/football_matches";

    public static int getTotalDrawCount(int year){
        int totalDraws = 0;
        totalDraws += getDrawCount(URL, year);
        return totalDraws;
    }

    private static int getDrawCount(String uri, int year){
        int totalDraws = 0;
        try {
            for(int i=1;i<=10;i++){
                String query = "?year="+year+"&team1goals="+i+"&page="+1;
                String resp = Request.makeRequest(uri, query);
                JSONParser parser = new JSONParser();
                JSONObject obj = (JSONObject)parser.parse(resp);
                if(resp.equals("")){
                    continue;
                }
                long total = (long)obj.get("total");
                totalDraws += total;
            }
        }
        catch (ParseException | IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return totalDraws;
    }

    public static int getTotalGoals(String team, int year) throws IOException {
        int totalGoals = 0;
        String t = "team1";
        totalGoals += countGoalsFromJsonObject(URL, year,t, team);
        t = "team2";
        totalGoals += countGoalsFromJsonObject(URL, year,t, team);
        return totalGoals;
    }

    private static int countGoalsFromJsonObject(String uri, int year, String t, String team) throws IOException {
        int totalGoals = 0;
        String teamEncoded = team.replace("\\s", "%20");
        String query = "?year="+year+"&"+t+"="+teamEncoded+"&page="+1;
        String response = Request.makeRequest(uri, query);

        System.out.println(response);
        if(response.equals("")){
            return totalGoals;
        }
        try {

            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject)parser.parse(response);
            long totalPages = (long)obj.get("total_pages");
            JSONArray data = (JSONArray) obj.get("data");
            totalGoals += countTeamGoals(year, t, team, data);

            for(int i=2;i<=totalPages;i++){
                query = "?year="+year+"&"+t+"="+teamEncoded+"&page="+i;
                String resp = Request.makeRequest(URL,query);
                if(resp.equals("")){
                    continue;
                }
                obj = (JSONObject)parser.parse(resp);
                data = (JSONArray) obj.get("data");
                totalGoals += countTeamGoals(year, t, team, data);
            }
        }
        catch (ParseException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return totalGoals;
    }

    private static int countTeamGoals(int year, String t, String team, JSONArray data){
        int totalGoals = 0;
        for(Object d: data){
            JSONObject jboj = (JSONObject)d;
            long tyear = (long)jboj.get("year");
            String team1goals = (String)jboj.get("team1goals");
            String team2goals = (String)jboj.get("team2goals");
            String team1 = (String)jboj.get("team1");
            String team2 = (String)jboj.get("team2");

            if(tyear==year && t.equals("team1") && team1.equals(team)){
                totalGoals += Integer.parseInt(team1goals);
            } else if(tyear==year && t.equals("team2") && team2.equals(team)){
                totalGoals += Integer.parseInt(team2goals);
            }
        }
        return totalGoals;
    }
}

