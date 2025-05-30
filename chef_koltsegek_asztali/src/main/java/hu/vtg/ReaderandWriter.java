package hu.vtg;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class ReaderandWriter {
        public ArrayList<Model> readFile() throws IOException {
        ArrayList<Model> list = new ArrayList<>();
        File file = new File("File/chef_koltsegek_2024.csv");
        try(Scanner sc = new Scanner(file, StandardCharsets.UTF_8)) {
            sc.nextLine();
            while(sc.hasNext()){
                String line = sc.nextLine();
                String [] lineArray = line.split(";");
                Model model = new Model();
                model.setId(Integer.parseInt(lineArray[0]));
                model.setChefname(lineArray[1]);
                model.setDatum(LocalDate.parse(lineArray[2]));
                model.setType(lineArray[3]);
                model.setCurrency(Integer.parseInt(lineArray[4]));
                model.setComment(lineArray[5]);
                list.add(model);
            }
            sc.close();
        }
        catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
        return list;
    }
    public void FileWiriter(ArrayList<Model> modellist) {
            try (Writer writer = new OutputStreamWriter(
                new FileOutputStream("File/chef_koltsegek_2024.csv"),
                StandardCharsets.UTF_8)) {
            writer.write("id;chefname;datum;kategoria;osszeg;megjegyzes\n");
            for (Model model : modellist) {
                writer.write(
                    model.getId() + ";" +
                    model.getChefname() + ";" +
                    model.getDatum() + ";" +
                    model.getType() + ";" +
                    model.getCurrency() + ";" +
                    model.getComment() + "\n"
                );
            }
        } catch (IOException e) {
            System.err.println("Hiba a fájl írásakor: " + e.getMessage());
        }
    }
}
