package request_simulator_thread_pool_system;

import java.util.*;
import javax.swing.*;
/**
 * <p>Title: Thread Pool System</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class RequestSender extends TimerTask {
 // private PhaseCounter
 boolean trick=false;
// int oddn=;
   private LinkedList myStrategyJobs;
  JTextArea jta;
 // IoBoundJob[] jobs=new IoBoundJob[1000];
  JSlider slider;
  boolean ioCpuRadioCheck;
  Pool threadPool;
  int noOfRequests;
  int requestFrequencies[]={8,4,4,4,5,9,7,14,4,10,11,10,10,12,12,2,2,3,2,1,1,2,3,2,0,2,0,2,0,3,1,1,1,1,1,1,1,5,4,10,8,10,10,10,12,3,3,4,4,3,4,11,6,12,8,20,10,20,11,20,12,20,11,12,12};
  int counter=0;
  int phaseNo;
  FrequencyCreator multi;
  public RequestSender(FrequencyCreator multi,LinkedList myStrategyJobs,JSlider slider,Pool threadPool,JTextArea j,boolean ioCpuRadioCheck) {
   this.multi=multi;
    this.myStrategyJobs=myStrategyJobs;
    this.noOfRequests=3;
    this.slider=slider;
   this.threadPool=threadPool;
    jta=j;
   // jta.append("\n phase#"+phaseNo);
    this.ioCpuRadioCheck=ioCpuRadioCheck;

  }
  public void run() {
    /*for(int i=1;i<=(this.requestFrequencies[counter]);i++)
              threadPool.execute((IoBoundJob)this.myStrategyJobs.removeFirst());
              counter++;*/

  if(((multi.getEvenOdd()%2)!=0)) //ie if odd
this.noOfRequests=8;
  else this.noOfRequests=multi.getMulti();
     try{
        if(ioCpuRadioCheck==true){  //ie if Io radiobutton is checked
    //  for(int i=1;i<=(this.noOfRequests);i++){
         for(int i=1;i<=(4);i++){
        long Times = System.currentTimeMillis();

        // long Times=System.nanoTime();
        ParentJob job=(ParentJob)this.myStrategyJobs.removeFirst();
       job.setEntryTime(Times);
      // IoBoundJob job=(IoBoundJob)this.myStrategyJobs.removeFirst();
              threadPool.execute((IoBoundJob)job);

    }}

  }catch(Exception e){
  JOptionPane.showMessageDialog(null,"  error6");
  }
  }

}