package org.hibernate.ee.jakarta.data.tck;

import ee.jakarta.tck.data.standalone.persistence.stateful.StatefulPersistenceEntityTests;
import ee.jakarta.tck.data.standalone.persistence.stateful._Inventory;
import ee.jakarta.tck.data.standalone.persistence.stateful._Products;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.spi.CDI;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.jboss.weld.junit5.WeldInitiator;
import org.jboss.weld.junit5.WeldJunit5Extension;
import org.jboss.weld.junit5.WeldSetup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(WeldJunit5Extension.class)
public class StandaloneStatefulPersistenceEntityTests extends StatefulPersistenceEntityTests {

    private EntityManager entityManager;

    @WeldSetup
    public WeldInitiator weld = WeldInitiator.from(StatefulPersistenceEntityTests.class,
                    _Inventory.class,
                    _Products.class,
                    EntityManagerFactoryProducer.class,
                    UserTransactionProducer.class,
                    TransactionalInterceptor.class)
            .activate(RequestScoped.class)
            .inject(this)
            .setPersistenceUnitFactory(ip -> CDI.current().select(EntityManagerFactory.class).get())
            .setPersistenceContextFactory(ip -> {
                if (entityManager == null) {
                    entityManager = CDI.current().select(EntityManagerFactory.class).get().createEntityManager();
                }
                return entityManager;
            })
            .setPersistenceAgentFactory(ip -> CDI.current().select(EntityManagerFactory.class).get().createEntityAgent(java.util.Map.of()))
            .build();

    @BeforeEach
    public void setUp() {
        entityManager = null;
    }
}
