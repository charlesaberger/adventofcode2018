package thebergers.adventofcode2018.day1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FrequencyDataFile extends FrequencyData {

	@Override
	public void parseData(Object dataSource) throws DataParseException {
		if (!(dataSource instanceof java.lang.String)) {
			throw new DataParseException("DataSource must be a String");
		}
		
		String filename = (String)dataSource;
		File file = new File(filename);
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			while (true) {
				String dataStr = reader.readLine();
				if (dataStr == null) {
					break;
				}
				parseData(dataStr);
			}
		} catch (IOException e) {
			String message = String.format("Error reading from file %s", filename);
			LOG.error(message, e);
			throw new DataParseException(message, e);
		}

	}
	
	private void parseData(String dataStr) {
		try {
			Integer dataInteger = Integer.parseInt(dataStr);
			data.add(dataInteger);
		} catch (NumberFormatException e) {
			LOG.warn("Error parsing value {} to Integer", dataStr);
		}
	}

}
