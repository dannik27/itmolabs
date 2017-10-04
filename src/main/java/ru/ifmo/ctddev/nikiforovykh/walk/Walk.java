package ru.ifmo.ctddev.nikiforovykh.walk;

import java.io.*;

public class Walk {



    public  static void main(String[] args){


//        try{
//            if(args.length != 2){
//                throw new Exception("Неверное кол-во аргументов");
//
//            }
//        }catch(Exception ex){
//
//        }
        try {
            new Walk(args[0], args[1]);
        }catch (ArrayIndexOutOfBoundsException ex){
            System.out.println("Неверное кол-во аргументов");
        }

    }



    public Walk(String inputFile, String outputFile){

        try (
             BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
        ){

            String path;
            while((path = reader.readLine()) != null){

                try(FileInputStream f = new FileInputStream(path);){



                    byte[] fileInArray = new byte[(int) new File(path).length()];
                    f.read(fileInArray);
                    writer.write(hash(fileInArray) + " " + path);
                    writer.newLine();

                }catch (FileNotFoundException ex){
                    writer.write("00000000 " + path);
                    writer.newLine();
                }



            }


        } catch (FileNotFoundException e) {
            System.out.println("Не найден входной файл");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static String hash(final byte[] bytes) {
        int h = 0x811c9dc5;
        for (final byte b : bytes) {
            h = (h * 0x01000193) ^ (b & 0xff);
        }
        StringBuffer hh = new StringBuffer(Integer.toHexString(h));
        //StringBuffer hh = new StringBuffer("123456");
        //System.out.println("hash->" + hh.toString());
        int len = hh.length();
        for(int i=0;i<8 - len;i++){
            hh.insert(0, '0');
        }
        return hh.toString();
    }


}
