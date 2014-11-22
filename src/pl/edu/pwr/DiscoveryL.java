package pl.edu.pwr;

import edu.unsw.cse.wiiboard.WiiBoard;
import edu.unsw.cse.wiiboard.WiiBoardDiscoverer;
import edu.unsw.cse.wiiboard.WiiBoardDiscoveryListener;
import edu.unsw.cse.wiiboard.event.WiiBoardListener;

public class DiscoveryL implements WiiBoardDiscoveryListener {

	WiiBoardDiscoverer wbd;
	WiiBoardListener wbl;

	public DiscoveryL(WiiBoardDiscoverer wbd, WiiBoardListener wbl) {
		this.wbd = wbd;
		this.wbl = wbl;
	}

	@Override
	public void wiiBoardDiscovered(WiiBoard wiiboard) {
		wbd.stopWiiBoardSearch();
		wiiboard.addListener(wbl);
	}

	public WiiBoardDiscoverer getWbd() {
		return wbd;
	}

	public void setWbd(WiiBoardDiscoverer wbd) {
		this.wbd = wbd;
	}

	public WiiBoardListener getWbl() {
		return wbl;
	}

	public void setWbl(WiiBoardListener wbl) {
		this.wbl = wbl;
	}

}
