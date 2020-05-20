package com.smoothbrain;

import java.util.ArrayList;
import java.util.List;

public class Brain {
    private int inputCount;
    private int outputCount;
    private List<Layer> layers;
    private List<Float> inputs;

    public Brain(int inputCount, int outputCount, int layerCount, int layerHeight){
        this.inputCount = inputCount;
        this.outputCount = outputCount;
        this.layers = new ArrayList<Layer>();

        //first layer has to have the number of inputs as the brain
        //subsequent layers will use the layerHeight as its number of inputs

        this.layers.add(new Layer(inputCount, layerHeight));

        //assumes a non-zero number of layers
        for(int i = 1; i < layerCount; i++){
            this.layers.add(new Layer(inputCount, layerHeight));
        }

        //final layer is the output layer
        this.layers.add(new Layer(layerHeight, outputCount));

        /*

        In theory this will end up like this for an input count of 4, output of 3, hidden layer count of 2, height of 4:
        (not including links between neurons, obviously)

        I   H   H
                    O
        I   H   H
                    O
        I   H   H
                    O
        I   H   H

         */
    }

    public Brain(List<Layer> layers){
        this.layers = layers;
        this.inputCount = layers.get(0).getNeurons().size();
        this.outputCount = layers.get(layers.size() - 1).getNeurons().size();
    }

    public List<Layer> getLayers(){
        return this.layers;
    }

    public int getHeight() {
        if (this.layers.size() > 0){
            return this.layers.get(0).getNeurons().size();
        }else{
            return 0;
        }
    }

    public void setLayers(List<Layer> layers){
        this.layers = layers;
    }

    public List<Float> think(){
        List<Float> thoughts = new ArrayList<Float>();

        //first layer may have is using the inputs rather than the previous layer's thoughts
        this.layers.get(0).setInputs(this.inputs);
        thoughts = this.layers.get(0).think();

        for(int i = 1; i < this.layers.size(); i++){
            this.layers.get(i).setInputs(thoughts);
            thoughts = this.layers.get(i).think();
        }

        return thoughts;
    }

    public void setInputs(List<Float> inputs){
        this.inputs = inputs;
    }

    public void mutate(float mutationRate){
        for(Layer l : this.layers){
            l.mutate(mutationRate);
        }
    }

    public static Brain breed(List<Brain> breedingPool){
        ArrayList<Layer> bredLayers = new ArrayList<Layer>();
        int selector;

        for(int i = 0; i < breedingPool.get(0).getLayers().size(); i++){
            selector = (int)(breedingPool.size() * Math.random());
            bredLayers.add(breedingPool.get(selector).getLayers().get(i));
        }

        return new Brain(bredLayers);
    }
}
