package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
    

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

public class ConvertCurrency extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String API_KEY = "6ce5850849e0471ca4727ff2";
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String amountParam = request.getParameter("amount");
        String sourceCurrency = request.getParameter("sourceCurrency");
        String targetCurrency = request.getParameter("targetCurrency");

        if (amountParam == null || amountParam.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Amount is required.");
            return;
        }

        try {
            double amount = Double.parseDouble(amountParam);

            if (!isValidCurrencyCode(sourceCurrency) || !isValidCurrencyCode(targetCurrency)) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid currency codes.");
                return;
            }

            double convertedAmount = convertCurrency(amount, sourceCurrency, targetCurrency);

            // Set converted amount and symbols as request attributes
            request.setAttribute("amount", amount);
            request.setAttribute("sourceCurrency", sourceCurrency);
            request.setAttribute("sourceCurrencySymbol", getCurrencySymbol(sourceCurrency));
            request.setAttribute("targetCurrency", targetCurrency);
            request.setAttribute("targetCurrencySymbol", getCurrencySymbol(targetCurrency));
            request.setAttribute("convertedAmount", convertedAmount);

            // Forward request to result.jsp
            request.getRequestDispatcher("result.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,
                    "Invalid amount. Please enter a valid numeric value.");
        }
    }

    private boolean isValidCurrencyCode(String code) {
        // You can implement additional validation if needed
        return code != null && !code.isEmpty();
    }

    private double convertCurrency(double amount, String sourceCurrency, String targetCurrency) throws IOException {
        URL url = new URL(API_URL + sourceCurrency);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder responseContent = new StringBuilder();
        String line;

        while ((line = in.readLine()) != null) {
            responseContent.append(line);
        }
        in.close();

        JSONObject jsonResponse = new JSONObject(responseContent.toString());
        JSONObject rates = jsonResponse.getJSONObject("conversion_rates");

        double sourceRate = rates.getDouble(sourceCurrency);
        double targetRate = rates.getDouble(targetCurrency);

        return amount * (targetRate / sourceRate);
    }

    private String getCurrencySymbol(String currencyCode) {
        switch (currencyCode) {
            case "USD":
                return "$";
            case "EUR":
                return "€";
            case "GBP":
                return "£";
            case "JPY":
                return "¥";
            case "AUD":
                return "A$";
            case "CAD":
                return "C$";
            case "INR":
                return "₹";
            case "CNY":
                return "¥";
            case "BRL":
                return "R$";
            case "MXN":
                return "Mex$";
            case "ZAR":
                return "R";
            case "RUB":
                return "₽";
            case "KRW":
                return "₩";
            case "TRY":
                return "₺";
            case "AED":
                return "د.إ";
            case "SGD":
                return "S$";
            case "HKD":
                return "HK$";
            case "NZD":
                return "NZ$";
            case "CHF":
                return "CHF";
            case "SEK":
                return "kr";
            case "NOK":
                return "kr";
            case "DKK":
                return "kr";
            case "ISK":
                return "kr";
            case "HUF":
                return "Ft";
            case "PLN":
                return "zł";
            case "THB":
                return "฿";
            case "PHP":
                return "₱";
            case "IDR":
                return "Rp";
            case "MYR":
                return "RM";
            case "ARS":
                return "$";
            case "ILS":
                return "₪";
            case "COP":
                return "$";
            case "EGP":
                return "£";
            case "CLP":
                return "$";
            case "VND":
                return "₫";
            case "UAH":
                return "₴";
            case "SAR":
                return "﷼";
            case "KWD":
                return "ك";
            case "IQD":
                return "ع.د";
            case "QAR":
                return "ر.ق";
            case "CRC":
                return "₡";
            case "PEN":
                return "S/.";
            case "PKR":
                return "₨";
            case "OMR":
                return "ر.ع.";
            case "TWD":
                return "NT$";
            case "HRK":
                return "kn";
            default:
                return ""; // Return empty string if symbol not found
        }
    }
}
