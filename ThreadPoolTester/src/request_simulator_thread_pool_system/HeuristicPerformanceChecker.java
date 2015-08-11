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

public class HeuristicPerformanceChecker extends Thread {
Pool threadPool;

  Vector phaseStorer;


   JTextArea jta;
  public HeuristicPerformanceChecker(Thread t, JTextArea jta,Vector phaseStorer,Pool threadPool) {

    this.threadPool=threadPool;
    this.phaseStorer=phaseStorer;

    this.jta=jta;
    try{t.join();}catch(InterruptedException e){}
    }
public void run(){
try{

 if(this.phaseStorer.size()>2){
   Phase currentPhase,previousPhase,prePrePhase;
   synchronized(this.phaseStorer){
     currentPhase = (Phase)this.phaseStorer.elementAt(this.phaseStorer.size() - 1);
     previousPhase = (Phase)this.phaseStorer.elementAt(this.phaseStorer.size() - 2);
     prePrePhase=(Phase)this.phaseStorer.elementAt(this.phaseStorer.size() - 3);
   }
     double prePreAIT=prePrePhase.getAvgWait();
     double preAIT=previousPhase.getAvgWait();
     double currentAIT=currentPhase.getAvgWait();
     double differenceOfAvgWaits=currentAIT-preAIT;

     differenceOfAvgWaits=Math.abs(differenceOfAvgWaits);
          if((differenceOfAvgWaits/preAIT) > .01){
  if(currentAIT>preAIT){
if(preAIT>=prePreAIT)
      this.threadPool.growPoolSize(2);//this.threadPool.getFrequencyHolder().getFrequency());


}
        }//if() if()
      }
  }catch(Exception g){JOptionPane.showMessageDialog(null,"  error66");}
  }

}
