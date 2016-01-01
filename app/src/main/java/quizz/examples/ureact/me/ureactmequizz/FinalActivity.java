package quizz.examples.ureact.me.ureactmequizz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import me.ureact.tracker.Event;
import me.ureact.tracker.UReactMe;
import me.ureact.tracker.UTracker;
import me.ureact.tracker.exceptions.EmptyTokenException;

/**
 * Created by pappacena on 01/01/16.
 */
public class FinalActivity extends AppCompatActivity {
    private UTracker tracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        Intent intent = getIntent();
        int totalRight = intent.getIntExtra("total_right", 0);
        int totalWrong = intent.getIntExtra("total_wrong", 0);

        TextView rightView = (TextView) findViewById(R.id.total_right);
        TextView wrongView = (TextView) findViewById(R.id.total_wrong);

        rightView.setText("" + totalRight);
        wrongView.setText("" + totalWrong);

        // Track the "game end" event,
        // setting the value of the event as the total number of correct answers
        try {
            this.tracker = UReactMe.getInstance(this).getTracker();
            this.tracker.send(new Event()
                    .setMetric("game_end")
                    .setValue(totalRight));
        } catch (EmptyTokenException e) {
            Log.e("quizz", "Error getting token");
        }
    }
}
