package com.smoothbrain;

import java.util.ArrayList;
import java.util.List;

public class Layer {
    private int inputCount;
    private List<Neuron> neurons;
    private List<Float> inputs;

    public Layer(int inputCount, int neuronCount){
        this.inputCount = inputCount;
        this.neurons = new ArrayList<Neuron>();

        for(int i = 0; i < neuronCount; i++){
            this.neurons.add(new Neuron(this.inputCount));
        }
    }

    public Layer(List<Neuron> neurons, int inputCount){
        this.inputCount = inputCount;
        this.neurons = neurons;
    }

    public List<Float> think(){
        List<Float> thoughts = new ArrayList<Float>();

        for(Neuron n: this.neurons){
            n.setInputs(this.inputs);
            thoughts.add(n.think());
        }

        return thoughts;
    }

    public void setInputs(List<Float> inputs){
        this.inputs = inputs;
    }

    public void mutate(float mutationRate){
        for(Neuron n : this.neurons){
            n.mutate(mutationRate);
        }
    }

    public static Layer breed(List<Layer> breedingPool){
        ArrayList<Neuron> bredNeurons = new ArrayList<Neuron>();
        int selector;

        for(int i = 0; i < breedingPool.get(0).getNeurons().size(); i++){
            selector = (int)(breedingPool.size() * Math.random());
            bredNeurons.add(breedingPool.get(selector).getNeurons().get(i));
        }

        return new Layer(bredNeurons, breedingPool.get(0).getInputCount());
    }

    public List<Neuron> getNeurons(){
        return this.neurons;
    }

    public int getInputCount(){
        return this.inputCount;
    }
}
