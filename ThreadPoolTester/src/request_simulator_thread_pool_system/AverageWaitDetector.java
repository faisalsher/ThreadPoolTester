package request_simulator_thread_pool_system;

//import java.util.TimerTask;

/**
 * <p>Title: Thread Pool System</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */
import java.util.*;
import javax.swing.*;
public class AverageWaitDetector extends TimerTask {
  Pool pool;
  LinkedList queue;
  JTextArea jta;
   double sum;
   int size,i;
  double averageWaitTime=0;
  public AverageWaitDetector(Pool pool,JTextArea jta) {
    this.pool=pool;
    queue=this.pool.getJobQ();
    this.jta=jta;
    this.jta.setLineWrap(true);
    if(pool instanceof ThreadPool) jta.append("\nFBOS Average Waits\n");
     else  if(pool instanceof WaterMarkThreadPooll)jta.append("\nWaterMark Average Waits\n");
       else  if(pool instanceof HeuristicThreadPool)jta.append("\nHeuristic Average Waits\n");
jta.append("\n");
  }
  public void run() {
   size=i=0;
   sum=averageWaitTime=0.0;
    long currentTime=System.currentTimeMillis();
    synchronized(this.queue){
      size=this.queue.size();

    while(i<size){
      ParentJob job=(ParentJob)this.queue.get(i);
    sum+=(currentTime-job.getInQueueTime());
    i++;
       }
    }
    if(size==0) this.averageWaitTime=0;
    else
    this.averageWaitTime=sum/size;
     Double e=new Double(this.averageWaitTime);
    // this.jta.append(bump);
this.jta.append(""+e.floatValue()+"\n");
    /**@todo Implement this java.lang.Runnable abstract method*/
  }

}