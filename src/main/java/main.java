import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;


public class main {

    public static void sumaPolinoame(int numarPoligoane,int nrThreads, String output) throws InterruptedException, IOException {

        Thread th[] = new Thread[nrThreads];

        LinkedList list=new LinkedList();
        Queue coada=new Queue();
        long startTime = System.nanoTime();

        th[0]=new ThreadClassRead(coada,numarPoligoane);
        th[0].start();

        for (int i = 1; i < nrThreads; i++) {
            th[i] = new ThreadClassSum(list,coada);
            th[i].start();
        }
        for (int i = 0; i < nrThreads; i++)
            th[i].join();

        long finishTime = System.nanoTime();

        list.printList(output);
        Utils.writeFileExcel(((finishTime - startTime) / 1000000),"A1");

    }

    public static void main(String[] args) throws IOException, InterruptedException {
        int numarPoligoane=200;
        int min=40;
        int max=100;
        int maxCof=1000;
        String out="output";
        String fisier="poligon";
        for(int i=0;i<numarPoligoane;i++)
            Utils.createPolinom(fisier+Integer.toString(i)+".txt",min,max,maxCof);

        sumaPolinoame(numarPoligoane,8,"final.txt");



    }
}
