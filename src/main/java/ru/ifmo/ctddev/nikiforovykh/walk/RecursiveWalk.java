package ru.ifmo.ctddev.nikiforovykh.walk;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
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

        try(
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
        ){

            String path;
            while((path = reader.readLine())!=null){
               //checkPath(path);

                if( new File(path).exists()){

                    Files.walkFileTree(Paths.get(path), new SimpleFileVisitor<Path>(){

                        @Override
                        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

                            out.add( getFileHash(file.toString()) + " " + file.toString());
                            return super.visitFile(file, attrs);

                        }

                        @Override
                        public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {

                            out.add("00000000 " + file.toString());
                            return FileVisitResult.SKIP_SUBTREE;
                        }
                    });
                }else{
                    out.add("00000000 " + path);
                }


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


    private String getFileHash(String path){

        String result = "";

        try(InputStream f = new BufferedInputStream(new FileInputStream(path))){

            byte[] fileInArray;
            int hash = Walk.HASH_START;
            int bufferSize = 1048576;

            while(f.available() > 0){

                if(f.available() > bufferSize){
                    fileInArray = new byte[bufferSize];
                }else{
                    fileInArray = new byte[f.available()];
                }

                f.read(fileInArray, 0 , fileInArray.length);
                hash = Walk.incrementHash(hash, fileInArray);
            }

            result = Walk.hashToHex(hash);

        }catch (FileNotFoundException ex){
            result = "00000000";
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;


    }


}
