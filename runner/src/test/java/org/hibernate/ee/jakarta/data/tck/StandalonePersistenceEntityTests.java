package org.hibernate.ee.jakarta.data.tck;

import ee.jakarta.tck.data.standalone.persistence.stateless.PersistenceEntityTests;
import ee.jakarta.tck.data.standalone.persistence.stateless._Catalog;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.spi.CDI;
import jakarta.persistence.EntityManagerFactory;
import org.jboss.weld.junit5.WeldInitiator;
import org.jboss.weld.junit5.WeldJunit5Extension;
import org.jboss.weld.junit5.WeldSetup;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(WeldJunit5Extension.class)
public class StandalonePersistenceEntityTests extends PersistenceEntityTests {

    @WeldSetup
    public WeldInitiator weld = WeldInitiator.from(PersistenceEntityTests.class,
                    _Catalog.class,
                    EntityManagerFactoryProducer.class)
            .activate(RequestScoped.class)
            .inject(this)
            .setPersistenceUnitFactory(ip -> CDI.current().select(EntityManagerFactory.class).get())
            .build()
            ;
}
