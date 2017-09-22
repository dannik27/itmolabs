import java.io.*;
import java.nio.charset.StandardCharsets;

public class Laba1 {



    public  static void main(String[] args){
        new Laba1(args[0], args[1]);
    }

    public Laba1(String inputFile, String outputFile){

        try (
             BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
        ){

            String path;
            while((path = reader.readLine())!=null){

                try(FileInputStream f = new FileInputStream(path);){

                    byte[] fileInArray = new byte[(int) new File(path).length()];
                    f.read(fileInArray);
                    writer.write(Integer.toHexString(hash(fileInArray)) + " " + path);
                    writer.newLine();

                }catch (FileNotFoundException ex){
                    writer.write("00000000 " + path);
                    writer.newLine();
                }



            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private int hash(final byte[] bytes) {
        int h = 0x811c9dc5;
        for (final byte b : bytes) {
            h = (h * 0x01000193) ^ (b & 0xff);
        }
        return h;
    }


}
