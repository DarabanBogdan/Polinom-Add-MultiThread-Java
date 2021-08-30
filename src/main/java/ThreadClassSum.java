import java.io.IOException;

public class ThreadClassSum extends Thread {

    private LinkedList list=new LinkedList();
    private Queue coada;
    private String fisier="poligon";
    public ThreadClassSum(LinkedList list, Queue coada){
        this.list=list;
        this.coada=coada;

    }
    @Override
    public void run(){

        while(true){
            Queue.Node buff;

            synchronized (coada){
                buff=coada.getFirst();
                if(buff!=null){
                if(buff.power==-1)
                    break;
                else {

                    coada.remove();

                }

                    }


            }
            if(buff!=null){
                try {
                    sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if(list.head!=null){
                    if(list.finOneBefore(buff.power).next!=null){
                        synchronized (list.finOneBefore(buff.power)){
                            //synchronized (list.finOneBefore(nod.power).next) {
                                list.insert(buff.data, buff.power);
                                 if (list.findOne(buff.power) == 0)
                                    list.delete(buff.power);

                    }}else{
                        synchronized (list.finOneBefore(buff.power)){
                        list.insert(buff.data,buff.power);
                        if(list.findOne(buff.power)==0)
                            list.delete(buff.power);}
                }}
                else{ synchronized (list){

                        list.insert(buff.data,buff.power);
                        if(list.findOne(buff.power)==0)
                            list.delete(buff.power);


                         }
                    }

            }

        }

    }
}
