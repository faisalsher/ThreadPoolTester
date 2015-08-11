package request_simulator_thread_pool_system;

import java.util.TimerTask;

/**
 * <p>Title: Thread Pool System</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class MultiTimer extends TimerTask {
  FrequencyCreator multi;
  public MultiTimer(FrequencyCreator multi) {
    this.multi=multi;
  }
  public void run() {
    /**@todo Implement this java.lang.Runnable abstract method*/
    multi.incEvenOdd();
    if((multi.getEvenOdd()%2)==0){  //ie if even
      multi.multiBy3();
  }
  }

}