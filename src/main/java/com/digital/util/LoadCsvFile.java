package com.digital.util;

import com.digital.model.Quitance;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LoadCsvFile {
    public static final String SPLIT = ";";


    public static List<String> getData(String pathname) {

        //String file = csvFile;
        BufferedReader br = null;
        String line = "";

        int i = 0;
        List<String> results = new ArrayList<String>();

        try {

            br = new BufferedReader(new FileReader(pathname));
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] columns = line.split(SPLIT);
                //if (!columns[0].contains(first)) {
                results.add(line);
                //}

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("Done");
        return results;
    }

}
