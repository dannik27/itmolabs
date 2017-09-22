import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Laba1b {

    ArrayList<String> out;

    public  static void main(String[] args){
        new Laba1b(args[0], args[1]);
    }



    public Laba1b(String inputFile, String outputFile){

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
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void checkPath(String path){

        File file = new File(path);
        if ((file.exists())&&(file.isDirectory())){
            for(File fileIn : file.listFiles()){
                checkPath(fileIn.getPath());

            }
        }else{

            try(FileInputStream f = new FileInputStream(path);){
                byte[] fileInArray = new byte[(int) file.length()];
                f.read(fileInArray);
                out.add(Integer.toHexString(hash(fileInArray)) + " " + path);
            }catch (FileNotFoundException ex){
                out.add("00000000 " + path);
            } catch (IOException e) {
                e.printStackTrace();
            }

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
