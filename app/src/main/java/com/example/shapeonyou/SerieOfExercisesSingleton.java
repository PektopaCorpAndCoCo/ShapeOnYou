package com.example.shapeonyou;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class SerieOfExercisesSingleton {
    private static SerieOfExercisesSingleton instance;

    private ExercisesType type = null;
    private ArrayList<String> serieOfExercises = null;
    private ArrayList<ExercicePage> serieOfExercisesPages = null;
    private int currentExerciseId = 0;


    private SerieOfExercisesSingleton(){
        //this.type = ExercisesType.abdo;
        //this.serieOfExercises = new ArrayList<String>(Arrays.asList("crunch-abdo", "crunch-bicycle", "planks", "russian-twist", "touche-talon"));

    }

    public static synchronized SerieOfExercisesSingleton getInstance(){
        if (instance == null){
            instance = new SerieOfExercisesSingleton();
        }
        return instance;
    }

    public ExercisesType getType(){
        return type;
    }

    public ArrayList<String> getSerieOfExercises(){
        return serieOfExercises;
    }

    public ArrayList<ExercicePage> getSerieOfExercisesPages(){
        return serieOfExercisesPages;
    }

    public int getCurrentExerciseId(){
        return currentExerciseId;
    }

    public ExercicePage getCurrentExercicePage(){
        return serieOfExercisesPages.get(currentExerciseId);
    }

    public void setType(ExercisesType newType){
        this.type = newType;
    }

    //not used for now

    public void setSerieOfExercises(ArrayList<String> newSerieOfExercises){
        this.serieOfExercises = newSerieOfExercises;
    }
/*
    public void addExerciseToSerieOfExercises(String newExercise){
        this.serieOfExercises.add(newExercise);
    }

    public void insertExerciseToSerieOfExercises(int index, String newExercise){
        this.serieOfExercises.add(index, newExercise);
    }

    public void changeExerciseOfSerieOfExercises(int index, String newExercise){
        this.serieOfExercises.set(index, newExercise);
    }

    public void removeExerciseOfSerieOfExercises(int index){
        this.serieOfExercises.remove(index);
    }*/

    public void setCurrentExerciseId(int newCurrentExerciseId){
        this.currentExerciseId = newCurrentExerciseId;
    }

    public void generateExercicePage(ArrayList<String> serieOfExercises, Context context) throws IOException {
        ArrayList<ExercicePage> result = new ArrayList<ExercicePage>();
        for(String Exercise : serieOfExercises){
            ExercicePage exercicePage = new ExercicePage();
            //exercicePage.name = serieOfExercises[i];

            String name = Exercise.replace("-", "_");
            InputStream csvIs = context.getAssets().open(type + "/" + name + "/" + name + "_data.csv");
            int videoResId = context.getResources().getIdentifier(name, "raw", context.getPackageName());


            String[] exerciseData = readCsvFile(csvIs);

            exercicePage.name = exerciseData[0];
            exercicePage.videoUrl = "android.resource://" + context.getPackageName() + "/" + videoResId;
            exercicePage.instructions = exerciseData[1];
            exercicePage.repetitionNumber = 10; //temp hardcode (need to be rethink)

            result.add(exercicePage);
        }
        //return result;
        this.serieOfExercisesPages = result;
    }

    private String[] readCsvFile(InputStream csvIs){
        String[] tokens = null;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(csvIs));
            String line;

            if ((line = reader.readLine()) != null) {
                tokens = line.split(",");
            }

            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
            tokens = new String[]{"Name Error", "Instruction Error"};
        }
        return tokens;
    }

    public boolean nextExercise(){
        if(currentExerciseId >= serieOfExercises.size()-1) {
            return false;
        }else {
            this.currentExerciseId += 1;
            return true;
        }
    }

    public boolean previousExercise(){
        if(currentExerciseId <= 0) {
            return false;
        }else {
            this.currentExerciseId -= 1;
            return true;
        }
    }

    public void reset(){
        this.type = null;
        this.serieOfExercises = null;
        this.serieOfExercisesPages = null;
        this.currentExerciseId = 0;
    }
}
