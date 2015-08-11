package request_simulator_thread_pool_system;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ThroughputDetector2 extends Timer implements ActionListener {
       // NonblockingCounter throughput;

        public ThroughputDetector2(int interval) {
                super(interval,null);
             //   this.throughput= throughput;

        }

        /**
         * @param args
         */
        public static void main(String[] args) {
                // TODO Auto-generated method stub

        }

        public void actionPerformed(ActionEvent e){
            //    this.throughput.getValue();

        }

}
