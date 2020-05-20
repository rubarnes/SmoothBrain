package com.smoothbraintests;

import com.smoothbrain.Brain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {


    public static void main(String[] args) {
        ArrayList<Being> currentGeneration = new ArrayList<Being>();
        ArrayList<Being> nextGeneration = new ArrayList<Being>();
        for(int i = 0; i < 100; i++){
            currentGeneration.add(new Being(10,4,4,4));
        }

        ArrayList<Float> inputs = new ArrayList<Float>();

        inputs.add(1.0f);
        inputs.add(2.0f);
        inputs.add(3.0f);
        inputs.add(4.0f);
        inputs.add(4.0f);
        inputs.add(4.0f);
        inputs.add(4.0f);
        inputs.add(4.0f);
        inputs.add(4.0f);
        inputs.add(4.0f);

        int generation = 0;
        int sumFitness = 0;
        float sumA;
        float sumB;
        float sumC;
        float sumD;

        while(true){
            generation++;

            sumFitness = 0;
            sumA = 0;
            sumB = 0;
            sumC = 0;
            sumD = 0;

            for(Being b : currentGeneration){
                sumFitness += b.think(inputs);
                List<Float> thoughts = b.getBrain().think();
                sumA += thoughts.get(0);
                sumB += thoughts.get(1);
                sumC += thoughts.get(2);
                sumD += thoughts.get(3);
            }

            Collections.reverse(currentGeneration);

            System.out.println("Generation " + generation + " average outputs: " + (sumA/100) + "|" + (sumB/100) + "|" + (sumC/100) + "|" + (sumD/100));
            System.out.println("Generation " + generation + " total fitness: " + sumFitness + " average fitness: " + (sumFitness / 100) + " best fitness: " + currentGeneration.get(0).getFitness());



            for(int i = 0; i < 10; i++){
                currentGeneration.get(i).mutate(0.0001f);
                currentGeneration.get(i).resetFitness();
                nextGeneration.add(currentGeneration.get(i));
            }

            currentGeneration.clear();

            for(int i = 0; i < 10; i++){
                currentGeneration.addAll(nextGeneration);
            }

            nextGeneration.clear();
        }

    }

}
