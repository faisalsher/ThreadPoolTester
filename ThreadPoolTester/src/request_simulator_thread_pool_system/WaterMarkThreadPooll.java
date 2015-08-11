package request_simulator_thread_pool_system;


/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
public class WaterMarkThreadPooll implements Pool
{  private int highWaterMark=15;

  private WfPerformanceMonitor performanceMonitor;
   // PhaseTabPanel phasePanel;

   DefaultTableModel model;//for phase table
    DefaultTableModel model2;//for job table
    FrequencyHolder frequencyHolder;
    private final int initialThreads;
    private int jobsPerPhase=2;
    private int phaseCount=0;
    JTextArea jta;
    String dummy=null;
    private JobCompletionCount jobCompletionCount;
    private int frequencyCount;
     private Vector workers;
     private final LinkedList queue;
     private LinkedList queueOut;
     public void startInitialThreads(){
     for (int i=0; i<initialThreads; i++) {
       PoolWorker pw=new PoolWorker(this);
       this.workers.addElement(pw);
       pw.start();
     }
     }
     public int getJobVectorCount(){
  return this.performanceMonitor.getJobVectorCount();
  }
  public int getTotalCompletedJobs(){
 return this.jobCompletionCount.getTotalCompletedJobs();
  }
  public  LinkedList getJobQ(){
    synchronized(this.queue){
     return this.queue;
     }}


     public Dump getPerformanceData(){
     return this.performanceMonitor;
     }
     public synchronized int getQueueOutSize(){

return this.queueOut.size();
     }
     public synchronized void growPoolSize(int numberOfThreads){
       for (int i=1; i<=numberOfThreads; i++) {
         PoolWorker pw=new PoolWorker(this);
         synchronized(this.workers){
         this.workers.addElement(pw);
         pw.start();
       }}

     }
     public FrequencyHolder getFrequencyHolder(){
     return this.frequencyHolder;
     }
     public DefaultTableModel getTableModel(){
     return this.model;
     }




       public WaterMarkThreadPooll(int initialThreads,JTextArea jta,FrequencyHolder frequencyHolder,DefaultTableModel model,DefaultTableModel model2)//,PhaseTabPanel phasePanel)
     {

      // this.waitCounter=new WaitCounter(0);
       this.model=model;
        this.model2=model2;
        this.initialThreads = initialThreads;
      //  this.jobsPerPhase=12;
       this.frequencyHolder=frequencyHolder;
       this.performanceMonitor=new WfPerformanceMonitor(this,jta,jobsPerPhase);

         this.frequencyCount=0;
         this.jta=jta;
         this.workers=new Vector(initialThreads,5);

         queue = new LinkedList();
         queueOut = new LinkedList();
         this.jobCompletionCount=new JobCompletionCount(this.jobsPerPhase);
         startInitialThreads();
    }
    public synchronized LinkedList getQueueOut(){
      return this.queueOut;
    }

    public int getCurrentWorkers(){//return pool size
      synchronized(this.workers){
        return this.workers.size();
      }

    }
    public int getCurrentJobs(){
      synchronized(this.queue){
    return this.queue.size();
      }

    }
    public void close(){
    ///
    for (Iterator i = workers.iterator(); i.hasNext(); ) {
            PoolWorker w = (PoolWorker)i.next();
            w.interrupt();
        }
        this.workers.clear();
       this.queue.clear();

    ///

    }

  public synchronized int getRequestCounts(){
  return (this.frequencyCount);
  }
  public synchronized int getPhaseCount(){
  return (this.phaseCount);
  }

  public synchronized void setfrequencyCountZero(){
  this.frequencyCount=0;
  }
public synchronized Vector getWorkerPool(){
  return this.workers;
}
     public synchronized void execute(Runnable r) {
       long Times = System.currentTimeMillis();
       ParentJob job=(ParentJob)r;
       job.setinQueueTime(Times);
       r=(Runnable)job;
       this.frequencyCount++;
       synchronized(queue) {
         //if(this.getCurrentWorkers()< highWaterMark)
            if (this.queue.size() > this.getCurrentWorkers())
                    this.growPoolSize(1);


       queue.addLast(r);

      queue.notify();
   }
                    // this.jta.append("Que size="+queue.size()+"\n");


       }
     private class PoolWorker extends Thread {
       WaterMarkThreadPooll pool;

         public PoolWorker(WaterMarkThreadPooll pool){
           setDaemon(true);
         this.pool=pool;
         }

         public void run() {
             Runnable r;
           //1  synchronized(jta){jta.append(this.getName()+"  exists at index"+pool.getWorkerPool().indexOf(this)+" size of pool="+pool.getCurrentWorkers()+"\n");}
         //  if(pool.getCurrentWorkers()>2 && !queue.isEmpty()){ this.pool.getWaitCounter().incrementCounter();}
             while (true) {
                synchronized (queue) {
                   java.util.Timer timer = null;
                   PoolReducer poolReducer;
                   boolean startTimer = false;

                   while (queue.isEmpty()) {
                     try {
                       /*synchronized(jta){
                          jta.append("I am going to wait\n"+workers.size());
                              }*/

                       if (pool.getCurrentWorkers() > pool.initialThreads)
                         if (! (startTimer)) { //ie if startTimer==false
                           {
                             startTimer = true;
                             timer = new java.util.Timer(true);
                             poolReducer = new PoolReducer(this, this.pool, timer);
                             timer.schedule(poolReducer, 4000);
                           }
                         }
                    //   this.pool.getWaitCounter().incrementCounter();
               //  synchronized( this.pool.jta){      this.pool.jta.append(""+this.pool.getWaitCounter().getCounter()+"\n");}
                       queue.wait();
                     }
                     catch (InterruptedException ignored) {
                       JOptionPane.showMessageDialog(null,"  error2");
                     }
                   }
                   if (startTimer) {
                     timer.cancel();
                     poolReducer = null;
                   }
                 //  this.pool.getWaitCounter().decrementCounter();
                 //   synchronized( this.pool.jta){ this.pool.jta.append(""+this.pool.getWaitCounter().getCounter()+"\n");}
                   r = (Runnable) queue.removeFirst();
                   long Times = System.currentTimeMillis();
                   ParentJob job = (ParentJob) r;
                   job.setDeQueueTime(Times);
                   r = (Runnable) job;

                 }

                 // If we don't catch RuntimeException,
                 // the pool could leak threads
                 try {
                   r.run();
                   synchronized (queueOut) {
                     queueOut.addLast(r);
                       }

                       synchronized (jobCompletionCount) {
                   jobCompletionCount.incrementJobCount(); //it is synchronized method
                   ParentJob job = (ParentJob) r;
                       job.setNumber(jobCompletionCount.getTotalCompletedJobs());
                       job.setPhaseNumber(phaseCount);
                      synchronized (model2){
                      model2.addRow(job.getObject());

                    }


                   if (jobCompletionCount.getJobCount() == jobsPerPhase) {
                      //jta.append("\n Now jobs="+jobCompletionCount.getJobCount());
                     phaseCount++;



                     jobCompletionCount.setJobCountToZero();
                     Thread t = performanceMonitor.calculateXecutionTime();
                  //   performanceMonitor.checkPerformance(t);

                   }
                /*2   synchronized (jta) {
                    jta.append("\nCompleted jobs=" + jobCompletionCount.getJobCount());
                       }*/


                 }
                      /////////////////
                   /*synchronized(queue){
                         jta.append("Queue size="+queue.size());
                          }*/


                 }
                 catch (RuntimeException e) {
                   JOptionPane.showMessageDialog(null,"  error3");
                 }
              //   if(!queue.isEmpty())   this.pool.getWaitCounter().incrementCounter();

             }
         }
         private class PoolReducer extends TimerTask{
           PoolWorker poolWorker;
           WaterMarkThreadPooll pool;
           java.util.Timer timer;
         public PoolReducer(PoolWorker pw,WaterMarkThreadPooll pool,java.util.Timer timer){
           poolWorker=pw;
           this.pool=pool;
           this.timer=timer;
         }
         public void run(){
           String name;int j;
           Vector vector=pool.getWorkerPool();
           synchronized(vector){
             if(vector.size()>pool.initialThreads){
          /*3   synchronized(pool.jta){
               pool.jta.append(poolWorker.getName()+"exists at index"+vector.indexOf(poolWorker)+" size of pool="+vector.size());//pool.getCurrentWorkers()+"\n");
             }*/

             name=poolWorker.getName();
             j=vector.indexOf(poolWorker);
          //   this.pool.getWaitCounter().decrementCounter();
             vector.removeElementAt(j);
          /*4   synchronized(pool.jta){
               pool.jta.append("\n"+name+ " has been removed from  location "+j);
               pool.jta.append(" size of pool="+vector.size()+"\n");
             }*/

         try{
           poolWorker.stop();
         }catch(Exception e){}

           }}//////////
this.timer.cancel();

         }

       }
     }

}
