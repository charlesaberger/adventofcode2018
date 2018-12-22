package thebergers.adventofcode2018.day1;

public class FrequencyDataArray extends FrequencyData {

	@Override
	public void parseData(Object dataSource) throws DataParseException {
		if (dataSource instanceof int[]) {
			int[] dataSourceInt = (int[])dataSource;
			for(int val : dataSourceInt) {
				this.data.add(val);
			}
		} else {
			throw new DataParseException("dataSource must be array of int!");
		}
	}

}
