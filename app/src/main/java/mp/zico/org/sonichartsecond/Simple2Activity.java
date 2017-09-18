package mp.zico.org.sonichartsecond;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Vibrator;
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

//public class Simple2Activity extends AppCompatActivity implements OnChartValueSelectedListener, SeekBar.OnSeekBarChangeListener {
    public class Simple2Activity extends AppCompatActivity implements OnChartValueSelectedListener {

    private LineChart mChart;
//    private SeekBar mSeekBar;
    private TextView tvX;
    SoundPool mySound;
    int raygunID;
    Entry e;
    MediaPlayer mp;
    Button btn, btn2;
    Vibrator vibrator;


    final private ArrayList<Entry> entries = new ArrayList<Entry>();

    private void playmp(float a) {
//        float volume = ((a / (mChart.getYChartMax() - mChart.getYChartMin())) * mSeekBar.getProgress());
        float volume = ((a / (mChart.getYChartMax() - mChart.getYChartMin())));
        mySound.play(raygunID, 1, 1, 1, 0, volume);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple1);

        //ini adalah load Soundpool
        mySound = new SoundPool(6, AudioManager.STREAM_NOTIFICATION, 0);
        raygunID = mySound.load(this, R.raw.p1, 1);
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);


        tvX = (TextView) findViewById(R.id.freqChart);

/*        mSeekBar = (SeekBar) findViewById(R.id.seekBar);
        mSeekBar.setMax(5);
        mSeekBar.setProgress(1);
        mSeekBar.setOnSeekBarChangeListener(this);*/

        mChart = (LineChart) findViewById(R.id.chart);
        mChart.setOnChartValueSelectedListener(this);

        entries.add(new Entry(2F, 0));
        entries.add(new Entry(4F, 1));
        entries.add(new Entry(6F, 2));
        entries.add(new Entry(4F, 3));

        //for (int i = 0; i < 6; ++i)
        //    entries.add(new Entry(0.1F + (float) Math.random() * 20.0F, i));

        LineDataSet dataset = new LineDataSet(entries, "# of Calls");

        ArrayList<String> labels = new ArrayList<String>();
        labels.add("1");
        labels.add("2");
        labels.add("3");
        labels.add("4");


        LineData data = new LineData(labels, dataset);
        dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
        dataset.setDrawCubic(true);
        dataset.setDrawFilled(true);

        mChart.setData(data);
        mChart.animateY(1000);
        mChart.setScaleEnabled(false);
        tvX.setText("2");

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
                startActivity(new Intent(Simple2Activity.this, Simple3Activity.class));
            }
        });
    }


/*    @Override
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

    }*/

    @Override
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
        playmp(e.getVal());
        vibrator.vibrate((long) e.getVal()*10);
    }

    @Override
    public void onNothingSelected() {
        //mySound.release();
        //Log.i("Nothing selected", "Nothing selected.");
    }
}