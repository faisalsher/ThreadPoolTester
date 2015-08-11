package request_simulator_thread_pool_system;
import  java.util.*;

/**
 * <p>Title: Thread Pool System</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */
import javax.swing.table.*;
public interface Pool {
final int jobsPerPhase=10;
public abstract void execute(Runnable r);
  // public abstract PerformanceMonitor getPerformanceData();
public abstract int getCurrentWorkers();
public abstract int getQueueOutSize();
public abstract int getTotalCompletedJobs();
public abstract int getRequestCounts();
public abstract void setfrequencyCountZero();
public abstract LinkedList getQueueOut();
public abstract int getCurrentJobs();
public abstract DefaultTableModel getTableModel();
public abstract FrequencyHolder getFrequencyHolder();
public abstract void growPoolSize(int a);
public abstract Dump getPerformanceData();



  public abstract LinkedList getJobQ();
 // public void shrinkPoolSize(int q);


   }