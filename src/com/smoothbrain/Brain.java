package com.smoothbrain;

import java.util.ArrayList;
import java.util.List;

public class Brain {
    private int inputCount;
    private int outputCount;
    private List<Layer> layers;

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
}
