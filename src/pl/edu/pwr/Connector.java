package pl.edu.pwr;

import edu.unsw.cse.wiiboard.WiiBoardDiscoverer;
import edu.unsw.cse.wiiboard.event.WiiBoardListener;

public class Connector {

	public static void main(String[] args) {
		System.setProperty("bluecove.jsr82.psm_minimum_off", "true");
		if (WiiBoardDiscoverer.isBluetoothReady()) {
			WiiBoardDiscoverer wbd = WiiBoardDiscoverer.getWiiBoardDiscoverer();
			WiiBoardListener wbl = new WiiBoardL();
			DiscoveryL dl = new DiscoveryL(wbd, wbl);
			wbd.addWiiBoardDiscoveryListener(dl);
			wbd.startWiiBoardSearch();
			}
		else
			System.out.println("No adapter found. Shutting down.");
	}
}
