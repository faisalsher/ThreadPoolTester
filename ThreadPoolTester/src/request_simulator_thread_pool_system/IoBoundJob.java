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
public class IoBoundJob extends ParentJob implements Runnable {

  JTextArea jta;
  public IoBoundJob(long delay,JTextArea j) {
    this.delay=delay;jta=j;
  }
  public void run() {
    int count=0;
    try{
Thread.currentThread().sleep(this.delay);
        }catch(InterruptedException e){}
setLeavingTime(System.currentTimeMillis());
     }

}