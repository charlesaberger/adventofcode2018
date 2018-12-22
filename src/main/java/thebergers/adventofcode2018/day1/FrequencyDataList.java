package thebergers.adventofcode2018.day1;

import java.util.List;

public class FrequencyDataList extends FrequencyData {

	@SuppressWarnings("unchecked")
	@Override
	public void parseData(Object dataSource) throws DataParseException {
		if (dataSource instanceof List) {
			this.data = (List<Integer>)dataSource;
		} else {
			throw new DataParseException("dataSource must be List of Integer!");
		}

	}

}
