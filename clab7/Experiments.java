import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

import java.util.ArrayList;

/**
 * Created by hug.
 */
public class Experiments {
    public static void experiment1() {
        ArrayList<Integer> x = new ArrayList<>();
        ArrayList<Double> randomInsert = new ArrayList<>();
        ArrayList<Double> optimalInsert = new ArrayList<>();
        for (int i = 1; i < 5000; i++){
            x.add(i);
            randomInsert.add(experiment1Helper(i));
            optimalInsert.add(ExperimentHelper.optimalAverageDepth(i));
        }
        XYChart chart = new XYChartBuilder().width(800).height(600).xAxisTitle("x label").yAxisTitle("y label").build();
        chart.addSeries("random", x, randomInsert);
        chart.addSeries("optimal", x, optimalInsert);

        new SwingWrapper(chart).displayChart();

    }

    private static double experiment1Helper(int N) {
        BST<Double> temp = new BST<>();
        ArrayList<Double> recorder = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            double ran = Math.random();
            temp.add(ran);
            recorder.add(ran);
        }
        double averDepth = 0;
        for (Double i : recorder) {
            averDepth += temp.contains(i) - 1;
        }
        averDepth /= temp.size();
        return averDepth;

    }

    public static void experiment2() {
    }

    public static void experiment3() {
    }

    public static void main(String[] args) {
        experiment1();
    }
}
