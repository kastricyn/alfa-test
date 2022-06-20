package ru.kastricyn.alfatest.model;

import java.util.Map;

/**
 * useful (part of full) model for response object of
 * <a href="https://docs.openexchangerates.org/docs/historical-json">historical.json</a> &
 * <a href="https://docs.openexchangerates.org/docs/latest-json">latest.json</a>
 */
public record ExchangeRates(long timestamp, String base, Map<String, Double> rates) {
}
