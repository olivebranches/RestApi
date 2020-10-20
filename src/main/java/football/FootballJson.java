package football;

import java.util.Arrays;
import java.util.Objects;

public class FootballJson {
    private int page;
    private int per_page;
    private int total;
    private int total_pages;
    private TeamData[] data;

    @Override
    public String toString() {
        return "combined.football.JsonData{" +
                "page=" + page +
                ", per_page=" + per_page +
                ", total=" + total +
                ", total_pages=" + total_pages +
                ", data=" + Arrays.toString(data) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FootballJson footballJson = (FootballJson) o;
        return page == footballJson.page &&
                per_page == footballJson.per_page &&
                total == footballJson.total &&
                total_pages == footballJson.total_pages &&
                Arrays.equals(data, footballJson.data);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(page, per_page, total, total_pages);
        result = 31 * result + Arrays.hashCode(data);
        return result;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public TeamData[] getData() {
        return data;
    }

    FootballJson(){
    }

    FootballJson(int page, int perPage, int total, int totalPages, TeamData[] data){
        this.page = page;
        this.per_page = perPage;
        this.total = total;
        this.total_pages = totalPages;
        this.data = data;
    }
}
