/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockreporter.scrappers;

import java.io.IOException;
import stockreporter.daomodels.StockSummary;
import stockreporter.daomodels.StockTicker;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import stockreporter.StockReporter;
import stockreporter.Utility;
import stockreporter.daomodels.StockDateMap;


/**
 *
 * @author Herve Tchoufong
 */
public class YahooScraper extends StockScraper {
    
    public YahooScraper(){
        super();
    }
    
    public void scrapeAllSummaryData(){
        for(StockTicker stockTicker: stockTickers)
            scapeSingleSummaryData(stockTicker);
    }
    
    public void scapeSingleSummaryData(StockTicker stockTicker){        
        System.out.println(stockTicker.getSymbol());
        String url = "https://finance.yahoo.com/quote/"+stockTicker.getSymbol().toLowerCase();
        try {
            Connection jsoupConn = Jsoup.connect(url);
            Document document = jsoupConn.referrer("http://www.google.com") .timeout(1000*5).get();

            StockDateMap stockDateMap = new StockDateMap();
            stockDateMap.setSourceId(1);
            stockDateMap.setTickerId(stockTicker.getId());
            stockDateMap.setDate(new SimpleDateFormat("MM-dd-yyyy").format(new Date()));
            int last_inserted_id = dao.insertStockDateMap(stockDateMap);
        
            Element table1 = document.select("table").get(0);
            Elements rows = table1.select("tr");    
            StockSummary summaryData = new StockSummary();
            
            summaryData.setStockDtMapId(last_inserted_id);
            
            int rowNum=0;
            String prevClosePrice = rows.get(rowNum).select("td").get(1).text();
            summaryData.setPrevClosePrice(Utility.convertStringCurrency(Utility.isBlank(prevClosePrice)?"0":prevClosePrice));
            rowNum++;
            
            String openPrice = rows.get(rowNum).select("td").get(1).text();
            summaryData.setOpenPrice(Utility.convertStringCurrency(Utility.isBlank(openPrice)?"0":openPrice));
            rowNum++;
            
            String bidPrice = rows.get(rowNum).select("td").get(1).text();
            summaryData.setBidPrice(Utility.convertStringCurrency(Utility.isBlank(openPrice)?"0":bidPrice));
            rowNum++;
            
            String askPrice = rows.get(rowNum).select("td").get(1).text();
            summaryData.setAskPrice(Utility.convertStringCurrency(Utility.isBlank(openPrice)?"0":Utility.computeStringValues(askPrice)));
            rowNum++;
            
            String daysRangeMax = Utility.getRangeMinAndMax(rows.get(rowNum).select("td").get(1).text())[0].trim();
            summaryData.setDaysRangeMin(Utility.convertStringCurrency(Utility.isBlank(daysRangeMax)?"0":daysRangeMax));            
            String daysRangeMin = Utility.getRangeMinAndMax(rows.get(rowNum).select("td").get(1).text())[1].trim();
            summaryData.setDaysRangeMax(Utility.convertStringCurrency(Utility.isBlank(daysRangeMin)?"0":daysRangeMin));
            rowNum++;
                    
            String fiftyTwoWeeksMin = Utility.getRangeMinAndMax(rows.get(rowNum).select("td").get(1).text())[0].trim();
            summaryData.setFiftyTwoWeeksMin(Utility.convertStringCurrency(Utility.isBlank(fiftyTwoWeeksMin)?"0":fiftyTwoWeeksMin));
            String fiftyTwoWeeksMax = Utility.getRangeMinAndMax(rows.get(rowNum).select("td").get(1).text().trim())[1].trim();
            summaryData.setFiftyTwoWeeksMax(Utility.convertStringCurrency(Utility.isBlank(fiftyTwoWeeksMax)?"0":fiftyTwoWeeksMax));
            rowNum++;
            
            String volume = rows.get(rowNum).select("td").get(1).text();
            summaryData.setAvgVolume(Utility.convertStringCurrency(Utility.isBlank(volume)?"0":volume).longValue());
            rowNum++;
           
            rowNum=0;
            Element table2 = document.select("table").get(1);
            rows = table2.select("tr");    
            
            String marketCap = rows.get(rowNum).select("td").get(1).text();
            summaryData.setMarketCap(Utility.convertStringCurrency(Utility.isBlank(marketCap)?"0":marketCap));
            rowNum++;
            
            String betaCoefficient = rows.get(rowNum).select("td").get(1).text();
            summaryData.setBetaCoefficient(Utility.convertStringCurrency(Utility.isBlank(betaCoefficient)?"0":betaCoefficient));
            rowNum++;
            
            String peRatio = rows.get(rowNum).select("td").get(1).text();
            summaryData.setPeRatio(Utility.convertStringCurrency(Utility.isBlank(peRatio)?"0":peRatio));
            rowNum++;
            
            String eps = rows.get(rowNum).select("td").get(1).text();
            summaryData.setEps(Utility.convertStringCurrency(Utility.isBlank(eps)?"0":eps));
            rowNum++;
            
            String earningDate = rows.get(rowNum).select("td").get(1).text();
            summaryData.setEarningDate(earningDate);
            rowNum++;
            
            String dividend = Utility.getNumberBeforeParantheses(rows.get(rowNum).select("td").get(1).text()).trim();
            summaryData.setDividentYield(Utility.convertStringCurrency(Utility.isBlank(dividend)?"0":dividend));
            rowNum++;
            
            String exDividendDate = rows.get(rowNum).select("td").get(1).text();
            summaryData.setExDividentDate(exDividendDate);
            rowNum++;
            
            String onYearTargetEst = rows.get(rowNum).select("td").get(1).text();
            summaryData.setOneYearTargetEst(Utility.convertStringCurrency(Utility.isBlank(onYearTargetEst)?"0":onYearTargetEst));
            
            dao.insertStockSummaryData(summaryData);
            
        } catch (IOException ex) {
            Logger.getLogger(StockReporter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}