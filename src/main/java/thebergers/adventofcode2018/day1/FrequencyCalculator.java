package thebergers.adventofcode2018.day1;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class FrequencyCalculator {

	private Map<Integer, Integer> frequencyCounts = new HashMap<>();
	
	public Integer calculateResult(FrequencyData frequencyData) {
		Integer result = 0;
		for (Integer val : frequencyData.getData()) {
			result += val;
		}
		return result;
	}
	
	public Integer calibrate(FrequencyData frequencyData) {
		Integer calibratedFrequency = null;
		boolean calibrated = false;
		Integer frequency = 0;
		frequencyCounts.put(frequency,  1);
		while (!calibrated) {
			for (Integer val : frequencyData.getData()) {
				frequency += val;
				Integer count = 0;
				if (frequencyCounts.containsKey(frequency)) {
					count = frequencyCounts.get(frequency);
				}
				count++;
				frequencyCounts.put(frequency, count);
				if (isCalibrated()) {
					calibratedFrequency = frequency;
					calibrated = true;
					break;
				}
			}
		}
		return calibratedFrequency;
	}
	
	private boolean isCalibrated() {
		Set<Integer> keys = frequencyCounts.keySet();
		for (Integer key : keys) {
			Integer count = frequencyCounts.get(key);
			if (count == 2) {
				return true;
			}
		}
		return false;
	}
}
