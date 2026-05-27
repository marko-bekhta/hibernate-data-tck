package org.hibernate.ee.jakarta.data.tck;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.transaction.UserTransaction;

@ApplicationScoped
public class UserTransactionProducer {

    @Produces
    @ApplicationScoped
    public UserTransaction createUserTransaction() {
        return com.arjuna.ats.jta.UserTransaction.userTransaction();
    }
}
