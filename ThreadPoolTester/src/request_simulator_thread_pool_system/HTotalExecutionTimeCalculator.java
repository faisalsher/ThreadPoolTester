package request_simulator_thread_pool_system;

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
public class HTotalExecutionTimeCalculator extends Thread {
   JTextArea jta;
  Pool threadPool;
   private final int jobsPerPhase;
  LinkedList queueOut;
  Vector phaseStorer;
  Vector jobsHistoryStorer;

  int count;
  int poolSize;
  public HTotalExecutionTimeCalculator(LinkedList queueOut,Vector phaseStorer,Vector jobsHistoryStorer,int poolSize, JTextArea jta,int jobsPerPhase,Pool threadPool,int count) {
    this.count=count;
    this.threadPool=threadPool;
    this.jta=jta;
    this.jobsPerPhase=jobsPerPhase;
    this.queueOut=queueOut;
    this.phaseStorer=phaseStorer;
    this.jobsHistoryStorer=jobsHistoryStorer;
    this.poolSize=poolSize;
    //start();
  }

  public void run(){
    try{
   int jobsInQ= this.threadPool.getCurrentJobs();
      long totalXcutionTime=0;
      long totalOutOfWaitTime=0;
    //synchronized(this.jta){jta.append("\nExecutionTimeCalculator ......");}
    int count=1;
     synchronized(this.queueOut){
    while(count++<=jobsPerPhase){

    Runnable r=(Runnable)this.queueOut.removeFirst();
    ParentJob job=(ParentJob)r;
  //  int number=jobsHistoryStorer.size();
   // job.setNumber(number);
    //ParentJob job2=job;
    jobsHistoryStorer.add(job);
    totalXcutionTime+=job.totalTime();
    totalOutOfWaitTime+=job.totalOutWaitTime();
    r=null;
    job=null;
    }
  }

  Phase p=new Phase(poolSize,totalXcutionTime,totalOutOfWaitTime,totalXcutionTime-totalOutOfWaitTime,jobsInQ,this.jobsPerPhase);
  p.setIndex(this.count);
this.threadPool.getTableModel().addRow(p.getObject());
  synchronized(this.phaseStorer){
    phaseStorer.add(p);
  }
 /*1 synchronized(this.jta){
  jta.append("\nPool size="+p.getPoolSize()+" TotalXTime="+p.getTotalXcutionTime());
  jta.append("\nPool size="+p.getPoolSize()+" TotalX Out Wait Time="+p.getTotalOutOfWaitTime());
  //jta.append("\nExecutionTimeCalculator ......");
  }*/

  }catch(Exception e){
   //2 jta.append("\n Queueout size="+this.queueOut.size());
    JOptionPane.showMessageDialog(null,"  error 77");
    e.printStackTrace();}
  }////run

}
