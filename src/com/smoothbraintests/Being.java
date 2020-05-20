package com.smoothbraintests;

import com.smoothbrain.Brain;

import java.util.List;

public class Being implements Comparable<Being> {
    private int fitness;
    private Brain brain;

    public Being(int inputCount, int outputCount, int layerCount, int layerHeight){
        this.brain = new Brain(inputCount, outputCount, layerCount, layerHeight);
    }

    public Being(Brain brain){
        this.brain = brain;
    }

    public void resetFitness(){
        this.fitness = 0;
    }

    public void mutate(float mutationRate){
        this.brain.mutate(mutationRate);
    }

    public int think(List<Float> inputs){
        brain.setInputs(inputs);
        List<Float> thoughts = brain.think();

        if(thoughts.get(0) > 0f && thoughts.get(0) < 1000f){
            this.fitness += 10;
        }

        if(thoughts.get(1) > 0.1f && thoughts.get(1) < 1f){
            this.fitness += 1000;
        }

        if(thoughts.get(2) > 0.5f && thoughts.get(2) < 1f){
            this.fitness += 1000;
        }

        if(thoughts.get(3) <= -0.0f && thoughts.get(3) > -1000.0f){
            this.fitness += 10;
        }

        if(fitness > 20){
            int fuck = 100;
        }
        return this.fitness;
    }

    public Brain getBrain(){
        return this.brain;
    }

    public int getFitness(){
        return fitness;
    }

    @Override
    public int compareTo(Being o) {
        return this.fitness - o.getFitness();
    }


}