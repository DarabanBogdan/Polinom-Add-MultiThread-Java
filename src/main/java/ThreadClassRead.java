import javafx.scene.Node;

import java.io.IOException;

public class ThreadClassRead extends Thread {

    private LinkedList list;
    private Queue coada;
    private String fisier="poligon";
    private int nrPolinoame;
    public ThreadClassRead(Queue coada, int numarPolinoame){
        this.coada=coada;
        this.nrPolinoame=numarPolinoame;

    }
    @Override
    public void run(){

        for(int i=0;i<nrPolinoame;i++){
            LinkedList buff=new LinkedList();
            try {
                buff=Utils.readOne(fisier+Integer.toString(i)+".txt");
            } catch (IOException e) {
                e.printStackTrace();
            }

            LinkedList.Node nod=buff.getHead();
            while(nod!=null){
                    synchronized(coada) {
                        coada.insert(nod.data, nod.power);

                    }
                nod = nod.next;



            }

        }


            synchronized(coada){
                coada.insert(-1,-1);

            }




    }
}
