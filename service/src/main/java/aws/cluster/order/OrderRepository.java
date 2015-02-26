package aws.cluster.order;

import java.util.List;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import aws.cluster.order.entity.Order;

@Repository
public class OrderRepository {
    private final static Logger logger = LoggerFactory.getLogger(OrderRepository.class);
    private final SessionFactory sessionFactory;

    @Autowired
    public OrderRepository(final SessionFactory sessionFactory) {
        logger.info("created OrderRepository");
        this.sessionFactory = sessionFactory;
    }

    public List<Order> getOrdersByUserId(final int userId) {
        logger.info("fetching orders for user {}", userId);
        @SuppressWarnings("unchecked")
        List<Order> orders = sessionFactory.getCurrentSession().createQuery("from Order where userid = :userid").setParameter("userid", userId).list();
        logger.info("fetched orders {} for user {}", orders, userId);
        return orders;
    }
}
