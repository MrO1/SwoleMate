package com.example.rid.swolemate;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WorkoutActivity extends AppCompatActivity {


    private WorkoutTypeClass workout;

    private ListView lv1;
    private ListView lv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        Bundle extras = getIntent().getExtras();
        System.out.println(extras.size());





        if(getIntent().getExtras().size() == 2){
            lv1 = (ListView)findViewById(R.id.lv1);
            workout = new WorkoutTypeClass((String)extras.get("Workout1"),(String)extras.get("WorkoutType"));
            workout.setSetsReps();
            System.out.println(workout.getWorkoutType());
        }else if (getIntent().getExtras().size() == 3){
            lv1 = (ListView)findViewById(R.id.lv1);
            lv2 = (ListView)findViewById(R.id.lv2);
            workout = new WorkoutTypeClass((String)extras.get("Workout1"), (String)extras.get("Workout2"),(String)extras.get("WorkoutType"));
            workout.setSetsReps();
            System.out.println(workout.getWorkoutType());
        }


        new CreateWorkout(this).execute(workout);
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

    private class CreateWorkout extends AsyncTask<WorkoutTypeClass, Void, ArrayList<ArrayList<String>>> {

        private ProgressDialog Dialog = new ProgressDialog(WorkoutActivity.this);

        Context activity;
        public CreateWorkout(Activity a){
            activity = a;
        }

        @Override
        protected ArrayList<ArrayList<String>> doInBackground(WorkoutTypeClass... params) {
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


            ArrayList<String> selectedWorkouts = new ArrayList<String>();
            ArrayList<String> selectedWorkouts2 = new ArrayList<String>();



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
                                selectedWorkouts.add(obj.get("name").toString());
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
                                selectedWorkouts.add(obj.get("name").toString());
                                usedInts.add(n);
                            }

                            //System.out.println(obj.get("id"));

                        }
                    }catch(Exception e){
                        System.out.println("EXCEPTION!: " + e);
                    }
                    break;
                case "leg":
                    try {
                        for (int i = 0; i < legMuscles.size(); i++) {
                            JSONObject parser = new JSONObject(readUrl("http://wger.de/api/v2/exercise/?format=json&language=2&muscles=" + legMuscles.get(i)));

                            JSONArray results = new JSONArray(parser.get("results").toString());
                            //System.out.println(results);
                            JSONObject currObject = new JSONObject();
                            for (int j = 0; j < results.length(); j++) {

                                currObject = (JSONObject) results.get(j);
                                JSONArray muscleList = (JSONArray) currObject.get("muscles");

                                if (muscleList.length() == 1) {
                                    legExercises.add(currObject);
                                }

                            }

                        }
                        for (int i = 0; i < 4; i++) {
                            //System.out.println(results.get(i));
                            int n = rand.nextInt(legExercises.size() - 1);
                            JSONObject obj = legExercises.get(n);
                            //if()
                            //JSONArray muscleList = new JSONArray(obj.get("muscles").toString());
                            if (usedInts.contains(n)) {
                                i -= 1;
                            } else {
                                System.out.println(obj.get("name"));
                                selectedWorkouts.add(obj.get("name").toString());
                                usedInts.add(n);
                            }

                            //System.out.println(obj.get("id"));

                        }
                    }catch(Exception e){
                        System.out.println("EXCEPTION!: " + e);
                    }
                    break;

                case "shoulder":
                    try {
                        for (int i = 0; i < shoulderMuscles.size(); i++) {
                            JSONObject parser = new JSONObject(readUrl("http://wger.de/api/v2/exercise/?format=json&language=2&muscles=" + shoulderMuscles.get(i)));

                            JSONArray results = new JSONArray(parser.get("results").toString());
                            //System.out.println(results);
                            JSONObject currObject = new JSONObject();
                            for (int j = 0; j < results.length(); j++) {

                                currObject = (JSONObject) results.get(j);
                                JSONArray muscleList = (JSONArray) currObject.get("muscles");

                                if (muscleList.length() == 1) {
                                    shoulderExercises.add(currObject);
                                }

                            }

                        }
                        for (int i = 0; i < 4; i++) {
                            //System.out.println(results.get(i));
                            int n = rand.nextInt(shoulderExercises.size() - 1);
                            JSONObject obj = shoulderExercises.get(n);
                            //if()
                            //JSONArray muscleList = new JSONArray(obj.get("muscles").toString());
                            if (usedInts.contains(n)) {
                                i -= 1;
                            } else {
                                System.out.println(obj.get("name"));
                                selectedWorkouts.add(obj.get("name").toString());
                                usedInts.add(n);
                            }

                            //System.out.println(obj.get("id"));

                        }
                    }catch(Exception e){
                        System.out.println("EXCEPTION!: " + e);
                    }
                    break;

                case "bicep":
                    try {
                        for (int i = 0; i < bicepMuscles.size(); i++) {
                            JSONObject parser = new JSONObject(readUrl("http://wger.de/api/v2/exercise/?format=json&language=2&muscles=" + bicepMuscles.get(i)));

                            JSONArray results = new JSONArray(parser.get("results").toString());
                            //System.out.println(results);
                            JSONObject currObject = new JSONObject();
                            for (int j = 0; j < results.length(); j++) {

                                currObject = (JSONObject) results.get(j);
                                JSONArray muscleList = (JSONArray) currObject.get("muscles");

                                if (muscleList.length() == 1) {
                                    bicepExercises.add(currObject);
                                }

                            }

                        }
                        for (int i = 0; i < 4; i++) {
                            //System.out.println(results.get(i));
                            int n = rand.nextInt(bicepExercises.size() - 1);
                            JSONObject obj = bicepExercises.get(n);
                            //if()
                            //JSONArray muscleList = new JSONArray(obj.get("muscles").toString());
                            if (usedInts.contains(n)) {
                                i -= 1;
                            } else {
                                System.out.println(obj.get("name"));
                                selectedWorkouts.add(obj.get("name").toString());
                                usedInts.add(n);
                            }

                            //System.out.println(obj.get("id"));

                        }
                    }catch(Exception e){
                        System.out.println("EXCEPTION!: " + e);
                    }
                    break;

                case "tricep":
                    try {
                        for (int i = 0; i < tricepMuscles.size(); i++) {
                            JSONObject parser = new JSONObject(readUrl("http://wger.de/api/v2/exercise/?format=json&language=2&muscles=" + tricepMuscles.get(i)));

                            JSONArray results = new JSONArray(parser.get("results").toString());
                            //System.out.println(results);
                            JSONObject currObject = new JSONObject();
                            for (int j = 0; j < results.length(); j++) {

                                currObject = (JSONObject) results.get(j);
                                JSONArray muscleList = (JSONArray) currObject.get("muscles");

                                if (muscleList.length() == 1) {
                                    tricepExercises.add(currObject);
                                }

                            }

                        }
                        for (int i = 0; i < 4; i++) {
                            //System.out.println(results.get(i));
                            int n = rand.nextInt(tricepExercises.size() - 1);
                            JSONObject obj = tricepExercises.get(n);
                            //if()
                            //JSONArray muscleList = new JSONArray(obj.get("muscles").toString());
                            if (usedInts.contains(n)) {
                                i -= 1;
                            } else {
                                System.out.println(obj.get("name"));
                                selectedWorkouts.add(obj.get("name").toString());
                                usedInts.add(n);
                            }

                            //System.out.println(obj.get("id"));

                        }
                    }catch(Exception e){
                        System.out.println("EXCEPTION!: " + e);
                    }
                    break;

                case "ab":
                    try {
                        for (int i = 0; i < backMuscles.size(); i++) {
                            JSONObject parser = new JSONObject(readUrl("http://wger.de/api/v2/exercise/?format=json&language=2&muscles=" + abMuscles.get(i)));

                            JSONArray results = new JSONArray(parser.get("results").toString());
                            //System.out.println(results);
                            JSONObject currObject = new JSONObject();
                            for (int j = 0; j < results.length(); j++) {

                                currObject = (JSONObject) results.get(j);
                                JSONArray muscleList = (JSONArray) currObject.get("muscles");

                                if (muscleList.length() == 1) {
                                    abExercises.add(currObject);
                                }

                            }

                        }
                        for (int i = 0; i < 4; i++) {
                            //System.out.println(results.get(i));
                            int n = rand.nextInt(abExercises.size() - 1);
                            JSONObject obj = abExercises.get(n);
                            //if()
                            //JSONArray muscleList = new JSONArray(obj.get("muscles").toString());
                            if (usedInts.contains(n)) {
                                i -= 1;
                            } else {
                                System.out.println(obj.get("name"));
                                selectedWorkouts.add(obj.get("name").toString());
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


            if(params[0].getMuscleGroup2() != null){
                switch(params[0].getMuscleGroup2()){
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
                                    selectedWorkouts2.add(obj.get("name").toString());
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
                                    selectedWorkouts2.add(obj.get("name").toString());
                                    usedInts.add(n);
                                }

                                //System.out.println(obj.get("id"));

                            }
                        }catch(Exception e){
                            System.out.println("EXCEPTION!: " + e);
                        }
                        break;
                    case "leg":
                        try {
                            for (int i = 0; i < backMuscles.size(); i++) {
                                JSONObject parser = new JSONObject(readUrl("http://wger.de/api/v2/exercise/?format=json&language=2&muscles=" + legMuscles.get(i)));

                                JSONArray results = new JSONArray(parser.get("results").toString());
                                //System.out.println(results);
                                JSONObject currObject = new JSONObject();
                                for (int j = 0; j < results.length(); j++) {

                                    currObject = (JSONObject) results.get(j);
                                    JSONArray muscleList = (JSONArray) currObject.get("muscles");

                                    if (muscleList.length() == 1) {
                                        legExercises.add(currObject);
                                    }

                                }

                            }
                            for (int i = 0; i < 4; i++) {
                                //System.out.println(results.get(i));
                                int n = rand.nextInt(legExercises.size() - 1);
                                JSONObject obj = legExercises.get(n);
                                //if()
                                //JSONArray muscleList = new JSONArray(obj.get("muscles").toString());
                                if (usedInts.contains(n)) {
                                    i -= 1;
                                } else {
                                    System.out.println(obj.get("name"));
                                    selectedWorkouts2.add(obj.get("name").toString());
                                    usedInts.add(n);
                                }

                                //System.out.println(obj.get("id"));

                            }
                        }catch(Exception e){
                            System.out.println("EXCEPTION!: " + e);
                        }
                        break;

                    case "shoulder":
                        try {
                            for (int i = 0; i < shoulderMuscles.size(); i++) {
                                JSONObject parser = new JSONObject(readUrl("http://wger.de/api/v2/exercise/?format=json&language=2&muscles=" + shoulderMuscles.get(i)));

                                JSONArray results = new JSONArray(parser.get("results").toString());
                                //System.out.println(results);
                                JSONObject currObject = new JSONObject();
                                for (int j = 0; j < results.length(); j++) {

                                    currObject = (JSONObject) results.get(j);
                                    JSONArray muscleList = (JSONArray) currObject.get("muscles");

                                    if (muscleList.length() == 1) {
                                        shoulderExercises.add(currObject);
                                    }

                                }

                            }
                            for (int i = 0; i < 4; i++) {
                                //System.out.println(results.get(i));
                                int n = rand.nextInt(shoulderExercises.size() - 1);
                                JSONObject obj = shoulderExercises.get(n);
                                //if()
                                //JSONArray muscleList = new JSONArray(obj.get("muscles").toString());
                                if (usedInts.contains(n)) {
                                    i -= 1;
                                } else {
                                    System.out.println(obj.get("name"));
                                    selectedWorkouts2.add(obj.get("name").toString());
                                    usedInts.add(n);
                                }

                                //System.out.println(obj.get("id"));

                            }
                        }catch(Exception e){
                            System.out.println("EXCEPTION!: " + e);
                        }
                        break;

                    case "bicep":
                        try {
                            for (int i = 0; i < bicepMuscles.size(); i++) {
                                JSONObject parser = new JSONObject(readUrl("http://wger.de/api/v2/exercise/?format=json&language=2&muscles=" + bicepMuscles.get(i)));

                                JSONArray results = new JSONArray(parser.get("results").toString());
                                //System.out.println(results);
                                JSONObject currObject = new JSONObject();
                                for (int j = 0; j < results.length(); j++) {

                                    currObject = (JSONObject) results.get(j);
                                    JSONArray muscleList = (JSONArray) currObject.get("muscles");

                                    if (muscleList.length() == 1) {
                                        bicepExercises.add(currObject);
                                    }

                                }

                            }
                            for (int i = 0; i < 4; i++) {
                                //System.out.println(results.get(i));
                                int n = rand.nextInt(bicepExercises.size() - 1);
                                JSONObject obj = bicepExercises.get(n);
                                //if()
                                //JSONArray muscleList = new JSONArray(obj.get("muscles").toString());
                                if (usedInts.contains(n)) {
                                    i -= 1;
                                } else {
                                    System.out.println(obj.get("name"));
                                    selectedWorkouts2.add(obj.get("name").toString());
                                    usedInts.add(n);
                                }

                                //System.out.println(obj.get("id"));

                            }
                        }catch(Exception e){
                            System.out.println("EXCEPTION!: " + e);
                        }
                        break;

                    case "tricep":
                        try {
                            for (int i = 0; i < tricepMuscles.size(); i++) {
                                JSONObject parser = new JSONObject(readUrl("http://wger.de/api/v2/exercise/?format=json&language=2&muscles=" + tricepMuscles.get(i)));

                                JSONArray results = new JSONArray(parser.get("results").toString());
                                //System.out.println(results);
                                JSONObject currObject = new JSONObject();
                                for (int j = 0; j < results.length(); j++) {

                                    currObject = (JSONObject) results.get(j);
                                    JSONArray muscleList = (JSONArray) currObject.get("muscles");

                                    if (muscleList.length() == 1) {
                                        tricepExercises.add(currObject);
                                    }

                                }

                            }
                            for (int i = 0; i < 4; i++) {
                                //System.out.println(results.get(i));
                                int n = rand.nextInt(tricepExercises.size() - 1);
                                JSONObject obj = tricepExercises.get(n);
                                //if()
                                //JSONArray muscleList = new JSONArray(obj.get("muscles").toString());
                                if (usedInts.contains(n)) {
                                    i -= 1;
                                } else {
                                    System.out.println(obj.get("name"));
                                    selectedWorkouts2.add(obj.get("name").toString());
                                    usedInts.add(n);
                                }

                                //System.out.println(obj.get("id"));

                            }
                        }catch(Exception e){
                            System.out.println("EXCEPTION!: " + e);
                        }
                        break;

                    case "ab":
                        try {
                            for (int i = 0; i < backMuscles.size(); i++) {
                                JSONObject parser = new JSONObject(readUrl("http://wger.de/api/v2/exercise/?format=json&language=2&muscles=" + abMuscles.get(i)));

                                JSONArray results = new JSONArray(parser.get("results").toString());
                                //System.out.println(results);
                                JSONObject currObject = new JSONObject();
                                for (int j = 0; j < results.length(); j++) {

                                    currObject = (JSONObject) results.get(j);
                                    JSONArray muscleList = (JSONArray) currObject.get("muscles");

                                    if (muscleList.length() == 1) {
                                        abExercises.add(currObject);
                                    }

                                }

                            }
                            for (int i = 0; i < 4; i++) {
                                //System.out.println(results.get(i));
                                int n = rand.nextInt(abExercises.size() - 1);
                                JSONObject obj = abExercises.get(n);
                                //if()
                                //JSONArray muscleList = new JSONArray(obj.get("muscles").toString());
                                if (usedInts.contains(n)) {
                                    i -= 1;
                                } else {
                                    System.out.println(obj.get("name"));
                                    selectedWorkouts2.add(obj.get("name").toString());
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
            }

            ArrayList<ArrayList<String>> returnValue = new ArrayList<>();
            returnValue.add(selectedWorkouts);
            if(params[0].getMuscleGroup2() != null){
                returnValue.add(selectedWorkouts2);
            }





            return returnValue;
        }

        protected void onPreExecute() {
            Dialog.setMessage("Creating Workout..");
            Dialog.show();
        }

        protected void onPostExecute(ArrayList<ArrayList<String>> selectedWorkoutsIn){

            if(getIntent().getExtras().size() == 2){
                ArrayList<String> workoutsToPopulateLV1 = selectedWorkoutsIn.get(0);
                ListViewAdapter adapter = new ListViewAdapter((android.app.Activity)activity, workoutsToPopulateLV1, workout.getSets(), workout.getReps());
                //ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(getApplicationContext(), R.layout.listview_item_custom, workoutsToPopulateLV1);

                //lv1.setAdapter(arrayAdapter1);
                lv1.setAdapter(adapter);
            }
            if(getIntent().getExtras().size() == 3){
                ArrayList<String> workoutsToPopulateLV1 = selectedWorkoutsIn.get(0);

                ListViewAdapter adapter = new ListViewAdapter((android.app.Activity)activity, workoutsToPopulateLV1, workout.getSets(), workout.getReps());




               // ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(getApplicationContext(), R.layout.listview_item_custom, workoutsToPopulateLV1);
                //lv1.setAdapter(arrayAdapter1);
                lv1.setAdapter(adapter);

                ArrayList<String> workoutsToPopulateLV2 = selectedWorkoutsIn.get(1);
                ListViewAdapter adapter2 = new ListViewAdapter((android.app.Activity)activity, workoutsToPopulateLV2, workout.getSets(), workout.getReps());
                //ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(getApplicationContext(), R.layout.listview_item_custom, workoutsToPopulateLV2);
                //lv2.setAdapter(arrayAdapter2);
                lv2.setAdapter(adapter2);
            }


            Dialog.hide();
            //lv1.setAdapter(arrayAdapter1);
        }

    }
}


