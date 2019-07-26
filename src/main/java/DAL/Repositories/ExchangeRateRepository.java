package DAL.Repositories;

import DAL.HibernateUtil;
import DAL.dto.ExchangeRate;
import DAL.net.BinanceApiConnection;
import org.hibernate.Session;
import org.hibernate.query.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ExchangeRateRepository {

    public static ExchangeRate getCurrencyBySymbol(String symbol) {
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM ExchangeRate WHERE symbol = :symbol");
            query.setParameter("symbol", symbol);
            ExchangeRate curr = (ExchangeRate) query.uniqueResult();
            session.close();
            return curr;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<ExchangeRate> getAllCurrencies() {
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM ExchangeRate");
            List currList = query.list();
            session.close();
            return currList;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void addCurrency(String symbol, double price) {
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            ExchangeRate curr = new ExchangeRate();
            curr.setSymbol(symbol);
            curr.setValue(price);
            curr.setPriceTimestamp(LocalDateTime.now());
            session.save(curr);
            session.getTransaction().commit();
            session.close();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private static void truncateExchangeRates() {
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.getTransaction().begin();
            session.createNativeQuery("truncate table kr_exchange_rates").executeUpdate();
            session.getTransaction().commit();
            session.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateExchangeRates() throws Exception{
        truncateExchangeRates();

        long time1 = System.nanoTime();

        List<String> symbols = BinanceApiConnection.getSymbolsList();
        Map<String, Double> exchangeRates = BinanceApiConnection.performMultithreadedPriceRequests(symbols, 20);

        for(Map.Entry<String, Double> entry : exchangeRates.entrySet()) {
            addCurrency(entry.getKey(), entry.getValue());
        }

        long time2 = System.nanoTime();


        System.out.println("\nExchange rates update complete.\nTime elapsed: " + (double)(time2 - time1) / 1000000000 + " seconds.");
    }
}
