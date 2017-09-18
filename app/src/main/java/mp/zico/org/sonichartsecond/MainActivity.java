package mp.zico.org.sonichartsecond;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.github.mikephil.charting.utils.Utils;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize the utilities
        Utils.init(this);
        ArrayList<ContentItem> objects = new ArrayList<ContentItem>();

        objects.add(new ContentItem("Simple Chart", "3 simple Audio Chart ."));
        objects.add(new ContentItem("Medium Chart",
                "3 medium Audio Chart."));
        objects.add(new ContentItem("Complex Chart", "3 complex Audio Chart."));

        MyAdapter adapter = new MyAdapter(this, objects);

        ListView lv = (ListView) findViewById(R.id.listView1);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> av, View v, int pos, long arg3) {

        Intent i;
        System.out.println(pos);
        switch (pos) {
            case 0:
                i = new Intent(this, Simple1Activity.class);
                startActivity(i);
                break;
            case 1:
                i = new Intent(this, Medium1Activity.class);
                startActivity(i);
                break;
            case 2:
                i = new Intent(this, Complex1Activity.class);
                startActivity(i);
                break;

        }

        overridePendingTransition(R.anim.move_right_in_activity, R.anim.move_left_out_activity);
    }
}



