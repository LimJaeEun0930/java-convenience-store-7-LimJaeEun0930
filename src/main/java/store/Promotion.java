package store;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Promotion {
    public static final ArrayList<Promotion> promotions = new ArrayList<>();
    private String name;
    private int buy;
    private int get;
    private LocalDateTime startDate;
    private  LocalDateTime endDate;

    public Promotion(String name, int buy, int get, LocalDateTime startDate, LocalDateTime endDate) {
        this.name = name;
        this.buy = buy;
        this.get = get;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(name)
                .append(',')
                .append(buy)
                .append(',')
                .append(get)
                .append(startDate.format(DateTimeFormatter.ISO_DATE))
                .append(endDate.format(DateTimeFormatter.ISO_DATE));
        return sb.toString();
    }

    public static void readPromtion(String line) {
        String[] parts = line.split(",");
        String name = parts[0];
        int buy = Integer.parseInt(parts[1]);
        int get = Integer.parseInt(parts[2]);
        LocalDateTime startDate = LocalDateTime.parse(parts[3].trim() + "T00:00:00");
        LocalDateTime endDate = LocalDateTime.parse(parts[4].trim() + "T00:00:00");
        promotions.add(new Promotion(name, buy, get, startDate, endDate));
    }

}
