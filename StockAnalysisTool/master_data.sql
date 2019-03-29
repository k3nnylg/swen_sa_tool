-- Insert data into STOCK_TICKER table
--------------------------------------
INSERT INTO STOCK_TICKER (SYMBOL, NAME) VALUES ('MSFT', 'Microsoft Corporation.');
INSERT INTO STOCK_TICKER (SYMBOL, NAME) VALUES ('AAPL', 'Apple Inc.');
INSERT INTO STOCK_TICKER (SYMBOL, NAME) VALUES ('GOOG', 'Alphabet Inc.');
INSERT INTO STOCK_TICKER (SYMBOL, NAME) VALUES ('BA', 'The Boeing Company');
INSERT INTO STOCK_TICKER (SYMBOL, NAME) VALUES ('NFLX', 'Netflix Inc.');
INSERT INTO STOCK_TICKER (SYMBOL, NAME) VALUES ('AMAZ', 'Amazon.com Inc.');
INSERT INTO STOCK_TICKER (SYMBOL, NAME) VALUES ('FB', 'Facebook Inc.');
INSERT INTO STOCK_TICKER (SYMBOL, NAME) VALUES ('CSCO', 'Cisco Systems Inc.');
INSERT INTO STOCK_TICKER (SYMBOL, NAME) VALUES ('TSLA', 'Tesla Inc.');
INSERT INTO STOCK_TICKER (SYMBOL, NAME) VALUES ('TIF', 'Tiffany & Co.');

-- Insert data into STOCK_SOURCE table
--------------------------------------
INSERT INTO STOCK_SOURCE (NAME) VALUES ('Yahoo');
INSERT INTO STOCK_SOURCE (NAME) VALUES ('Investopedia');

COMMIT;