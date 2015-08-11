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
import javax.swing.*;
public class ThroughputDetector extends TimerTask {
  Pool pool;
  JTextArea jta;
  public ThroughputDetector(Pool pool, JTextArea jta) {
    this.pool=pool;
    this.jta=jta;
    if(pool instanceof ThreadPool) jta.append("\nFBOS Throughput\tFBOS Pool size");
      else  if(pool instanceof WaterMarkThreadPooll) jta.append("\nWatermark Throughput\tWatermark Pool size");
        else  if(pool instanceof HeuristicThreadPool) jta.append("\nHeuristic Throughput\tHeuristic Pool size");
  //  jta.append("\nThroughput\tPool size");
  }
  public void run() {
    jta.append("\n    "+pool.getTotalCompletedJobs()+" \t\t     "+pool.getCurrentWorkers());
    /**@todo Implement this java.lang.Runnable abstract method*/
  }

}