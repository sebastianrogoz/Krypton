import DAL.HibernateUtil;
import DAL.dto.CurrencyEntity;
import NET.BinanceApiConnection;
import org.hibernate.Session;



public class Main {
    public static void main(String[] args) throws Exception {

        BinanceApiConnection binanceConn = new BinanceApiConnection();

        Session session = HibernateUtil.getSessionFactory().openSession();


        session.beginTransaction();
        CurrencyEntity curr = session.get(CurrencyEntity.class, 1);
        if(curr != null) {
            System.out.println(curr.getId());
            System.out.println(curr.getSymbol());
            System.out.println(curr.getPrice());
            System.out.println(curr.getPriceTimestamp());
        }

        session.getTransaction().commit();
        HibernateUtil.shutdown();
    }
}

