package com.gokhantamkoc.javabootcamp.odevhafta3.service;

import com.gokhantamkoc.javabootcamp.odevhafta3.model.Candle;
import com.gokhantamkoc.javabootcamp.odevhafta3.repository.CSVRepository;
import com.gokhantamkoc.javabootcamp.odevhafta3.util.chart.CandleStickChart;

import java.util.List;

public class ChartService {
	
	CSVRepository cryptoDataCSVRepository;
	
	public ChartService(CSVRepository cryptoDataCSVRepository) {
		this.cryptoDataCSVRepository = cryptoDataCSVRepository;
	}
	
	public CandleStickChart createChartFromCryptoData() {
		// Bu metodu doldurmanizi bekliyoruz.

		final CandleStickChart candleStickChart = new CandleStickChart("BTC_daily_chart");
		try {
			List<Candle> candles = cryptoDataCSVRepository.readCSV("Binance_BTCUSDT_d.csv");

			for (Candle candle : candles) {
				candleStickChart.addCandle(candle.getTime(),
						candle.getOpen(),
						candle.getHigh(),
						candle.getLow(),
						candle.getClose(),
						candle.getVolume());
			}

		} catch (Exception e) {
			System.out.println("Error occured while creatining the chart" + e);
		}
		return candleStickChart;
	}
}
