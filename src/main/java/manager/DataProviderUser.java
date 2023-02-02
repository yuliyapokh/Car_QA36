package manager;

import model.User;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderUser {

    public Iterator<Object[]> loginDataUser() {

        List<Object[]> list = new ArrayList<>();

        list.add(new Object[]{User.builder().email("pokh@i2.ua").password("Yyp12345!").build()});
        list.add(new Object[]{User.builder().email("pokh@i2.ua").password("Yyp12345!").build()});
        list.add(new Object[]{User.builder().email("pokh@i2.ua").password("Yyp12345!").build()});

        return list.iterator();

    }

    @DataProvider
    public Iterator<Object[]> loginDataFile() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("src/test/resources/dataLog.csv")));
        String  line =bufferedReader.readLine();
        while(line!=null){
            String[] split = line.split(",");
            list.add(new Object[]{User.builder().email(split[0]).password(split[1]).build()});
            line =bufferedReader.readLine();
        }

        return list.iterator();
    }


    public Iterator<Object[]> registrationDataUser() {

        List<Object[]> list = new ArrayList<>();

        list.add(new Object[]{User.builder().name("Juli").lastName("Pet").email("blabla@gmail.ru").password("Bla12335!").build()});
        list.add(new Object[]{User.builder().name("Ann").lastName("Get").email("bla@gmail.ru").password("Bla1233577$").build()});
        list.add(new Object[]{User.builder().name("Henri").lastName("Poh").email("love@gmail.ru").password("Love12335!").build()});


        return list.iterator();

    }



    @DataProvider
    public Iterator<Object[]> RegistrationDataFile() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("src/test/resources/dataReg.csv")));
        String  line =bufferedReader.readLine();
        while(line!=null){
            String[] split = line.split(",");
            list.add(new Object[]{User.builder().email(split[0]).password(split[1]).build()});
            line =bufferedReader.readLine();
        }

        return list.iterator();
    }
}