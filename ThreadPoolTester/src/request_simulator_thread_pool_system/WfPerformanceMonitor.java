package request_simulator_thread_pool_system;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import java.util.*;

/**
 * <p>Title: Thread Pool System</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */
import javax.swing.*;
public class WfPerformanceMonitor extends Dump {
  int phase;
  int count;
  Vector phaseStorer;
  private  int jobsPerPhase;

  Vector jobsHistoryStorer;
  Pool threadPool;
  JTextArea jta;
  boolean CpuBoundBehavior=false;

  public WfPerformanceMonitor(Pool pool,JTextArea jta,int jobsPerPhase) {

    count=0;
    this.jobsPerPhase=jobsPerPhase;

    this.jta=jta;
    this.threadPool=pool;
    phase=0;
    this.phaseStorer= new Vector(20,5);//initial capacity , increment
    jobsHistoryStorer=new Vector(30,5);
    this.phaseStorer.add(new Phase());//initially store a dummy phase at 0 posistion of Vector

  }
  public int getJobVectorCount(){
  return this.jobsHistoryStorer.capacity();
  }

  public void makeCpuBoundBehaviorTrue(){
  this.CpuBoundBehavior=true;
  }
  public void makeCpuBoundBehaviorFalse(){
    this.CpuBoundBehavior=false;
    }
    public boolean getCpuBoundBehavior(){
    return this.CpuBoundBehavior;
  }

  public void setCountToZero(){
  this.count=0;
  }
  public int getCount(){
  return this.count;
  }
  public Thread calculateXecutionTime(){
    this.count++;
  WfTotalExecutionTimeCalculator thread=new WfTotalExecutionTimeCalculator(this.threadPool.getQueueOut(),this.phaseStorer,jobsHistoryStorer,this.threadPool.getCurrentWorkers(),this.jta,jobsPerPhase,this.threadPool,count);
//***********************************************************
 // if(count==20) count=0;
  thread.start();
  return thread;
  }
  public Vector getPhases(){
  return this.phaseStorer;
  }
  public void checkPerformance(Thread t){
//WfPerformanceChecker checker=new WfPerformanceChecker(t,jta,this.phaseStorer,this.threadPool);
  //  checker.start();

  }
  public Vector getJobsHistory(){
    return this.jobsHistoryStorer;
    }
    public Vector getPhaseHistory(){
    return this.phaseStorer;
    }


}
