/**
 * @author Jaydon Reap
 * @version 1.0
 *
 * The main class responsible for running GaussianNaiveBayes heuristics
 */
import java.io.*;
import java.lang.Math;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String args[]) {
        // Ordered as X is at i and its counterpart, Y, is at i + 1
        ArrayList<ArrayList<Double>> trainingdata = ReadFileData();
        ArrayList<ArrayList<Double>> averages = InitListGroups(trainingdata);
        ArrayList<ArrayList<Double>> variance = InitListGroups(trainingdata);
        System.out.println("=Training Data=");
        System.out.println(trainingdata);

        // Calculate X values then Y values and add them to the list
        double[] temp;
        for(int x = 0; x < trainingdata.size(); x++) {
            temp = Average(trainingdata.get(x));

            averages.get(x).add(temp[0]);
            averages.get(x).add(temp[1]);
        }
        System.out.println("=Averages=");
        System.out.println(averages);
    }

    private static double[] Variance(ArrayList<Double> group, ArrayList<Double> gaverages) {

    }

    private static ArrayList<ArrayList<Double>> InitListGroups(ArrayList<ArrayList<Double>> list) {
        int groupcount = list.size();

        ArrayList<ArrayList<Double>> temp = new ArrayList<>();

        for (int i = 0; i < groupcount; i++) {
            temp.add(new ArrayList<Double>());
        }

        return temp;
    }

    private static ArrayList<ArrayList<Double>> ReadFileData() {
        ArrayList<ArrayList<Double>> list = new ArrayList<ArrayList<Double>>();

        try {
            File file = new File("/home/jrreap/Projects/GaussianNaiveBayes/src/data.txt");
            Scanner sc = new Scanner(file);

            while (sc.hasNext()) {
                int val = sc.nextInt();

                // If we haven't created a list for this class yet, create one
                if(list.size() < val + 1) {
                    list.add(new ArrayList<Double>());
                }

                //Read out the next two vals
                double x = sc.nextDouble();
                double y = sc.nextDouble();

                //Add the vals to the designated area in the list
                list.get(val).add(x);
                list.get(val).add(y);
            }


            return list;
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    private static double[] Average(ArrayList<Double> group) {
        //Average the Xs
        double total = 0;
        int count = 0;
        for(int i = 0; i < group.size(); i = i + 2) {
            total = total + group.get(i);
            count++;
        }

        double avgx = total/count;

        //Average the Ys
        count = 0;
        total = 0;
        for(int i = 1; i < group.size(); i = i + 2) {
            total = total + group.get(i);
            count++;
        }

        double avgy = total/count;

        double[] a = new double[2];
        a[0] = avgx;
        a[1] = avgy;

        return a;

    }
}
