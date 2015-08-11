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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ParentJob {
  protected long delay;
  protected long responseTime;
 protected long inQueueTime;
 protected long deQueueTime;
 protected long totalWait;
 protected long leavingTime;
 protected long entryTime;
 protected long inQMinusEntry;//ie request queue entry time - system ie server entery time
 int number;
 int phaseNumber;
  public ParentJob() {
  }
  public void setNumber(int n){number=n;}
   public void setPhaseNumber(int n){phaseNumber=n;}
  public long totalTime(){
return (leavingTime-inQueueTime);
}
  public long totalOutWaitTime(){//i/o intensity
return (totalTime()-totalWait);
}

  public String toString(){
  String s="";

  s+= "\n Job#"+this.number+" InQueTime="+inQueueTime+" DequeTime="+deQueueTime+" Totalwait="+totalWait+"Leaving time="+leavingTime+" Total time="+totalTime()+"T Time-T weight="+(totalTime()-totalWait);
  return s;
  }
  public void seResponseTime(long time){

    this.responseTime=  time-inQueueTime;
  }
  public long getResponseTime(){

  return this.responseTime;
  }
  public void setEntryTime(long time){ this.entryTime=time;}
 public long getinQMinusEntry(){ return this.inQueueTime-this.entryTime;}
  public void setinQueueTime(long time){
  inQueueTime=time;
 /* Date d = new Date();
              DateFormat df = new SimpleDateFormat("HH:mm:ss:SSS");

                              d.setTime(inQueueTime);*/
                          //System.out.println(name+"  inQueue Time= " + df.format(d));
  }
  public void setDeQueueTime(long time){
  deQueueTime=time;
 /* Date d = new Date();
              DateFormat df = new SimpleDateFormat("HH:mm:ss:SSS");

                              d.setTime(deQueueTime);*/
  totalWait=deQueueTime-inQueueTime;
                          //System.out.println(name+"  DeQueue Time= " + df.format(d));
                         // System.out.println(name+"  Wait Time= " +totalWait);
  }

  public long totalWait(){
  return totalWait;
  }
  public void setLeavingTime(long time){
  leavingTime=time;
 /* Date d = new Date();
              DateFormat df = new SimpleDateFormat("HH:mm:ss:SSS");

                              d.setTime(leavingTime);*/
                          //System.out.println(name+"  Leaving Time= " + df.format(d));
                         // System.out.println(name+"  Total Time="+(leavingTime-inQueueTime));

  }
  public Object[] getObject(){
  final int t=10;
    Object[] o=new Object[t];
    for(int i=0;i<t;i++)
    o[i]=new Object();
   o[0]=""+this.number;
   o[1]=""+this.entryTime;
   o[2]=""+this.getinQMinusEntry();
   o[3]=""+this.inQueueTime;
   o[4]=""+this.deQueueTime;
   o[5]=""+((double)this.totalWait)/1000.0;
   // o[3]=""+this.totalWait;
   o[6]=""+this.leavingTime;
   o[7]=""+((double)totalTime())/1000.0;
  // o[5]=""+totalTime();
  // o[6]=""+(totalTime()-totalWait);
   o[8]=""+((double)(totalTime()-totalWait))/1000.0;
  // o[7]=""+(delay);///1000);//it will print the i/o intensity of job
   o[9]="";
    // o[8]=""+this.getResponseTime();

   return o;
  }
  public long getInQueueTime(){
return this.inQueueTime;
  }
}