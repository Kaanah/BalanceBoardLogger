package pl.edu.pwr;

import info.monitorenter.gui.chart.Chart2D;
import info.monitorenter.gui.chart.ITrace2D;
import info.monitorenter.gui.chart.traces.Trace2DLtd;


import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;

import java.util.Timer;



@SuppressWarnings("serial")
public class GraphPanel extends JPanel{
	
	public static Timer timer;
	final static ITrace2D trace = new Trace2DLtd(200,"COPx"); 
	final static ITrace2D trace2 = new Trace2DLtd(200,"COPy"); 
	boolean zapis = false;

	
	public GraphPanel() {
		setLayout(new BorderLayout(0,0));
		
	    JPanel frameGraph = new JPanel();
	    Chart2D chart = new Chart2D();
	    add(frameGraph,BorderLayout.CENTER);
	    frameGraph.setLayout(new BorderLayout());	    
	    frameGraph.add(chart,BorderLayout.CENTER);
	    	    
	    frameGraph.setSize(400,300);
	    frameGraph.setVisible(true);
	    
	    
	    trace.setColor(Color.RED); 		
	    chart.addTrace(trace);
	    
	   
	    trace2.setColor(Color.BLUE); 
	    chart.addTrace(trace2);
	    
	    timer = new Timer(true);
	    
	}

}
