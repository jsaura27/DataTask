import org.apache.commons.math3.stat.regression.SimpleRegression;

import java.io.IOException;
import java.text.ParseException;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class DataProcessor {

    public static void dataCalculator(List<DataClass> list){

        //Simple method to get the minimun and maximum by sorting the list by the values of kWh
        list.sort(Comparator.comparing(DataClass::getkWh));
        int min = list.get(0).getkWh();
        Date date = list.get(0).getDate();
        System.out.println("Minimum consumption = " + min + ", On the date = " + date);
        list.sort(Comparator.comparing(DataClass::getkWh).reversed());
        int max = list.get(0).getkWh();
        date = list.get(0).getDate();
        System.out.println("Maximum consumption = " + max + ", On the date = " + date);

        //Average operation
        int avg = 0;
        for(int i = 0;i< list.size();i++){
            avg += list.get(i).getkWh();
        }
        avg = avg/list.size();
        System.out.println("Average consumption = " + avg);

    }

    //Method to calculate Linear regresion easily thanks to the Math apache library
    public static void linearRegresion(List<DataClass> list){
        // creating regression object, passing true to have intercept term
        SimpleRegression simpleRegression = new SimpleRegression(true);

        // passing data to the model based on the data in the file
        double[][] data = new double[list.size()][2];
        for(int i = 0; i<list.size();i++){
            data[i][0] = list.get(i).getHdd();
            data[i][1] = list.get(i).getkWh();
        }
        simpleRegression.addData(data);

        //Returning operations needed
        System.out.println("slope = " + simpleRegression.getSlope());

        System.out.println("intercept = " + simpleRegression.getIntercept());

        System.out.println("standard error = " + simpleRegression.getInterceptStdErr());

        System.out.println("R2 value = " + simpleRegression.getRSquare());


    }

    public static void main(String[] args) throws IOException, ParseException {

        List<DataClass> list = CSVReader.reader();
        dataCalculator(list);
        linearRegresion(list);

    }

}
