package quizz.examples.ureact.me.ureactmequizz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz);
    }
}