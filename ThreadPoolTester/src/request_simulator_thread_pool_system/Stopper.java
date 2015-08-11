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
import java.util.*;
import javax.swing.*;
public class Stopper extends TimerTask {
  java.util.Timer timer;
  JProgressBar jProgressBar1;
  JButton button;
  public Stopper(java.util.Timer timer,JProgressBar jProgressBar1,JButton button) {
    this.jProgressBar1=jProgressBar1;
    this.timer=timer;
    this.button=button;
  }
  public void run() {
    timer.cancel();
   // button.setEnabled(true);
    jProgressBar1.setIndeterminate(false);
     //  jProgressBar1.setVisible(false);
    /**@todo Implement this java.lang.Runnable abstract method*/
  }

}