import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class Utils {

    public static LinkedList readOne(String nume) throws IOException {
        LinkedList polinom=new LinkedList();
        boolean nextIsPower=false;
        int buf=0;
        int power=0;
        int sign=1;
        File file = new File(nume);
        if(file.exists() && !file.isDirectory()) {
            FileInputStream fis = new FileInputStream(file);
            int i = fis.read();
            while (i != -1) {
                if ((i == '-' || i == '+') && nextIsPower) {
                    polinom.insert(buf * sign, power);
                    buf = 0;
                    power = 0;
                    nextIsPower = false;
                }

                if (i == '-')
                    sign = -1;
                else if (i == '+')
                    sign = 1;
                else if (i == 'x')
                    nextIsPower = true;
                else if (nextIsPower == false)
                    buf = buf * 10 + Character.getNumericValue(i);
                else if (nextIsPower == true)
                    power = power * 10 + Character.getNumericValue(i);


                i = fis.read();

            }
            fis.close();

            polinom.insert(buf * sign, power);

            return polinom;
        }
        else return null;

    }

    public static void createPolinom(String nume, int min, int max,int maxCof) throws FileNotFoundException, UnsupportedEncodingException {

        PrintWriter writer = new PrintWriter(nume, "UTF-8");
        Random rand = new Random();
        int size=rand.nextInt(max-min)+min;
        ArrayList<Integer> list = new ArrayList<Integer>(size);
        for(int i = 0; i < size; i++) {
            list.add(i+min);
        }

        for(int i=0;i<size;i++) {
            int k=rand.nextInt(list.size());
            int power = list.get(k);
            list.remove(k);
            int data=rand.nextInt(maxCof)+1;
            int sigh =rand.nextInt(2);
            if(sigh==0)
                writer.print("-");
            else
                writer.print("+");

            writer.print(data+"x"+power);
        }
        writer.close();

    }
    public static void writeFileExcel(long numar,String thred) throws IOException {
        FileWriter writer = new FileWriter("time.csv",true);
        writer.write(numar+"ms,"+ thred);
        writer.write('\n');
        writer.close();


    }
    public static boolean checkTwoFiles(String fille1, String fille2){
        boolean areFilesIdentical = true;
        File file1 = new File(fille1);
        File file2 = new File(fille2);
        if (!file1.exists() || !file2.exists()) {

            return false;
        }
        try {
            FileInputStream fis1 = new FileInputStream(file1);
            FileInputStream fis2 = new FileInputStream(file2);
            int i1 = fis1.read();
            int i2 = fis2.read();
            while (i1 != -1) {
                if (i1 != i2) {
                    System.out.print(i1+" "+i2);
                    areFilesIdentical = false;
                    break;
                }
                i1 = fis1.read();
                i2 = fis2.read();
            }
            fis1.close();
            fis2.close();
        } catch (IOException e) {
            System.out.println("IO exception");
            areFilesIdentical = false;
        }
        return areFilesIdentical;
    }
}
