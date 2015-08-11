
package request_simulator_thread_pool_system;
import javax.swing.*;
import java.util.TimerTask;

/**
 * <p>Title: Thread Pool System</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class FrequencyDetector extends TimerTask {
  FrequencyHolder frequencyHolder;
 JTextArea jta;
 Pool threadPool;
 int oldFrequency=0;
 int newFrequency=0;
// private int frequency;
  public FrequencyDetector(Pool threadPool,JTextArea j,FrequencyHolder frequencyHolder) {
    this.frequencyHolder=frequencyHolder;
    jta=j;
   // frequency=0;
    this.threadPool=threadPool;
    if(threadPool instanceof ThreadPool) jta.append("\nFBOS Frequencies\n");
     else  if(threadPool instanceof WaterMarkThreadPooll)jta.append("\nWaterMark Frequencies\n");
       else  if(threadPool instanceof HeuristicThreadPool)jta.append("\nHeuristic Frequencies\n");
jta.append("\n");

  }
  public void run() {
  //  int freq=threadPool.getRequestCounts();
   //  frequencyHolder.setFrequency(freq);
 //following lines would be used in Little FBOS later i have checked it it guves 2 much performance
   int oldFrequency=frequencyHolder.getFrequency();
   int newFrequency=threadPool.getRequestCounts();
   if(newFrequency>oldFrequency){
     this.threadPool.growPoolSize(newFrequency);
   }

   frequencyHolder.setFrequency(threadPool.getRequestCounts());
   this.jta.append("Frequency="+frequencyHolder.getFrequency()+"\n");
    this.threadPool.setfrequencyCountZero();

  }

}