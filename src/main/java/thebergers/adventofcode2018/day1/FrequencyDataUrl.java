package thebergers.adventofcode2018.day1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class FrequencyDataUrl extends FrequencyData {

	@Override
	public void parseData(Object dataSource) throws DataParseException {
		if (!(dataSource instanceof java.lang.String)) {
			throw new DataParseException("DataSource must be a String");
		}
		
		String urlStr = (String)dataSource;

		URL url = null;
		try {
			url = new URL(urlStr);
		} catch (MalformedURLException e) {
			throw new DataParseException(String.format("Error parsing dataSource %s", urlStr), e);
		}
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet get = new HttpGet(urlStr);
		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(get);
			HttpEntity entity = response.getEntity();
			validateContentType(entity);
			readUrlContent(entity);
		} catch (ClientProtocolException e) {
			String message = String.format("Protocol Exception for url %s", url);
			LOG.error(message, e);
			throw new DataParseException(message, e);
		} catch (IOException e) {
			String message = String.format("IOException for url %s", url);
			LOG.error(message, e);
			throw new DataParseException(message, e);
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					LOG.error("Error closing response", e);
				}
			}
			try {
				httpClient.close();
			} catch (IOException e) {
				LOG.error("Error closing httpClient", e);
			}
		}
	}

	private void validateContentType(HttpEntity entity) throws DataParseException {
		Header contentTypeHeader = entity.getContentType();
		LOG.debug("Content Type is {}", contentTypeHeader);
		String contentType = contentTypeHeader.getValue();
		if (!"text/plain".contentEquals(contentType)) {
			throw new DataParseException(String.format("Content Type %s not the expected value of text/plain", contentType));
		}
	}

	private void readUrlContent(HttpEntity entity) throws DataParseException {
		try {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent()));
			while (true) {
				String dataStr = bufferedReader.readLine();
				if (dataStr == null) {
					break;
				}
				parseData(dataStr);
			}
		} catch (IOException e) {
			LOG.error("Error reading inputStream");
			throw new DataParseException("Error reading inputStream", e);
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
