package com.example.rid.swolemate;

/**
 * Created by rid on 12/17/16.
 */

public class WorkoutTypeClass {

    private String muscleGroup1;
    private String muscleGroup2;

    private String workoutType;

    public WorkoutTypeClass(){

    }


    public WorkoutTypeClass(String muscleGroup1In, String workoutTypeIn){
        muscleGroup1 = muscleGroup1In;
        muscleGroup2 = null;
        workoutType = workoutTypeIn;
    }


    public WorkoutTypeClass(String muscleGroup1In, String muscleGroup2In, String workoutTypeIn){
        muscleGroup1 = muscleGroup1In;
        muscleGroup2 = muscleGroup2In;
        workoutType = workoutTypeIn;
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
}
