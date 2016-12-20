package com.example.rid.swolemate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by rid on 12/17/16.
 */

public class WorkoutTypeClass implements Serializable{

    private String muscleGroup1;
    private String muscleGroup2;

    private String workoutType;

    private ArrayList<String> workout1;
    private ArrayList<String> workout2;

    private ArrayList<Integer> sets;
    private ArrayList<Integer> reps;




    public WorkoutTypeClass(){

    }


    public WorkoutTypeClass(String muscleGroup1In, String workoutTypeIn){
        muscleGroup1 = muscleGroup1In;
        muscleGroup2 = null;
        workoutType = workoutTypeIn;
        workout1 = new ArrayList<>();

        sets = new ArrayList<Integer>();
        reps = new ArrayList<Integer>();
    }


    public WorkoutTypeClass(String muscleGroup1In, String muscleGroup2In, String workoutTypeIn){
        muscleGroup1 = muscleGroup1In;
        muscleGroup2 = muscleGroup2In;
        workoutType = workoutTypeIn;

        workout1 = new ArrayList<>();
        workout2 = new ArrayList<>();

        sets = new ArrayList<Integer>();
        reps = new ArrayList<Integer>();
    }

    public String getMuscleGroup1(){
        return muscleGroup1;
    }

    public String getMuscleGroup2(){
        return muscleGroup2;
    }

    public String getWorkoutType(){
        return workoutType;
    }

    public void addWorkout(int workoutNumber, String workoutName){
        if(workoutNumber == 1){
            workout1.add(workoutName);
        }else if (workoutNumber == 2){
            workout2.add(workoutName);
        }
    }

    public void createWorkout(){

    }

    public void setSetsReps(){
        Random r = new Random();
        if (workoutType.equals("power")){


            //Power and Strength
            for(int i = 0; i < 4; i++){
                int setsSelection = r.nextInt(6-3) + 3;
                int repsSelection = r.nextInt(7-3) + 3;
                sets.add(setsSelection);
                reps.add(repsSelection);
            }


        }else if (workoutType.equals("hypertrophy")){
            //Hypertrophy and Endurance
            for(int i = 0; i < 4; i++){
                int setsSelection = r.nextInt(7-4) + 4;
                int repsSelection = r.nextInt(16-8) + 8;
                sets.add(setsSelection);
                reps.add(repsSelection);

            }
        }
    }

    public ArrayList<Integer> getSets(){
        return sets;
    }

    public ArrayList<Integer> getReps(){
        return reps;
    }

//    public ArrayList<String> getWorkout(int workoutNumber){
//        ArrayList<String> retVal;
//        if(workoutNumber == 1){
//            return workout1;
//        }else if (workoutNumber == 2){
//            return workout2;
//        }
//
//        return null;
//    }


//    public int getMuscleNumber(int workoutNumber){
//        if(workoutNumber == 1){
//            if(muscleGroup1.equals("chest")){
//                return 4;
//            }else if (muscleGroup1).equals("back"){
//                return 12;
//            }
//        }
//
//    }
}
