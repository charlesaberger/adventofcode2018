package thebergers.adventofcode2018.day1;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class FrequencyData {
	
	protected static final Logger LOG = LoggerFactory.getLogger(FrequencyData.class);
	
	protected List<Integer> data = new LinkedList<>();
	
	public List<Integer> getData() {
		return data;
	}
	
	public abstract void parseData(Object dataSource) throws DataParseException;
	
	public static FrequencyData getInstance(FrequencyDataType frequencyDataType, Object data) throws DataParseException {
		FrequencyData frequencyData = null;
		switch (frequencyDataType) {
		case ARRAY:
			frequencyData = new FrequencyDataArray();
			break;
		case FILE:
			frequencyData = new FrequencyDataFile();
			break;
		case LIST:
			frequencyData = new FrequencyDataList();
			break;
		case URL:
			frequencyData = new FrequencyDataUrl();
			break;
		default:
			throw new DataParseException("Unknown Frequency Data Type!");
		}
		frequencyData.parseData(data);
		return frequencyData;
	}
	
	public enum FrequencyDataType {
		ARRAY,
		FILE,
		LIST,
		URL;
	}
	
}
