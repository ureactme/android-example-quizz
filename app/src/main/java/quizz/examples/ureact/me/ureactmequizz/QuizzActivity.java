package quizz.examples.ureact.me.ureactmequizz;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import me.ureact.tracker.Event;
import me.ureact.tracker.UReactMe;
import me.ureact.tracker.UTracker;
import me.ureact.tracker.exceptions.EmptyTokenException;

/**
 * Created by pappacena on 31/12/15.
 */
public class QuizzActivity extends AppCompatActivity {
    private static String questions[] = {
            "Which one of these cities is the capital of Brazil?",
            "Which one of these countries has the bigger population?",
            "In which italian region is located the city of Milan?",
            "In which year USA became independent?"
    };

    private static String options[][] = {
            {"Brasilia", "Rio de Janeiro", "São Paulo", "Curitiba"},
            {"India", "USA", "Russia", "Italy"},
            {"Lombardia", "Piemonte", "Toscana", "Calabria"},
            {"1776", "1888", "1500", "1788"}
    };

    private UTracker tracker;
    private int currentQuestion;
    private int totalRight = 0;
    private int totalWrong = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz);

        try {
            // Get  the tracker
            this.tracker = UReactMe.getInstance(this).getTracker();

            // Send "game_started" event
            this.tracker.send(new Event().setMetric("game_started"));
        } catch (EmptyTokenException e) {
            Log.e("quizz", "Error getting token");
        }

        this.currentQuestion = 0;
        this.showQuestion(this.currentQuestion);
    }

    public void showQuestion(int questionId) {
        Button[] buttons = {
                (Button) findViewById(R.id.option1),
                (Button) findViewById(R.id.option2),
                (Button) findViewById(R.id.option3),
                (Button) findViewById(R.id.option4),
        };
        TextView question = (TextView) findViewById(R.id.question);
        question.setText(QuizzActivity.questions[questionId]);

        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setText(QuizzActivity.options[questionId][i]);
        }
    }

    public void onSelectedOption(View view) {
        Button button = (Button) view;
        final TextView feedback = (TextView) findViewById(R.id.feedback);

        // Check the correct answer and track it on ureact.me
        String rightAnswer = QuizzActivity.options[this.currentQuestion][0];
        if (button.getText().equals(rightAnswer)) {
            feedback.setText("Correct answer!");
            this.tracker.send(new Event()
                    .setMetric("right_answer"));
            this.totalRight++;
        } else {
            feedback.setText("Wrong answer!");
            this.tracker.send(new Event()
                    .setMetric("wrong_answer"));
            this.totalWrong++;
        }
        feedback.setVisibility(View.VISIBLE);

        // wait 1 second and show next question
        // if there is no new question, show final activity
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                currentQuestion++;
                if (currentQuestion >= QuizzActivity.questions.length) {
                    Intent intent = new Intent(getApplicationContext(), FinalActivity.class);
                    intent.putExtra("total_right", totalRight);
                    intent.putExtra("total_wrong", totalWrong);
                    startActivity(intent);
                    finish();
                } else {
                    feedback.setVisibility(View.GONE);
                    showQuestion(currentQuestion);
                }
            }
        }, 1000);
    }
}