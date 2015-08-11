package request_simulator_thread_pool_system;

/**
 * <p>Title: Thread Pool System</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class Phase {
  int index;
  int poolSize;
  int totalJobs;
  long totalXcutionTime;
  long totalOutOfWaitTime;
   double TTATinSec;
   double TTWOWinSec;
   double TotalWaitInSec;
   double AvgWait;
   double TTATInAverage;
  long totalWait;
  int jobQSize;
  int jobsPerPhase;
  public Phase() {
    this.poolSize=0;
    this.totalJobs=10;
    this.totalXcutionTime=9999999;
    this.totalOutOfWaitTime=9999999;
    this.totalWait=0;
    jobQSize=0;
    this.index=-1;
    this.AvgWait=0.0;


  }
  public Phase(int poolSize,long totalXTime,long totalOutOfWaitTime,long totalWait,int jobQSize,int jobsPerPhase){
    this.jobsPerPhase=jobsPerPhase;
  this.poolSize=poolSize;
  this.totalXcutionTime=totalXTime;
  this.totalOutOfWaitTime=totalOutOfWaitTime;
  this.totalWait=totalWait;
  TTATinSec=((double)totalXcutionTime)/1000.0;//TTAT in seconds(ms to sec)
  TTWOWinSec=((double)totalOutOfWaitTime)/1000.0;
  TotalWaitInSec=(double)this.totalWait/1000.0;//wait time  in seconds
  AvgWait=TotalWaitInSec/this.jobsPerPhase;//avg wait time  in seconds
 TTATInAverage=TTATinSec-TotalWaitInSec+AvgWait;
  this.totalJobs=10;
  this.jobQSize=jobQSize;
   this.index=-1;
  }
  public double getAvgWait(){
  return this.AvgWait;
  }
  public int getPoolSize(){return poolSize;}
  public int getTotalJobs(){return totalJobs;}
 // public double getTotalXcutionTime(){return TTATInAverage; }
  public double getTotalXcutionTime(){return TTATinSec; }
  public double getTotalOutOfWaitTime(){return  this.TTWOWinSec; }
  public int getJobQSize(){return this.jobQSize;}
  public String toString(){
    return "Pool Size="+poolSize+" TotalXcutionTime"+totalXcutionTime+" job Que Size="+this.jobQSize+" index="+this.getIndex() ;

  }
  public Object[] getObject(){
   final int t=8;
  Object[] o=new Object[t];
  for(int i=0;i<t;i++)
  o[i]=new Object();
  o[0]=""+getIndex();
  o[1]=""+getPoolSize();
  o[2]=""+getTotalXcutionTime();
 // o[2]=""+((double)getTotalXcutionTime())/1000.0;
 Double d=new Double(TTATInAverage);
// o[2]=""+d.floatValue();//for ignoring extra decimal places
 d=null;

 // o[3]=""+getTotalOutOfWaitTime();
  o[3]=""+TTWOWinSec;
 // o[4]=""+this.totalWait;
  //o[4]=""+(((double)this.totalWait)/1000.0);
   Double e=new Double(AvgWait*1000);//Since Avg wait is in sec so we will convert it into msec by multiplying it with 1000;
 o[4]=""+(e.floatValue());// average wait time of  jobs in the waiting queue
e=null;
  o[5]=""+getJobQSize();

//  o[6]=""+getThroughput();
   o[6]="";

// o[7]=""+performanceGain();
 o[7]="";


return o;
  }
  private double getThroughput(){
return ((60*this.jobsPerPhase)/TTATInAverage);// 163256(total execution time/20 jobs=60 sec/x? jobs[ie how many jobs in 1 minute])

  }
  private double performanceGain(){
//  return ((100*getThroughput())/getTotalOutOfWaitTime());
 return this.getTotalOutOfWaitTime()*100/this.getTotalXcutionTime();
  }
  public void setIndex(int index){this.index=index;}
  public int getIndex(){return this.index;}
}
