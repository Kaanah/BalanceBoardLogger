package pl.edu.pwr;

import java.awt.Color;
import java.util.TimerTask;

public class task extends TimerTask{

	int x = 385, y = 60, numer=1, licznik=0;
	static boolean k1 = false, r1 = false;
	static boolean k2 = false, r2 = false;
	static boolean k3 = false, r3 = false;
	static boolean k4 = false, r4 = false;
	static boolean k5 = false, r5 = false;
	static boolean k6 = false, r6 = false;
	static boolean k7 = false, r7 = false;
	static boolean k8 = false, r8 = false;
	
	@Override
	public void run() {
		
		if(WiiBoardL.startBadania == true){
		switch(numer)
		{
		case 1:
			x = 385;
			y = 60;
			k1 = true;
			licznik++;
			WiiBoardL.btnStart.setForeground(Color.GREEN);
			if (WiiBoardL.massX == 0.05 || WiiBoardL.massX == -0.03 || WiiBoardL.massY == 0.1 || WiiBoardL.massY == -0.08){
				r1 = true;
				WiiBoardL.btnStart.setForeground(Color.RED);
			}
			if (licznik == 1){
				licznik = 0;
				numer++;}
			break;
		case 2:
			x = 535;
			y = 135;
			k2 = true;
			licznik++;
			if (WiiBoardL.massX == 0.05 || 
					 WiiBoardL.massX == -0.03 || 
					 WiiBoardL.massY == 0.1 || 
					 WiiBoardL.massY == -0.08){
				r2 = true;
			}
			if (licznik == 1){
				licznik = 0;
				numer++;}
			break;
		case 3:
			x = 610;
			y = 285;
			k3 = true;
			licznik++;
			if (WiiBoardL.massX == 0.05 || 
					 WiiBoardL.massX == -0.03 || 
					 WiiBoardL.massY == 0.1 || 
					 WiiBoardL.massY == -0.08){
				r3 = true;
			}
			if (licznik == 1){
				licznik = 0;
				numer++;}
			break;
		case 4:
			x = 535;
			y = 435;
			k4 = true;
			licznik++;
			if (WiiBoardL.massX>-0.05 || WiiBoardL.massX<-0.163 || WiiBoardL.massY>0.117 || WiiBoardL.massY<0.53){
				r4 = true;
			}
			if (licznik == 1){
				licznik = 0;
				numer++;}
			break;
		case 5:
			x = 385;
			y = 510;
			k5 = true;
			licznik++;
			if (WiiBoardL.massX>-0.05 || WiiBoardL.massX<-0.163 || WiiBoardL.massY>0.117 || WiiBoardL.massY<0.53){
				r5 = true;
			}
			if (licznik == 1){
				licznik = 0;
				numer++;}
			break;
		case 6:
			x = 235;
			y = 435;
			k6 = true;
			licznik++;
			if (WiiBoardL.massX>-0.05 || WiiBoardL.massX<-0.163 || WiiBoardL.massY>0.117 || WiiBoardL.massY<0.53){
				r6 = true;
			}
			if (licznik == 1){
				licznik = 0;
				numer++;}
			break;
		case 7:
			x = 160;
			y = 285;
			k7 = true;
			licznik++;
			if (WiiBoardL.massX>-0.05 || WiiBoardL.massX<-0.163 || WiiBoardL.massY>0.117 || WiiBoardL.massY<0.53){
				r7 = true;
			}
			if (licznik == 1){
				licznik = 0;
				numer++;}
			break;
		case 8:
			x = 235;
			y = 135;
			k8 = true;
			licznik++;
			if (WiiBoardL.massX>-0.05 || WiiBoardL.massX<-0.163 || WiiBoardL.massY>0.117 || WiiBoardL.massY<0.53){
				r8 = true;
			}
			if (licznik == 1){
				licznik = 0;
				numer = 1;}
			break;
		}
		WiiBoardL.graph.repaint();
	}
		else if (WiiBoardL.reset == true){
			numer = 1;
		}
	}


	
	
}
