package st;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Test17 {
    String name;
    public Test17(String name) {
        this.name = name;
    }

    public static void make ()throws IOException {

            FileWriter fileWriter = new FileWriter("filll.txt");
            fileWriter.write( "Этот файл создан мной.\n");
            fileWriter.write( "Теперь он будет жить на диске\n");
            fileWriter.write( "или на флешке\n");
            fileWriter.close();
        }

    private int lineCount(String filename) throws Exception {

        int ii=0;
        try {
            FileReader  reader = new FileReader(filename);
            Scanner scanner = new Scanner(reader);


            while (scanner.hasNextLine()) {
                scanner.nextLine();
                ii++;            }
            reader.close();

        } catch (Exception e) {
            throw new IOException("файл не найден");

            }



        return ii;
    }
    public String test(String filename)  {

        try {
            filename.charAt(1);

        } catch (Exception e) {
           return  (new IOException() +" File not found").toString();

        }
        return "File processing";
    }

    public void test(int n){
        try {
            n=0;

        }
        catch (Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
        finally {
            System.out.println("finally executed");
        }

    }


    public static void main(String[] args) throws Exception {
        Test17 tt= new Test17("test");

        System.out.println(tt.lineCount("file1.txt"));
        double d=0;
        double r=0;

        System.out.println(tt.test("jkj"));


    }
}

