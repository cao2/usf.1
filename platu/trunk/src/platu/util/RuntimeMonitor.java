package platu.util;

public class RuntimeMonitor implements Runnable {
	double 	localStartTime = 0;
	double 	elapsedTimeSec = 0;
	double 	peakTotalMem = 0;
	double 	peakUsedMem = 0;
	
	int samplingPeriod = 1000;

	public RuntimeMonitor() {
		localStartTime = System.currentTimeMillis();
	}
	
	public RuntimeMonitor(int sampleGap) {
		localStartTime = System.currentTimeMillis();
		this.samplingPeriod = sampleGap;
	}

	public void run() {
		try {
			while (true) {
				double elapsedTimeMillis = System.currentTimeMillis() - localStartTime;
				this.elapsedTimeSec = elapsedTimeMillis / 1000F;


				double curTotalMem = Runtime.getRuntime().totalMemory();
				double curUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

				peakTotalMem = curTotalMem > this.peakTotalMem ? curTotalMem : this.peakTotalMem;
				peakUsedMem = curUsedMem > this.peakUsedMem ? curUsedMem : peakUsedMem;

				// if(this.elapsedTimeSec >= Options.TimeUpperBound) {
				// this.project.s= true;
				// }
				// if(peakUsedMem/1000000 >= Options.MemUpperBound) {
				// this.lMemOut = true;
				// }

				// System.out.println("*** " + this.elapsedTimeSec + "  " +
				// this.peakTotalMem/1000 + " MB " + this.peakUsedMem/1000 +
				// " MB");
				Thread.sleep(samplingPeriod);
			}
		} catch (InterruptedException exception) {
			// System.out.println("Thread terminate");
			return;
		}
	}

	public double getElapsedime() {
		return this.elapsedTimeSec;
	}

	public double getPeakTotalMem() {
		return this.peakTotalMem / 1000000F;
	}

	public double getPeakUsedMem() {
		return this.peakUsedMem / 1000000F;
	}
}
