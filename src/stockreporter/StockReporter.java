package stockreporter;

import java.util.logging.Level;
import java.util.logging.Logger;
import stockreporter.scrappers.YahooScraper;
import stockreporter.scrappers.InvestopediaScraper;

/**
 * Main class for scrapping the data
 */
public class StockReporter {
    
    private static final Logger logger = Logger.getLogger(StockReporter.class.getName());
    
    public static void main(String[] args) {    
        
        logger.log(Level.INFO, "Get database instance");
        StockDao dao = StockDao.getInstance();
        
        logger.log(Level.INFO, "Create scraper instances");
        InvestopediaScraper investopediaScraper = new InvestopediaScraper();
        YahooScraper yahooScraper = new YahooScraper();
        
        logger.log(Level.INFO, "Scrap summary data for Yahoo...");
        yahooScraper.scrapeAllSummaryData();
        
        logger.log(Level.INFO, "Scrap summary data for Investopedia...");
        investopediaScraper.scrapeAllSummaryData();
        
        logger.log(Level.INFO, "Scrap historical data for Yahoo...");
        yahooScraper.scrapeAllHistoricalData();
        
        //TODO: Future
        //Scrap historical data for Investopedia when available
    }
}