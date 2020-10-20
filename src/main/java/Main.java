import parseUtility.ParseJson;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        String team = in.next();
        int year = in.nextInt();

        int totalGoals = ParseJson.getTotalGoals(team, year);
        System.out.println(totalGoals);
        int totalDraw = ParseJson.getTotalDrawCount(year);
        System.out.println(totalDraw);
    }
}