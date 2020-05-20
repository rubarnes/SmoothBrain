package com.smoothbrain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Neuron {
    private int inputCount;
    private List<Float> inputs;
    private List<Float> weights;
    private float bias;

    public Neuron(int inputCount){
        this.inputCount = inputCount;
        this.weights = new ArrayList<Float>();

        Random r = new Random();

        for(int i = 0; i < this.inputCount; i++){
            this.weights.add(r.nextFloat());
        }

        this.bias = r.nextFloat();
    }

    public Neuron(List<Float> weights, Float bias){
        this.weights = weights;
        this.bias = bias;
        this.inputCount = weights.size();
    }

    public Float think(){
        float result = 0.0f;
        for(int i = 0; i < inputs.size(); i++){
            result += inputs.get(i) * weights.get(i);
        }
        result += bias;
        return result;
    }

    public void mutate(Float mutationRate){
        ArrayList<Float> newWeights = new ArrayList<Float>();

        Random r = new Random();

        for(float w : weights) {
            if(r.nextFloat() >= 0.5f) {
                newWeights.add((r.nextFloat() * mutationRate) + w);
            }else{
                newWeights.add((r.nextFloat() * mutationRate * -1) + w);
            }
        }

        this.weights = newWeights;
    }

    public static Neuron breed(List<Neuron> breedingPool){
        //this is probably awful.
        ArrayList<Float> bredWeights = new ArrayList<Float>();
        float bredBias = 0.0f;
        int selector;

        //randomly choose weights from all parents
        for(int i = 0; i < breedingPool.get(0).getWeights().size(); i++){
            selector = (int)(breedingPool.size() * Math.random());
            bredWeights.add(breedingPool.get(selector).getWeights().get(i));
        }

        selector = (int)(breedingPool.size() * Math.random());
        bredBias = breedingPool.get(selector).getBias();

        return new Neuron(bredWeights, bredBias);

    }

    public void setInputs(List<Float> inputs){
        this.inputs = inputs;
    }

    public void setWeights(List<Float> weights){
        this.weights = weights;
    }

    public List<Float> getWeights(){
        return this.weights;
    }

    public float getBias(){
        return this.bias;
    }
}
