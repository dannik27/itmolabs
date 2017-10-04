package ru.ifmo.ctddev.nikiforovykh.walk;

import java.io.*;
import java.util.ArrayList;

public class RecursiveWalk {

    ArrayList<String> out;

    public  static void main(String[] args){

        try {
            new RecursiveWalk(args[0], args[1]);
        }catch (ArrayIndexOutOfBoundsException ex){
            System.out.println("Неверное кол-во аргументов");
        }
    }



    public RecursiveWalk(String inputFile, String outputFile){

        out = new ArrayList<>();

        try (
                BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
        ){

            String path;
            while((path = reader.readLine())!=null){
               checkPath(path);
            }

            for (String line : out){
                writer.write(line);
                writer.newLine();
            }

        } catch (FileNotFoundException e) {
            System.out.println("Не найден входной файл");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void checkPath(String path){

        File file = new File(path);
        if ((file.exists())&&(file.isDirectory())&&(file.listFiles() != null)){
            for(File fileIn : file.listFiles()){
                checkPath(fileIn.getPath());
            }
        }else{


            try(FileInputStream f = new FileInputStream(path)){

                byte[] fileInArray = new byte[(int) file.length()];
                f.read(fileInArray);
                out.add(Walk.hash(fileInArray) + " " + path);

            }catch (FileNotFoundException ex){
                out.add("00000000 " + path);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


    }


}
