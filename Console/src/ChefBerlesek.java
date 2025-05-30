import java.io.*;
import java.nio.file.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.*;

class Berles {
    int uid;
    int chefid;
    LocalDate startDate;
    LocalDate endDate;
    int dailyRate;
    String name;
    String cuisine;

    public Berles(int uid, int chefid, LocalDate startDate, LocalDate endDate, int dailyRate, String name, String cuisine) {
        this.uid = uid;
        this.chefid = chefid;
        this.startDate = startDate;
        this.endDate = endDate;
        this.dailyRate = dailyRate;
        this.name = name;
        this.cuisine = cuisine;
    }

    public long getNapokSzama() {
        return ChronoUnit.DAYS.between(startDate, endDate) + 1;
    }

    public int getTotalPrice() {
        return (int) getNapokSzama() * dailyRate;
    }
}

public class ChefBerlesek {
    public static void main(String[] args) {
        List<Berles> berlesek = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try (BufferedReader br = Files.newBufferedReader(Paths.get("chef_berlesek_2024.csv"))) {
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] v = line.split(",");
                berlesek.add(new Berles(
                        Integer.parseInt(v[0]),
                        Integer.parseInt(v[1]),
                        LocalDate.parse(v[2], formatter),
                        LocalDate.parse(v[3], formatter),
                        Integer.parseInt(v[4]),
                        v[5],
                        v[6]
                ));
            }
        } catch (IOException e) {
            System.out.println("Hiba a fájl beolvasásakor: " + e.getMessage());
            return;
        }

        Scanner sc = new Scanner(System.in);
        System.out.print("Adjon meg egy hónapot (1-12): ");
        int month = sc.nextInt();

        int haviBevetel = berlesek.stream()
                .filter(b -> b.startDate.getMonthValue() <= month && b.endDate.getMonthValue() >= month)
                .mapToInt(Berles::getTotalPrice)
                .sum();
        System.out.println("1, A(z) " + month + ". hónap bevétele: " + haviBevetel + " euró");

        int evesBevetel = berlesek.stream().mapToInt(Berles::getTotalPrice).sum();
        System.out.println("2, A teljes 2024-es éves bevétel: " + evesBevetel + " euró");


        Berles maxBerles = Collections.max(berlesek, Comparator.comparingInt(Berles::getTotalPrice));
        System.out.println("3, A legdrágább bérlés " + maxBerles.name + " séftől volt, teljes ár: " + maxBerles.getTotalPrice() + " euró");


        long kulonbozoSefek = berlesek.stream().map(b -> b.chefid).distinct().count();
        System.out.println("4, Összesen " + kulonbozoSefek + " különböző séfet béreltek ki.");

    
        String legtobbszorBerelt = berlesek.stream()
                .collect(Collectors.groupingBy(b -> b.name, Collectors.counting()))
                .entrySet().stream().max(Map.Entry.comparingByValue())
                .map(e -> e.getKey() + " (" + e.getValue() + " bérlés)").orElse("Nincs adat");
        System.out.println("5, A legtöbbször bérelt séf: " + legtobbszorBerelt);


        System.out.println("6, Bérlések száma konyhatípusonként:");
        berlesek.stream()
                .collect(Collectors.groupingBy(b -> b.cuisine, TreeMap::new, Collectors.counting()))
                .forEach((cuisine, count) -> System.out.println(cuisine + ": " + count + " bérlés"));


        double atlagIdotartam = berlesek.stream().mapToLong(Berles::getNapokSzama).average().orElse(0.0);
        System.out.printf("7, Átlagos bérlési időtartam: %.2f nap%n", atlagIdotartam);
        sc.close();
    }
    
}
