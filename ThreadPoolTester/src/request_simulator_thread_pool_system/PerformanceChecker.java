package request_simulator_thread_pool_system;

/**
 * <p>Title: Thread Pool System</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */
import javax.swing.*;
import java.util.*;

public class PerformanceChecker extends Thread {
ThreadPool threadPool;

  Vector phaseStorer;
boolean ft=true;

   JTextArea jta;
  public PerformanceChecker(Thread t, JTextArea jta,Vector phaseStorer,ThreadPool threadPool) {

    this.threadPool=threadPool;
    this.phaseStorer=phaseStorer;

    this.jta=jta;
    try{t.join();}catch(InterruptedException e){}
    }
public void run(){
try{
    Phase currentPhase;
   synchronized(this.phaseStorer){
      currentPhase = (Phase)this.phaseStorer.elementAt(this.phaseStorer.size() - 1);
     }
double TTAT=currentPhase.getTotalXcutionTime();
double TTWOW=currentPhase.getTotalOutOfWaitTime();
if(TTAT >TTWOW )

   {
     int frequency=this.threadPool.getFrequencyHolder().getFrequency();
      this.threadPool.growPoolSize(frequency);

      }
    }catch(Exception g){JOptionPane.showMessageDialog(null,"  error66");}
  }

}