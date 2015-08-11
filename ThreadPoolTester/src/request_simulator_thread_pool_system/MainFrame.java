package request_simulator_thread_pool_system;



import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import java.util.*;
//import com.borland.jbcl.layout.*;
import javax.swing.table.*;

/**
 * <p>Title: Thread Pool System</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class MainFrame extends JFrame {

 FrequencyCreator multi;
  JPanel contentPane;
  BorderLayout borderLayout1 = new BorderLayout();
  TitledBorder titledBorder1;
  TitledBorder titledBorder2;
  JPanel jPanel1 = new JPanel();
  TitledBorder titledBorder3;
  JPanel requestTypePanel = new JPanel();
  JPanel requestFrequencyPanel = new JPanel();
  JRadioButton ioRadioButton = new JRadioButton("I/O Bound",true);
  JRadioButton cpuRadioButton = new JRadioButton();
  boolean ioCpuRadioCheck=true;
  ioCpuRequestRadioHandler handler=new ioCpuRequestRadioHandler();
  ButtonGroup requestRadioGroup=new ButtonGroup();
  static final int MIN = 1;
  static final int MAX = 3;
  static final int INIT = 2;    //initial frames per second

  JSlider ioSlider = new JSlider(JSlider.HORIZONTAL,MIN,MAX,INIT);
 // VerticalFlowLayout verticalFlowLayout2 = new VerticalFlowLayout();
  JSlider frequencySlider = new JSlider(1,10,1);
  int frequencySlidercount=6;
  JPanel frequencyPanel = new JPanel();
  FlowLayout flowLayout2 = new FlowLayout(FlowLayout.CENTER);
 // VerticalFlowLayout verticalFlowLayoutButtonProgressbarPanel = new VerticalFlowLayout();
  //JPanel buttonProgressbarPanel=new JPanel(verticalFlowLayoutButtonProgressbarPanel);

  JPanel buttonPanel = new JPanel();
  JButton startButton = new JButton();
  FlowLayout flowLayout3 = new FlowLayout();
  JPanel centerPanel = new JPanel();
  TitledBorder titledBorder4;
   JTextArea jta=new JTextArea();
   JTextArea jta2=new JTextArea();
  JScrollPane jScrollPane1 = new JScrollPane(jta);
  JScrollPane jScrollPanel2 = new JScrollPane(jta2);
  JTextArea jtaq=new JTextArea();
  JScrollPane jScrollPane1q = new JScrollPane(jtaq);

  JTextArea jta6=new JTextArea();
  JScrollPane jScrollPanel6 = new JScrollPane(jta6);
FrequencyTabPanel frequencyTabPanel=new FrequencyTabPanel();
  ThreadPoolTabPanel threadPoolTabPanel=new ThreadPoolTabPanel();
  ResponseTabPanel responseTabPanel=new ResponseTabPanel();
  GraphTabPanel graphTabPanel=new GraphTabPanel();
  DefaultTableModel model = new DefaultTableModel();//Phase model
  JTable jTable;
  DefaultTableModel model2 = new DefaultTableModel();//job model
  JTable jTable2;

  PhaseTabPanel phasePanel=new PhaseTabPanel();
  JobTabPanel jobPanel=new JobTabPanel();
  JTabbedPane tabbedPane=new JTabbedPane();
  BorderLayout borderLayout2 = new BorderLayout();

  StartStopButtonListener  startStopButtonListener;
  boolean startstopButtonsTester=false;//
  ThreadPool threadPool;
  Pool pool;
  FrequencyHolder frequencyHolder;
  JPanel jPanel2 = new JPanel();
  JProgressBar jProgressBar1 = new JProgressBar();
  TitledBorder titledBorder5;
   final int reservejobs=3000;
   LinkedList strategyJobs;
  LinkedList myStrategyJobs=new LinkedList();
   LinkedList xuStrategyJobs;
   LinkedList wfStrategyJobs;
   LinkedList fbosStrategyJobs;
  JRadioButton Xu = new JRadioButton();
  ButtonGroup xuwfGroup=new ButtonGroup();
  JRadioButton wf = new JRadioButton();
  JRadioButton fbos = new JRadioButton();
  JRadioButton ghazala = new JRadioButton("NBFBOS By Ghazala");
  JRadioButton summat = new JRadioButton("HFBOS By Summat");
  JRadioButton sehrish = new JRadioButton("DFBOS By Sehrish");
  JRadioButton shahid = new JRadioButton("RFBOS By Shahid");
  JRadioButton sheraz = new JRadioButton("LDFBOS By Sheraz");
  JRadioButton tayaba = new JRadioButton("LFBOS By Tayaba");
 XuwfRadioHandler xuwfRadioHandler;
  FlowLayout flowLayout4 = new FlowLayout();
  BorderLayout borderLayout3 = new BorderLayout();
  GridLayout gridLayout1 = new GridLayout(3,3);
 // RequestSender requestSender=new RequestSender(jta);

  //Construct the frame
  public MainFrame() {
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  private void setRadioSettings(JRadioButton jrb){
jrb.setBackground(new Color(169, 198, 236));
     jrb.setForeground(Color.black);
     jrb.setPreferredSize(new Dimension(150, 23));
     jrb.setFocusPainted(true);
   //  fbos.setSelected(true);


}

  public LinkedList getReserverJobs(){
  return this.myStrategyJobs;
}
public DefaultTableModel getJobModel(){//get job model
return this.model2;
}
public DefaultTableModel getPhaseModel(){//get phase model
return this.model;
}

  private void createJobs(){
 for(int i=0;i<reservejobs;i++){
 int a= 1+(int) (Math.random()*2);
 this.myStrategyJobs.addLast(new IoBoundJob((long)(2000),this.jta));
 //this.myStrategyJobs.addLast(new IoBoundJob((long)(a*500),this.jta));
 // this.myStrategyJobs.addLast(new IoBoundJob((long)(1000),this.jta));
//this.myStrategyJobs.addLast(new IoBoundJob((long)(500),this.jta));
 }

 this.xuStrategyJobs=(LinkedList)this.myStrategyJobs.clone();
 this.wfStrategyJobs=(LinkedList)this.myStrategyJobs.clone();
 this.fbosStrategyJobs=(LinkedList)this.myStrategyJobs.clone();
 this.strategyJobs=(LinkedList)this.myStrategyJobs.clone();

// this.xuStrategyJobs=(LinkedList)this.myStrategyJobs.clone();
// this.strategyJobs=(LinkedList)this.myStrategyJobs.clone();
  }
  //Component initialization
  private void jbInit() throws Exception  {
    try{
   createJobs();
    frequencyHolder=new FrequencyHolder();
    titledBorder4 = new TitledBorder("");
    titledBorder5 = new TitledBorder("");
      requestTypePanel.setBackground(new Color(169, 198, 236));
     // requestTypePanel.setBackground();

    requestFrequencyPanel.setBackground(new Color(169, 198, 236));
    requestFrequencyPanel.setMinimumSize(new Dimension(113, 97));
    ioRadioButton.setBackground(SystemColor.window);
    ioSlider.setBackground(SystemColor.window);
    cpuRadioButton.setBackground(SystemColor.window);
    frequencyPanel.setBackground(new Color(169, 198, 236));
    frequencyPanel.setPreferredSize(new Dimension(10, 28));
    frequencyPanel.setLayout(flowLayout2);
    frequencySlider.setBackground(new Color(169, 198, 221));
    //  frequencySlider.setEnabled(false);
    frequencySlider.setMajorTickSpacing(1);
    frequencySlider.setPaintTicks(true);
   // frequencySlider.setPaintLabels(true);
    frequencySlider.setSnapToTicks(true);
   frequencySlider.addChangeListener(new ChangeListener(){
      public void stateChanged(ChangeEvent e){
        if(frequencySlider.getValue()==1){
      //  frequencyTextField2.setText("2");
      //  frequencyTextField1.setText("1");
      //  frequencyLabel.setText("Request  after every");
        }

      else{
     //   if(frequencySlider.getValue()==2) frequencyLabel.setText("Request  after every");
    //    else frequencyLabel.setText("Requests after every");
      //  frequencyTextField1.setText((frequencySlider.getValue()-1)+"");// Ken Oay
    //    frequencyTextField1.setText((frequencySlider.getValue()-1)*3+"");

         //    frequencyTextField2.setText("1");
}
    }
    });


  //  buttonProgressbarPanel.setBackground(SystemColor.inactiveCaptionText);
   // buttonProgressbarPanel.setPreferredSize(new Dimension(400, 35));
    buttonPanel.setBackground(new Color(169, 198, 236));
    buttonPanel.setPreferredSize(new Dimension(674, 35));
    buttonPanel.setLayout(flowLayout3);
    startButton.setBackground(Color.white);
    startButton.setText("START");
    startStopButtonListener=new StartStopButtonListener();
    startButton.addActionListener(startStopButtonListener);
    //startButton.addActionListener();
    centerPanel.setBackground(SystemColor.window);
    centerPanel.setBorder(BorderFactory.createEtchedBorder());
    centerPanel.setLayout(borderLayout2);

    DisplayButtonListener displayButtonListener=new DisplayButtonListener();
    jPanel2.setBackground(new Color(169, 198, 236));
      jPanel2.setPreferredSize(new Dimension(382, 20));
      jProgressBar1.setBackground(Color.white);
      jProgressBar1.setForeground(SystemColor.activeCaption);
      jProgressBar1.setBorder(BorderFactory.createLoweredBevelBorder());
      jProgressBar1.setVisible(false);
      jProgressBar1.setPreferredSize(new Dimension(150, 14));
      xuwfRadioHandler=new XuwfRadioHandler(this);


      Xu.addItemListener(this.xuwfRadioHandler);
      wf.addItemListener(this.xuwfRadioHandler);
      fbos.addItemListener(this.xuwfRadioHandler);
      fbos.setBackground(new Color(169, 198, 236));
      fbos.setForeground(Color.black);
      fbos.setPreferredSize(new Dimension(100, 23));
      fbos.setFocusPainted(true);
      fbos.setSelected(true);
      fbos.addActionListener(new MainFrame_fbos_actionAdapter(this));
      setRadioSettings(ghazala);
      setRadioSettings(summat);
      setRadioSettings(sehrish);
      setRadioSettings(sheraz);
      setRadioSettings(shahid);
      setRadioSettings(tayaba);
     // fbos.setRequestFocusEnabled(true);
     Xu.setEnabled(true);
      xuwfGroup.add(fbos);
      xuwfGroup.add(Xu);
        xuwfGroup.add(wf);


      Xu.setBackground(new Color(169, 198, 236));
      Xu.setForeground(Color.black);
      Xu.setMaximumSize(new Dimension(23, 23));
      Xu.setMinimumSize(new Dimension(23, 23));
      Xu.setPreferredSize(new Dimension(100, 23));
      Xu.setToolTipText("");
      Xu.setText("Heuristic By Xu");
      wf.setBackground(new Color(169, 198, 236));
      wf.setPreferredSize(new Dimension(100, 23));
      wf.setActionCommand("wf");
      wf.setText("Watermark By Oracle");
      fbos.setText("FBOS By Bahadur");
    this.requestRadioGroup.add(this.ioRadioButton);
    this.requestRadioGroup.add(this.cpuRadioButton);
    this.ioRadioButton.addItemListener(this.handler);
    this.cpuRadioButton.addItemListener(this.handler);
    contentPane = (JPanel) this.getContentPane();

    contentPane.setBackground(SystemColor.inactiveCaptionText);
       contentPane.setEnabled(false);
       contentPane.setAlignmentY((float) 0.5);
       contentPane.setBorder(BorderFactory.createEtchedBorder());
       contentPane.setLayout(borderLayout1);
      // requestTypePanel.setMinimumSize(new Dimension(10, 10));
    requestTypePanel.setPreferredSize(new Dimension(674, 115));
    requestTypePanel.setLayout(gridLayout1);
    requestTypePanel.setBorder(BorderFactory.createTitledBorder("Server Thread Pool"));
    requestFrequencyPanel.setPreferredSize(new Dimension(674, 98));
    requestFrequencyPanel.setLayout(borderLayout3);
    requestFrequencyPanel.setBorder(BorderFactory.createTitledBorder("Request Frequency"));
    //ioRadioButton.setText("I/O Bound");
    cpuRadioButton.setText("CPU Bound");

    ioSlider.setBorder(BorderFactory.createTitledBorder("I/O Intensity"));
    ioSlider.setMajorTickSpacing(1);


    Hashtable labelTable = new Hashtable();
    labelTable.put( new Integer(1), new JLabel("L") );
    labelTable.put( new Integer(2), new JLabel("M") );
    labelTable.put( new Integer(3), new JLabel("H") );
    ioSlider.setLabelTable( labelTable );
   // ioSlider.setPaintLabels(true);
    ioSlider.setOpaque(true);
    ioSlider.setPreferredSize(new Dimension(80, 50));
    ioSlider.setSnapToTicks(true);
    ioSlider.addChangeListener(new ChangeListener(){
      public void stateChanged(ChangeEvent e){

      }
    });


    ////




        ////


    jPanel1.add(requestTypePanel, null);
    //requestTypePanel.add(ioRadioButton, null);
   // requestTypePanel.add(ioSlider, null);
   // requestTypePanel.add(cpuRadioButton, null);
    jPanel1.add(requestFrequencyPanel, null);
    requestFrequencyPanel.add(frequencySlider, BorderLayout.NORTH);
    requestFrequencyPanel.add(frequencyPanel, BorderLayout.CENTER);
    contentPane.add(jPanel1, BorderLayout.NORTH);
  //  buttonProgressbarPanel.add(buttonPanel, null);
  //   buttonProgressbarPanel.add(new JTextField(), null);

    jPanel1.add(buttonPanel, null);
    buttonPanel.add(startButton, null);

      jPanel2.add(jProgressBar1, null);
       jPanel1.add(jPanel2, null);



    titledBorder1 = new TitledBorder("");
    titledBorder2 = new TitledBorder("");
    titledBorder3 = new TitledBorder("");

    this.setSize(new Dimension(400, 500));

    this.setTitle("ThreadPoolTester");
    jPanel1.setBackground(new Color(169, 198, 236));
    jPanel1.setBorder(BorderFactory.createEtchedBorder());
    jPanel1.setPreferredSize(new Dimension(20, 265));
    jPanel1.setLayout(flowLayout4);
    tabbedPane.setBackground(SystemColor.window);
    this.tabbedPane.addTab("Average waits",this.threadPoolTabPanel);
    this.tabbedPane.addTab("ResponseTime",this.responseTabPanel);
     this.tabbedPane.addTab("Frequencies",this.frequencyTabPanel);
    this.tabbedPane.addTab("Throughput",this.graphTabPanel);
    this.tabbedPane.addTab("Phase Info",this.phasePanel);
    this.tabbedPane.addTab("Jobs Info",this.jobPanel);

    this.centerPanel.add(this.tabbedPane,BorderLayout.CENTER);
    contentPane.add(centerPanel, BorderLayout.CENTER);
    requestTypePanel.add(fbos, null);
      requestTypePanel.add(Xu, null);
    int processors = Runtime.getRuntime().availableProcessors();
//
    // this.threadPool=new ThreadPool(processors*2,this.jta,this.frequencyHolder,this.model,this.model2);

     this.multi=new FrequencyCreator();
     this.threadPool=new ThreadPool(2,this.jta,this.frequencyHolder,this.model,this.model2);
     pool=this.threadPool;
    this.jta.append("\nTotal processors="+processors+"\n");
      requestTypePanel.add(wf, null);
      requestTypePanel.add(ghazala, null);
      requestTypePanel.add(summat, null);
      requestTypePanel.add(sehrish, null);
      requestTypePanel.add(sheraz, null);
      requestTypePanel.add(shahid, null);
      requestTypePanel.add(tayaba, null);

 }catch(Exception e){
JOptionPane.showMessageDialog(null,"  error4444");
}

 }
public void setAllNull(){
   this.pool=null;

   this.multi=null;
 this.threadPool=null;
 this.frequencyHolder=null;
 this.strategyJobs=null;

 }
  //Overridden so we can exit when window is closed
  protected void processWindowEvent(WindowEvent e) {
    super.processWindowEvent(e);
    if (e.getID() == WindowEvent.WINDOW_CLOSING) {
      System.exit(0);
    }
  }
 private class ioCpuRequestRadioHandler implements ItemListener {
    public void itemStateChanged(ItemEvent e) {

     if(e.getSource()==ioRadioButton) {
       ioSlider.enable(true);ioCpuRadioCheck=true;
     }
       else if(e.getSource()==cpuRadioButton) {
         ioSlider.enable(false);ioCpuRadioCheck=false;
       }
    }
  }
  private class ThreadPoolTabPanel extends JPanel{
   ThreadPoolTabPanel(){
   setBackground(SystemColor.window);
   this.setLayout(new BorderLayout());
   add(jScrollPane1,BorderLayout.CENTER);
   //add();
  }
}
private class ResponseTabPanel extends JPanel{
   ResponseTabPanel(){
   setBackground(SystemColor.window);
   this.setLayout(new BorderLayout());
   add(jScrollPanel6,BorderLayout.CENTER);
   //add();
  }
}

private class FrequencyTabPanel extends JPanel{
  FrequencyTabPanel(){
  setBackground(SystemColor.window);
  this.setLayout(new BorderLayout());
  add(jScrollPane1q,BorderLayout.CENTER);
  //add();
 }
}

private class GraphTabPanel extends JPanel{
   GraphTabPanel(){
   setBackground(SystemColor.window);
   this.setLayout(new BorderLayout());
   this.add(jScrollPanel2,BorderLayout.CENTER);
  }
}
private class PhaseTabPanel extends JPanel{

   PhaseTabPanel(){
   setBackground(SystemColor.window);
    this.setLayout(new BorderLayout());
     prepareTable();
     JScrollPane pane = new JScrollPane(jTable,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
     jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
   //  pane.seth
     add(jTable.getTableHeader(), BorderLayout.PAGE_START);
     add(pane,BorderLayout.CENTER);
  }

  private void prepareTable(){
    model.addColumn("Phase#");
      model.addColumn("Pool Size");
      model.addColumn("TTAT(Sec)");
      model.addColumn("TTWOW(Sec)");
      model.addColumn("Avg wait Time(msec)");
      model.addColumn("Job Queue Size");
      model.addColumn("Throughput(Jobs /minute)");
      model.addColumn("Performance");
      jTable=new JTable(model);
      jTable.setBorder(BorderFactory.createEtchedBorder());
       //jTable.setBackground(SystemColor.inactiveCaptionText);
      JTableHeader header = jTable.getTableHeader();
    header.setBackground(SystemColor.window);
      DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
          dtcr.setHorizontalAlignment(SwingConstants.CENTER);
          TableColumn column = null;
          column = jTable.getColumnModel().getColumn(0);
       column.setCellRenderer(dtcr);
      column.setPreferredWidth(55);
      column = jTable.getColumnModel().getColumn(1);
       column.setCellRenderer(dtcr);
      column.setPreferredWidth(55);


  for (int i = 2; i < 8; i++) {
      column = jTable.getColumnModel().getColumn(i);
       column.setCellRenderer(dtcr);
      column.setPreferredWidth(95);
}
    /*column = jTable.getColumnModel().getColumn(5);
       column.setCellRenderer(dtcr);
      column.setPreferredWidth(100);*/

     //
 /* Object[] rowData = {"vvvvvvvvv","1236","dy","xdfg","sgdwretjertjertjrewtjertj"};
      Object[] rowData2 = {"vvvvvvvvv","1236","dy","xdfg","sgdfgjrtjerjertjertjertjrwtjertjertj"};
      model.insertRow(0,rowData);
      for(int i=1;i<23;i++)
      model.addRow(rowData2);
*/


  }
}

private class JobTabPanel extends JPanel{

   JobTabPanel(){
     //super("Information of Completed Requests");

   setBackground(SystemColor.window);

    this.setLayout(new BorderLayout());
     prepareTable();
     JScrollPane pane2 = new JScrollPane(jTable2,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    jTable2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
     pane2.setBackground(SystemColor.window);
     add(jTable2.getTableHeader(), BorderLayout.PAGE_START);
     add(pane2,BorderLayout.CENTER);
  }
  private void prepareTable(){
    model2.addColumn("Job#");
    model2.addColumn("Entery time");
    model2.addColumn("InQMInusEntry");

    model2.addColumn("Inqueue Time");
    model2.addColumn("Dequeue Time");
    model2.addColumn("Total wait(Sec)");
    model2.addColumn("Leaving Time");
    model2.addColumn("Total Time(Sec)");
    model2.addColumn("Total Time W/O wait(Sec)");
    model2.addColumn("Intensity");
   // model2.addColumn("Response Time");

    jTable2=new JTable(model2);
    //jTable2.removeAll();

 //   jTable2.invalidate();
     jTable2.setBorder(BorderFactory.createEtchedBorder());
   // jTable2.setBackground(SystemColor.inactiveCaptionText);
    JTableHeader header = jTable2.getTableHeader();
    header.setBackground(SystemColor.window);
    DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
    dtcr.setHorizontalAlignment(SwingConstants.CENTER);
    TableColumn column = null;
   for (int i = 0; i <8; i++) {
       column = jTable2.getColumnModel().getColumn(i);
        column.setCellRenderer(dtcr);
        if(i==0) column.setPreferredWidth(50);
       else if(i==3 || i==5)column.setPreferredWidth(80);
         else column.setPreferredWidth(120);
   }
  }
}

////
private class DisplayButtonListener implements ActionListener{

    public void actionPerformed(ActionEvent e) {
  Dump monitor=pool.getPerformanceData();
  Vector v=monitor.getJobsHistory();
  Iterator theData=v.iterator();
  while(theData.hasNext()){
 ParentJob job=(ParentJob) theData.next();
  jta.append("\n"+job.toString());
  }

/*
   Vector v=monitor.getPhaseHistory();
   Iterator theData=v.iterator();
   while(theData.hasNext()){
  Phase phase=(Phase) theData.next();
   jta.append("\n"+phase.toString());

   }
 */
jta.append("Current Pool Size="+pool.getCurrentWorkers());
jta.append("Queue out size="+pool.getQueueOutSize());
jta.append("Total completed jobs="+pool.getTotalCompletedJobs());

       /*  Vector v=threadPool.getPassStorer();
         Iterator theData=v.iterator();
   while(theData.hasNext()){
  Phase phase=(Phase) theData.next();
   jta.append(phase.toString()+"\n");
   }
 */



    }
}

////////
private class StartStopButtonListener implements ActionListener{
  java.util.Timer timer;
//  RequestSender requestSender
public StartStopButtonListener(){}
public void actionPerformed(ActionEvent e){
    if(e.getSource()==startButton){
      startButton.setEnabled(false);
      if(fbos.isSelected()==true)
              fbos.setEnabled(false);
            else if(Xu.isSelected()==true)
              Xu.setEnabled(false);
            else if(wf.isSelected()==true)
             wf.setEnabled(false);

     jProgressBar1.setIndeterminate(false);
  //   if(frequencyTextField1.getText().equals("1") && frequencyTextField2.getText().equals("2")){
      UIManager.put("ProgressBar.cycleTime", new Integer(3000));
    // }
   //  else
   //  {//  UIManager.put("ProgressBar.cycleTime", new Integer(Integer.parseInt(frequencyTextField1.getText())*));
  //   if(frequencyTextField1.getText().equals("1"))   UIManager.put("ProgressBar.cycleTime", new Integer(2000));
    //    if(frequencyTextField1.getText().equals("2"))   UIManager.put("ProgressBar.cycleTime", new Integer(1000));
       //    if(frequencyTextField1.getText().equals("3"))   UIManager.put("ProgressBar.cycleTime", new Integer(500));


   //  }
   //  else if(){}
     jProgressBar1.setIndeterminate(true);
      jProgressBar1.setVisible(true);
    timer=new java.util.Timer(true);
   // String temp=frequencyTextField2.getText();
  //  String temp2=frequencyTextField1.getText();

  // timer.scheduleAtFixedRate(new RequestSender(strategyJobs,ioSlider,pool,jta,ioCpuRadioCheck,temp2),2000,Long.parseLong(temp)*1000);
   timer.scheduleAtFixedRate(new RequestSender(multi,strategyJobs,ioSlider,pool,jta,ioCpuRadioCheck),1000,1000);//ie the task strats after 1 second and repeat itselft after every .5 seconds.
  //  timer.schedule(new RequestSender(multi,strategyJobs,ioSlider,pool,jta,ioCpuRadioCheck,temp2),1000);//ie the task strats after 1 second and repeat itselft after every 1 seconds.
   timer.scheduleAtFixedRate(new FrequencyDetector(pool,jtaq,frequencyHolder),1000,1000);//ie the task strats after 1 seconds and then it will repeat itselft after every 5 seconds.
   timer.scheduleAtFixedRate(new MultiTimer(multi),1000,4000);
   timer.scheduleAtFixedRate(new ThroughputDetector(pool,jta2),1000,1000);//ie the task strats after 1 second and repeat itselft after every 5 seconds.
   timer.scheduleAtFixedRate(new AverageWaitDetector(pool,jta),1000,197);
   timer.schedule(new Stopper(timer,jProgressBar1,startButton),20000);// it wd b activated after 22 sec to stop the timers.

    }
     }
}

  void fbos_actionPerformed(ActionEvent e) {

  }



}
class XuwfRadioHandler implements ItemListener{
    MainFrame adaptee;

 XuwfRadioHandler(MainFrame adaptee) {
    this.adaptee = adaptee;
  }
private void commonToAll(){
   adaptee.setAllNull();
   adaptee.frequencyHolder=new FrequencyHolder();
    adaptee.multi=new FrequencyCreator();
     if(adaptee.startButton.isEnabled()==false) adaptee.startButton.setEnabled(true);
  }
  public void itemStateChanged(ItemEvent e){
  if(e.getSource()==adaptee.Xu){
  commonToAll();

 adaptee.pool=new HeuristicThreadPool(2,adaptee.jta,adaptee.frequencyHolder,adaptee.getPhaseModel(),adaptee.getJobModel());
 adaptee.strategyJobs=adaptee.xuStrategyJobs;
  }
  else if(e.getSource()==adaptee.wf){
   commonToAll();
   adaptee.pool=new WaterMarkThreadPooll(2,adaptee.jta,adaptee.frequencyHolder,adaptee.getPhaseModel(),adaptee.getJobModel());
   adaptee.strategyJobs=adaptee.wfStrategyJobs;

  }
  else if(e.getSource()==adaptee.fbos){
  commonToAll();
  adaptee.pool=new ThreadPool(2,adaptee.jta,adaptee.frequencyHolder,adaptee.getPhaseModel(),adaptee.getJobModel());
  //ThreadPool(2,this.jta,this.frequencyHolder,this.model,this.model2);
   adaptee.strategyJobs=adaptee.fbosStrategyJobs;
  }

  }

  }

class MainFrame_fbos_actionAdapter implements java.awt.event.ActionListener {
  MainFrame adaptee;

  MainFrame_fbos_actionAdapter(MainFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.fbos_actionPerformed(e);
  }
}