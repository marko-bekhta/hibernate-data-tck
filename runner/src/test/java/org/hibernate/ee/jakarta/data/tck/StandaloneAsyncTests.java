package org.hibernate.ee.jakarta.data.tck;

import ee.jakarta.tck.data.standalone.entity.EntityTests;
import ee.jakarta.tck.data.web.async.AsyncTests;
import ee.jakarta.tck.data.web.async._Accounts;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.spi.CDI;
import jakarta.persistence.EntityManagerFactory;
import org.jboss.weld.junit5.WeldInitiator;
import org.jboss.weld.junit5.WeldJunit5Extension;
import org.jboss.weld.junit5.WeldSetup;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Map;

/**
 * Configure the {@link EntityTests} to run in a CDI environment using Weld
 */
@ExtendWith(WeldJunit5Extension.class)
public class StandaloneAsyncTests extends AsyncTests {

    @WeldSetup
    public WeldInitiator weld = WeldInitiator.from(AsyncTests.class,
                    _Accounts.class,
                    EntityManagerFactoryProducer.class)
            .activate(RequestScoped.class)
            .inject(this)
            .setPersistenceUnitFactory(ip -> CDI.current().select(EntityManagerFactory.class).get())
            .setPersistenceContextFactory(ip -> CDI.current().select(EntityManagerFactory.class).get().createEntityManager())
            .setPersistenceAgentFactory(ip -> CDI.current().select(EntityManagerFactory.class).get().createEntityAgent(Map.of()))
            .build();

}
