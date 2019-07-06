package com.JuanOvalle;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TemperatureConverterTest {

    TemperatureConverter temperatureConverter = new TemperatureConverter();
    @Test
    void getOutputWithOneQuery() {

        List<String[]> inputList = new ArrayList<String[]>();
        inputList.add(new String[]{"105","C","F"});
        List<String> outputList = new ArrayList<String>();
        outputList.add("105,C,F,221.0");

        assertEquals(outputList, temperatureConverter.getOutput(inputList));
    }
    @Test
    void getOutputWithAnInvalidNumber() {

        List<String[]> inputList = new ArrayList<String[]>();
        inputList.add(new String[]{"Test","C","F"});
        List<String> outputList = new ArrayList<String>();
        outputList.add("Invalid input!");

        assertEquals(outputList, temperatureConverter.getOutput(inputList));
    }
    @Test
    void getOutputWithInvalidQuery() {

        List<String[]> inputList = new ArrayList<String[]>();
        inputList.add(new String[]{"105","This is","a test"});
        List<String> outputList = new ArrayList<String>();
        outputList.add("Invalid input!");

        assertEquals(outputList, temperatureConverter.getOutput(inputList));
    }
    @Test
    void getOutputWithTwoQueries() {

        List<String[]> inputList = new ArrayList<String[]>();
        inputList.add(new String[]{"105","C","F"});
        inputList.add(new String[]{"45","F","K"});
        List<String> outputList = new ArrayList<String>();
        outputList.add("105,C,F,221.0");
        outputList.add("45,F,K,280.3722222222222");

        assertEquals(outputList, temperatureConverter.getOutput(inputList));
    }
    @Test
    void getOutputWithMultipleQueries() {

        List<String[]> inputList = new ArrayList<String[]>();
        inputList.add(new String[]{"105","C","F"});
        inputList.add(new String[]{"45","F","K"});
        inputList.add(new String[]{"56","C","K"});
        inputList.add(new String[]{"66","F","C"});
        List<String> outputList = new ArrayList<String>();
        outputList.add("105,C,F,221.0");
        outputList.add("45,F,K,280.3722222222222");
        outputList.add("56,C,K,329.15");
        outputList.add("66,F,C,18.88888888888889");

        assertEquals(outputList, temperatureConverter.getOutput(inputList));
    }
}