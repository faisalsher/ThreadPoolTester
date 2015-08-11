 package request_simulator_thread_pool_system;


/**
 * <p>Title: Thread Pool System</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class JobCompletionCount {
  private int jobcompleted;
  private int toTalJobsCompleted;
  private int jobsPerPhase;
  public JobCompletionCount(int jobsPerPhase) {
    this.jobsPerPhase=jobsPerPhase;
    this.jobcompleted=0;
    this.toTalJobsCompleted=0;
  }
public  void incrementJobCount(){
 /* if(this.jobcompleted<this.jobsPerPhase)this.jobcompleted++;
  else if(this.jobcompleted==this.jobsPerPhase){

  //call Monitoring component that will examine queue and frequency for dynamic optimization
   this.setJobCountToZero();
  }*/
   this.jobcompleted++;
  // if(this.jobcompleted==this.jobsPerPhase)  this.setJobCountToZero();

  this.toTalJobsCompleted++;
  }
  public  int getJobCount(){
    return this.jobcompleted;
}
public  void setJobCountToZero(){
    this.jobcompleted=0;
    }
    public int getTotalCompletedJobs(){
    return this.toTalJobsCompleted;
    }


}