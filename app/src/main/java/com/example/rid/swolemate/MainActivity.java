package com.example.rid.swolemate;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private int selectedMuscleGroups = 0;

    private ToggleButton chestButton;
    private ToggleButton backButton;
    private ToggleButton shoulderButton;
    private ToggleButton bicepButton;
    private ToggleButton tricepButton;
    private ToggleButton legButton;

    private ToggleButton powerStrength;
    private ToggleButton enduranceHyp;

    private ArrayList<ToggleButton> muscleButtons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chestButton = (ToggleButton)findViewById(R.id.chestButton);
        backButton = (ToggleButton)findViewById(R.id.backButton);
        shoulderButton = (ToggleButton)findViewById(R.id.shouldersButton);
        bicepButton = (ToggleButton)findViewById(R.id.bicepsButton);
        tricepButton = (ToggleButton)findViewById(R.id.tricepsButton);
        legButton = (ToggleButton)findViewById(R.id.legsButton);

        powerStrength = (ToggleButton)findViewById(R.id.powerStrengthButton);
        enduranceHyp = (ToggleButton)findViewById(R.id.hyperEnduranceButton);

        muscleButtons = new ArrayList<ToggleButton>();

        muscleButtons.add(chestButton);
        muscleButtons.add(backButton);
        muscleButtons.add(shoulderButton);
        muscleButtons.add(bicepButton);
        muscleButtons.add(tricepButton);
        muscleButtons.add(legButton);


        chestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(chestButton.isChecked());

                if(chestButton.isChecked() == true){
                    selectedMuscleGroups += 1;
                }else{
                    selectedMuscleGroups -= 1;
                }
                System.out.println(selectedMuscleGroups);

                checkMuscleButtonsEnableDisable();
            }
        });



        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(backButton.isChecked() == true){
                    selectedMuscleGroups += 1;
                }else{
                    selectedMuscleGroups -= 1;
                }
                System.out.println(selectedMuscleGroups);

                checkMuscleButtonsEnableDisable();

            }
        });

        shoulderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(shoulderButton.isChecked() == true){
                    selectedMuscleGroups += 1;
                }else{
                    selectedMuscleGroups -= 1;
                }
                System.out.println(selectedMuscleGroups);

                checkMuscleButtonsEnableDisable();

            }
        });

        bicepButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bicepButton.isChecked() == true){
                    selectedMuscleGroups += 1;
                }else{
                    selectedMuscleGroups -= 1;
                }
                System.out.println(selectedMuscleGroups);

                checkMuscleButtonsEnableDisable();

            }
        });

        tricepButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tricepButton.isChecked() == true){
                    selectedMuscleGroups += 1;
                }else{
                    selectedMuscleGroups -= 1;
                }
                System.out.println(selectedMuscleGroups);

                checkMuscleButtonsEnableDisable();

            }
        });

        legButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(legButton.isChecked() == true){
                    selectedMuscleGroups += 1;
                }else{
                    selectedMuscleGroups -= 1;
                }
                System.out.println(selectedMuscleGroups);

                checkMuscleButtonsEnableDisable();

            }
        });

        powerStrength.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(enduranceHyp.isChecked()){
                    enduranceHyp.setChecked(false);
                }
            }
        });

        enduranceHyp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(powerStrength.isChecked()){
                    powerStrength.setChecked(false);
                }
            }
        });

        Button submitButton = (Button)findViewById(R.id.getWorkoutButton);

    }

    private void checkMuscleButtonsEnableDisable(){
        if(selectedMuscleGroups == 2){
            for(int i = 0; i < muscleButtons.size(); i++){
                if(muscleButtons.get(i).isChecked()){

                }else{
                    muscleButtons.get(i).setEnabled(false);
                }

            }
        }else if (selectedMuscleGroups < 2){
            for(int i = 0; i < muscleButtons.size(); i++){
                muscleButtons.get(i).setEnabled(true);
            }
        }
    }


}
