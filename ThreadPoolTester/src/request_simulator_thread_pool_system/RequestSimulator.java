package request_simulator_thread_pool_system;

import javax.swing.UIManager;
import java.awt.*;

/**
 * <p>Title: Thread Pool System</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class RequestSimulator {
  boolean packFrame = false;

  //Construct the application
  public RequestSimulator() {
    MainFrame frame = new MainFrame();
    //Validate frames that have preset sizes
    //Pack frames that have useful preferred size info, e.g. from their layout
    if (packFrame) {
      frame.pack();
    }
    else {
      frame.validate();
    }
    //Center the window
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension frameSize = frame.getSize();
    if (frameSize.height > screenSize.height) {
      frameSize.height = screenSize.height;
    }
    if (frameSize.width > screenSize.width) {
      frameSize.width = screenSize.width;
    }
   // frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
    frame.setSize(screenSize.width-500,screenSize.height-100);
    frame.setVisible(true);
  }
  //Main method
  public static void main(String[] args) {
    try {
     // UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
     //UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
     //UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
     UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
     UIManager.put("ProgressBar.repaintInterval", new Integer(100));
     UIManager.put("ProgressBar.cycleTime", new Integer(3000));



    }
    catch(Exception e) {
      e.printStackTrace();
    }
    new RequestSimulator();
  }
}