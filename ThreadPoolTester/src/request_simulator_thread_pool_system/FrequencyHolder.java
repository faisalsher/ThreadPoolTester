package request_simulator_thread_pool_system;

/**
 * <p>Title: Thread Pool System</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class FrequencyHolder {
  private int frequency;
//private boolean firstTime=true;
  public FrequencyHolder() {

    frequency=0;
  }
    public synchronized void setFrequency(int frequency){
    this.frequency=frequency;//frequency requests per second
   //  this.jta.append("From Freq holder="+frequency+"\n");
  }
  public synchronized int getFrequency(){
    //if(firstTime){ firstTime=false;return 4;}
   // else
     return this.frequency/1;//because Frequency detector activates after 2 seconds
   }


}