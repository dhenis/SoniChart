package mp.zico.org.sonichart;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Medium4Activity extends AppCompatActivity implements OnChartValueSelectedListener, SeekBar.OnSeekBarChangeListener {

    private LineChart mChart;
    private SeekBar mSeekBar;
    private TextView tvX;
    SoundPool mySound;
    int raygunID;
    Entry e;
    MediaPlayer mp;
    Button btn, btn2;

    final private ArrayList<Entry> entries = new ArrayList<Entry>();

    private void playmp(float a) {
        float volume = ((a / (mChart.getYChartMax() - mChart.getYChartMin())) * mSeekBar.getProgress());
        mySound.play(raygunID, 1, 1, 1, 0, volume);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple1);

        //ini adalah load Soundpool
        mySound = new SoundPool(6, AudioManager.STREAM_NOTIFICATION, 0);
        raygunID = mySound.load(this, R.raw.p1, 1);

        tvX = (TextView) findViewById(R.id.freqChart);

        mSeekBar = (SeekBar) findViewById(R.id.seekBar);
        mSeekBar.setMax(5);
        mSeekBar.setProgress(1);
        mSeekBar.setOnSeekBarChangeListener(this);

        mChart = (LineChart) findViewById(R.id.chart);
        mChart.setOnChartValueSelectedListener(this);

        entries.add(new Entry(20F, 0));
        entries.add(new Entry(40F, 1));
        entries.add(new Entry(60F, 2));
        entries.add(new Entry(40F, 3));
        entries.add(new Entry(30F, 4));
        entries.add(new Entry(20F, 5));
        entries.add(new Entry(40F, 6));
        entries.add(new Entry(60F, 7));
        entries.add(new Entry(40F, 8));
        entries.add(new Entry(30F, 9));
        entries.add(new Entry(20F, 10));
        entries.add(new Entry(40F, 11));
        entries.add(new Entry(60F, 12));
        entries.add(new Entry(40F, 13));
        entries.add(new Entry(30F, 14));
        entries.add(new Entry(50F, 15));
        entries.add(new Entry(40F, 16));
        entries.add(new Entry(30F, 17));
        entries.add(new Entry(20F, 18));
        entries.add(new Entry(10F, 19));

        //for (int i = 0; i < 6; ++i)
        //    entries.add(new Entry(0.1F + (float) Math.random() * 20.0F, i));

        LineDataSet dataset = new LineDataSet(entries, "# of Calls");

        ArrayList<String> labels = new ArrayList<String>();
        labels.add("1");
        labels.add("2");
        labels.add("3");
        labels.add("4");
        labels.add("5");
        labels.add("6");
        labels.add("7");
        labels.add("8");
        labels.add("9");
        labels.add("10");
        labels.add("11");
        labels.add("12");
        labels.add("13");
        labels.add("14");
        labels.add("15");
        labels.add("16");
        labels.add("17");
        labels.add("18");
        labels.add("19");
        labels.add("20");

        LineData data = new LineData(labels, dataset);
        dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
        dataset.setDrawCubic(true);
        dataset.setDrawFilled(true);

        mChart.setData(data);
        mChart.animateY(1000);

        btn = (Button) findViewById(R.id.button1);
        mp = MediaPlayer.create(this, R.raw.p1);

        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                final Timer timer = new Timer();

                // Body Of Timer
                TimerTask time = new TimerTask() {

                    private int v = 0;

                    @Override
                    public void run() {

                        //Perform background work here
                        if (!mp.isPlaying()) {

                            playmp(entries.get(v++).getVal());


                            if (v >= entries.size())
                                timer.cancel();
                        }


                    }
                };
                //Starting Timer
                timer.scheduleAtFixedRate(time, 0, 500);


            }
        });

        btn2 = (Button) findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Medium4Activity.this, Medium5Activity.class));
            }
        });
    }


    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        tvX.setText("" + (mSeekBar.getProgress()));
        float volume = ((mChart.getYChartMax() / (mChart.getYChartMax() - mChart.getYChartMin())) * 5);
        //float volume= ((mChart.getY/(mChart.getYChartMax()-mChart.getYChartMin()))*5);
        mySound.play(raygunID, 1, 1, 1, 0, (volume * mSeekBar.getProgress()));

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
        playmp(e.getVal());
    }

    @Override
    public void onNothingSelected() {
        //mySound.release();
        //Log.i("Nothing selected", "Nothing selected.");
    }
}