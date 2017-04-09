import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Created by simonyan_51 on 09.04.2017.
 */
public class Client {

    private ArrayList<Country> countries;

    public Client() {
        countries = new ArrayList<>();
    }

    private void sort(ArrayList<Country> countries, int sortIndex) {
        Collections.sort(countries, new Comparator<Country>() {

            @Override
            public int compare(Country o1, Country o2) {
                if (sortIndex == 1) {
                    return o1.getCountryCode().compareTo(o2.getCountryCode());
                } else if (sortIndex == 2) {
                    return o1.getEnglishName().compareTo(o2.getEnglishName());
                } else if (sortIndex == 3) {
                    return o1.getFranchName().compareTo((o2.getFranchName()));
                }
                return 0;
            }
        });
    }

    private void readFile(String src) throws IOException {
        FileReader file = new FileReader(src);
        BufferedReader read = new BufferedReader(file);
        String line;
        int id = 0;
        while((line = read.readLine()) != null) {
            String[] fileAttr = line.replace("\"", "").split(",");
            Country country = new Country(++id, fileAttr[0].trim(), fileAttr[1].trim(), fileAttr[2].trim());
            countries.add(country);
        }
    }

    public void start() throws IOException {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter File Source: ");

        readFile(input.nextLine());

        System.out.println("Enter How You Want To Sort: 1-By Country Code, 2-By English Name, 3-By Franch Name");

        sort(countries, input.nextInt());

        for(Country country : countries) {
            StringBuilder str = new StringBuilder();
            str.append(country.getId()).append(", ").append(country.getCountryCode()).append(", ")
                    .append(country.getEnglishName()).append(", ").append(country.getFranchName());
            System.out.println(str);
        }
    }
}
