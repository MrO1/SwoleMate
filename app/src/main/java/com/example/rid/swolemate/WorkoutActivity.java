package com.example.rid.swolemate;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

public class WorkoutActivity extends AppCompatActivity {


    private WorkoutTypeClass workout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        Bundle extras = getIntent().getExtras();
        System.out.println(extras.size());



        if(getIntent().getExtras().size() == 2){
            workout = new WorkoutTypeClass((String)extras.get("Workout1"),(String)extras.get("WorkoutType"));
            System.out.println(workout.getWorkoutType());
        }


        new CreateWorkout().execute(workout);
    }

    private static String readUrl(String urlString) throws Exception {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read);

            return buffer.toString();
        } finally {
            if (reader != null)
                reader.close();
        }
    }

    private class CreateWorkout extends AsyncTask<WorkoutTypeClass, Void, Void> {

        private ProgressDialog Dialog = new ProgressDialog(WorkoutActivity.this);

        @Override
        protected Void doInBackground(WorkoutTypeClass... params) {
            //Setup
            Random rand = new Random();

            ArrayList<Integer> chestMuscles = new ArrayList<Integer>();
            ArrayList<Integer> backMuscles = new ArrayList<Integer>();
            ArrayList<Integer> tricepMuscles = new ArrayList<Integer>();
            ArrayList<Integer> bicepMuscles = new ArrayList<Integer>();
            ArrayList<Integer> shoulderMuscles = new ArrayList<Integer>();
            ArrayList<Integer> legMuscles = new ArrayList<Integer>();
            ArrayList<Integer> abMuscles = new ArrayList<Integer>();


            ArrayList<JSONObject> chestExercises = new ArrayList<JSONObject>();
            ArrayList<JSONObject> backExercises = new ArrayList<JSONObject>();
            ArrayList<JSONObject> tricepExercises = new ArrayList<JSONObject>();
            ArrayList<JSONObject> bicepExercises = new ArrayList<JSONObject>();
            ArrayList<JSONObject> shoulderExercises = new ArrayList<JSONObject>();
            ArrayList<JSONObject> legExercises = new ArrayList<JSONObject>();
            ArrayList<JSONObject> abExercises = new ArrayList<JSONObject>();

            chestMuscles.add(4);
            shoulderMuscles.add(2);
            backMuscles.add(12);
            backMuscles.add(9);
            legMuscles.add(11);
            legMuscles.add(7);
            legMuscles.add(8);
            legMuscles.add(10);
            legMuscles.add(15);
            bicepMuscles.add(1);
            bicepMuscles.add(13);
            tricepMuscles.add(5);
            abMuscles.add(14);
            abMuscles.add(6);

            //Do not allow duplicate workouts
            ArrayList<Integer> usedInts = new ArrayList<Integer>();


            //Set up sets and reps count
            params[0].setSetsReps();
            switch(params[0].getMuscleGroup1()){
                case "chest":
                    try {
                        for (int i = 0; i < chestMuscles.size(); i++) {
                            JSONObject parser = new JSONObject(readUrl("http://wger.de/api/v2/exercise/?format=json&language=2&muscles=" + chestMuscles.get(i)));

                            JSONArray results = new JSONArray(parser.get("results").toString());
                            //System.out.println(results);
                            JSONObject currObject = new JSONObject();
                            for (int j = 0; j < results.length(); j++) {

                                currObject = (JSONObject) results.get(j);
                                JSONArray muscleList = (JSONArray) currObject.get("muscles");

                                if (muscleList.length() == 1) {
                                    chestExercises.add(currObject);
                                }

                            }

                        }
                        for (int i = 0; i < 4; i++) {
                            //System.out.println(results.get(i));
                            int n = rand.nextInt(chestExercises.size() - 1);
                            JSONObject obj = chestExercises.get(n);
                            //if()
                            //JSONArray muscleList = new JSONArray(obj.get("muscles").toString());
                            if (usedInts.contains(n)) {
                                i -= 1;
                            } else {
                                System.out.println(obj.get("name"));
                                usedInts.add(n);
                            }

                            //System.out.println(obj.get("id"));

                        }
                    }catch(Exception e){
                        System.out.println("EXCEPTION!: " + e);
                    }
                    break;


                case "back":
                    try {
                        for (int i = 0; i < backMuscles.size(); i++) {
                            JSONObject parser = new JSONObject(readUrl("http://wger.de/api/v2/exercise/?format=json&language=2&muscles=" + backMuscles.get(i)));

                            JSONArray results = new JSONArray(parser.get("results").toString());
                            //System.out.println(results);
                            JSONObject currObject = new JSONObject();
                            for (int j = 0; j < results.length(); j++) {

                                currObject = (JSONObject) results.get(j);
                                JSONArray muscleList = (JSONArray) currObject.get("muscles");

                                if (muscleList.length() == 1) {
                                    backExercises.add(currObject);
                                }

                            }

                        }
                        for (int i = 0; i < 4; i++) {
                            //System.out.println(results.get(i));
                            int n = rand.nextInt(backExercises.size() - 1);
                            JSONObject obj = backExercises.get(n);
                            //if()
                            //JSONArray muscleList = new JSONArray(obj.get("muscles").toString());
                            if (usedInts.contains(n)) {
                                i -= 1;
                            } else {
                                System.out.println(obj.get("name"));
                                usedInts.add(n);
                            }

                            //System.out.println(obj.get("id"));

                        }
                    }catch(Exception e){
                        System.out.println("EXCEPTION!: " + e);
                    }
                    break;
                default:

            }
            return null;
        }

        protected void onPreExecute() {
            Dialog.setMessage("Creating Workout..");
            Dialog.show();
        }

    }
}


