package com.gokhantamkoc.javabootcamp.odevhafta3.repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.gokhantamkoc.javabootcamp.odevhafta3.model.Candle;

public class CryptoDataCSVRepository implements CSVRepository {
	
	private final String COMMA_DELIMITER = ",";

	@Override
	public List<Candle> readCSV(String filename) throws FileNotFoundException, IOException {
		List<Candle> candles = new ArrayList<Candle>();
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(filename);
		// Bu alandan itibaren kodunuzu yazabilirsiniz
		BufferedReader csvReader = null;
		String csvRecord = null;
		try {
			csvReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

			boolean isFirstLine = true;
			while ((csvRecord = csvReader.readLine()) != null) {
				if (isFirstLine) {
					isFirstLine = false;
					continue;
				}
				final String[] eachRow = csvRecord.split(",");
				Candle nextCandleInfo = new Candle(Long.parseLong(eachRow[0]), Double.parseDouble(eachRow[3]),
						Double.parseDouble(eachRow[4]),
						Double.parseDouble(eachRow[5]),
						Double.parseDouble(eachRow[6]),
						Double.parseDouble(eachRow[7]));
				candles.add(nextCandleInfo);
			}
		} catch (IOException e) {
			throw new RuntimeException("Reading CSV failed.", e);
		} finally {
			if (csvReader != null)
				try {
					csvReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		// Bu alandan sonra kalan kod'a dokunmayiniz.
		return candles;
	}

}
