package com.JuanOvalle;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TemperatureConverter {


    public static void main(String[] args) throws IOException {

        List<String[]> queries = new ArrayList<String[]>();

        TemperatureConverter temperatureConverter = new TemperatureConverter();

        System.out.println("Welcome to the Temperature Converter Application!");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the input.txt location directory: ");
        String inputString = scanner. nextLine();
        System.out.print("Enter the desired location for the output.txt file: ");
        String outputString = scanner.nextLine();

        boolean success = true;
        int count = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(inputString))) {
            String line;
            while ((line = br.readLine()) != null) {
                if(count == 0){
                    count++;
                    continue;
                }
                if(line.split(",").length != 3)
                    throw new IOException();

                queries.add(line.split(","));
            }
            temperatureConverter.printOutput(outputString, temperatureConverter.getOutput(queries));
        } catch (Exception e){
            System.out.println("Invalid input file!");
            success = false;
        }finally {
            if(success)
                System.out.println("All conversions were successful!");
        }

        System.out.println("Press enter to exit...");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        in.readLine();
    }

    public List<String> getOutput(List<String[]> queries){

        List<String> outputLines = new ArrayList<String>();
        for (String[] words : queries) {

            String[] outputWords = new String[words.length + 1];
            try {
                Double num = Double.parseDouble(words[0]);
            } catch (NumberFormatException e) {
                outputLines.add("Invalid input!");
                continue;
            }
            if(words[1].equalsIgnoreCase("C") && words[2].equalsIgnoreCase("F")){

                //Celsius to Fahrenheit
                System.arraycopy(words, 0, outputWords, 0, words.length);
                outputWords[3] = Double.toString((9.0/5.0) * Double.parseDouble(words[0]) + 32);
            } else if(words[1].equalsIgnoreCase("C") && words[2].equalsIgnoreCase("K")){

                //Celsius to Kelvin
                System.arraycopy(words, 0, outputWords, 0, words.length);
                outputWords[3] = Double.toString(Double.parseDouble(words[0]) + 273.15);
            } else if(words[1].equalsIgnoreCase("F") && words[2].equalsIgnoreCase("C")){

                //Fahrenheit to Celsius
                System.arraycopy(words, 0, outputWords, 0, words.length);
                outputWords[3] = Double.toString((5*(Double.parseDouble(words[0]) - 32))/ 9);
            } else if(words[1].equalsIgnoreCase("F") && words[2].equalsIgnoreCase("K")){

                //Fahrenheit to Kelvin
                System.arraycopy(words, 0, outputWords, 0, words.length);
                outputWords[3] = Double.toString((Double.parseDouble(words[0]) - 32)* 5/9 + 273.15);
            } else if(words[1].equalsIgnoreCase("K") && words[2].equalsIgnoreCase("C")){

                //Kelvin to Celsius
                System.arraycopy(words, 0, outputWords, 0, words.length);
                outputWords[3] = Double.toString(Double.parseDouble(words[0]) - 273.15);
            } else if(words[1].equalsIgnoreCase("K") && words[2].equalsIgnoreCase("F")){

                //Kelvin to Fahrenheit
                System.arraycopy(words, 0, outputWords, 0, words.length);
                outputWords[3] = Double.toString((Double.parseDouble(words[0]) - 273.15) * 9/5 + 32);
            } else{
                outputLines.add("Invalid input!");
                continue;
            }
            int count = 0;
            String w = "";
            for (String s: outputWords) {

                if(count == 3){
                    w+=s;
                    break;
                }
                w+=s+",";
                count++;
            }
            outputLines.add(w);
        }
        return outputLines;
    }

    public void printOutput(String output, List<String> outputLines) {

        output+="\\output.txt";
        outputLines.add(0, "Value,Origin unit,Destination unit,Output");
        try{
            Path file = Paths.get(output);
            Files.write(file, outputLines, StandardCharsets.UTF_8);
        }catch (Exception e){
            System.out.println("Invalid output directory!");
        }
    }
}
