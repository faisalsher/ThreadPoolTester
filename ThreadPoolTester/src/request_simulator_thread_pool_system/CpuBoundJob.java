// this class is of no use
package request_simulator_thread_pool_system;
import javax.swing.*;
/**
 * <p>Title: Thread Pool System</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CpuBoundJob extends ParentJob implements Runnable {// this class is of no use
  ////////////


int count;
//Thread t;
JTextArea jta;
public CpuBoundJob(JTextArea j){
//t=new Thread(this);
jta=j;
//t.start();
}



public void run(){

for(int i=3;i<40000;i++) //i<=55000

 {
int  n=i;
boolean prime=true;
for(int j=2;j<=n-1;j++)
{
if(n%j==0){prime=false;break;}
}
if(prime==true){
count++;

}
  // if(i==20000) try{Thread.sleep(300);}catch(InterruptedException e){}
}
/*synchronized(jta){
   jta.append(Thread.currentThread().getName()+" total primes="+count+"\n");
}*/
setLeavingTime(System.currentTimeMillis());
}

}