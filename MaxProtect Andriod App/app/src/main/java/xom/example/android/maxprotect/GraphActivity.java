package xom.example.android.maxprotect;


import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;


public class GraphActivity extends MainActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from data_activity.xml
        setContentView(R.layout.graph_activity);


        Bundle bundle = getIntent().getExtras();
        double[] y = bundle.getDoubleArray("xy");
        double x=0;

        GraphView graph = (GraphView) findViewById(R.id.graph);

        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>();

        for (int i = 0; i < 10; i++) {

            series.appendData(new DataPoint(x, y[i]), true, 20);
            x++;
        }


        graph.addSeries(series);


    }


}
