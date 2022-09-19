package com.epam.mjc.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;


public class FileReader {

    public Profile getDataFromFile(File file) {
        if (!file.canRead()) {
            return new Profile();
        }
        Profile result = new Profile();
        try(BufferedReader br = new BufferedReader(new java.io.FileReader(file))) {
            String next;
            while ((next = br.readLine()) != null) {
                fillColumn(result, next);
            }
            return result;
        }  catch (IOException e) {
            e.printStackTrace();
        }
        return new Profile();
    }

    private void fillColumn(Profile pf, String line) {
        String[] args = line.split("\\s");
        try {
            switch (args[0]) {
                case "Name:":
                    pf.setName(args[1]);
                    break;
                case "Age:":
                    pf.setAge(Integer.parseInt(args[1]));
                    break;
                case "Email:":
                    pf.setEmail(args[1]);
                    break;
                case "Phone:":
                    pf.setPhone(Long.parseLong(args[1]));
                    break;
                default:
                    throw new NumberFormatException("There was unknown argument:" + args[0]);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}