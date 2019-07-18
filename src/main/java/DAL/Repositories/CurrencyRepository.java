package DAL.Repositories;

import DAL.HibernateUtil;
import DAL.dto.CurrencyEntity;
import org.hibernate.Session;
import org.hibernate.query.*;

public class CurrencyRepository {

    public static CurrencyEntity getCurrencyBySymbol(String symbol) {
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM currency WHERE symbol = :symbol");
            query.setParameter("symbol", symbol);
            CurrencyEntity curr = (CurrencyEntity) query.uniqueResult();
            HibernateUtil.shutdown();
            return curr;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
