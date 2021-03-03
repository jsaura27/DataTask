import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CSVReader {

    static private final String REFERENCE_FILE = "Resources/Data.csv";
    static private final String SEPARATOR = ",";

    static public List<DataClass> reader() throws IOException, ParseException {


        //CSV reader of the file with the given data
        BufferedReader csvReader = new BufferedReader(new FileReader(REFERENCE_FILE));
        String row;
        List<DataClass> listData = new ArrayList<>();

        while ((row = csvReader.readLine()) != null) {

            try {
                DataClass data = new DataClass();

                String[] datas = row.split(SEPARATOR);

                //Here I get rid of the case of a date with errors
                if(datas.length>3){
                    List<String> auxList = new ArrayList<>();
                    String aux = datas[0];
                    for(int i = 1; i< datas.length-2; i++){
                        aux += "/" + datas[i];
                    }

                    auxList.add(aux);
                    auxList.add(datas[datas.length-2]);
                    auxList.add(datas[datas.length-1]);

                    datas[0] = auxList.get(0);
                    datas[1] = auxList.get(1);
                    datas[2] = auxList.get(2);

                    data.addMessage("The date was wrongly inputed.");

                }

                String dateString = datas[0];

                Date date = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(dateString);

                //Here is the case if an element of HDD is empty
                if(datas[1].isEmpty() ||datas[1] == null){
                    int hdd = (int) ((Math.random() * (725 - 0)) + 0);
                    String message = "The HDD was empty we added a random number on the range of values this day.";

                    data.setDate(date);
                    data.setHdd(hdd);
                    data.addMessage(message);
                    data.setkWh(Integer.parseInt(datas[2]));
                }else{

                    data.setDate(date);
                    data.setHdd(Integer.parseInt(datas[1]));
                    data.setkWh(Integer.parseInt(datas[2]));
                }

                listData.add(data);


            } catch (Exception e) {

            }



        }
        csvReader.close();
        return listData;
    }

}
