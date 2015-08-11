package request_simulator_thread_pool_system;

/**
 * <p>Title: Thread Pool System</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class FrequencyCreator {
  int evenodd=1;
  int multi=3;
  int a=3;
  public FrequencyCreator() {
  }
public synchronized void incEvenOdd(){evenodd++;}
public synchronized int getEvenOdd(){return evenodd;}
public synchronized void multiBy3(){
  multi=3*a;
  a++;
  }
public synchronized int getMulti(){return multi;}

}