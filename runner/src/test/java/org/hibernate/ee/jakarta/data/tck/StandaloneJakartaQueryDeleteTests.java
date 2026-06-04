package org.hibernate.ee.jakarta.data.tck;

import ee.jakarta.tck.data.framework.read.only._Countries;
import ee.jakarta.tck.data.standalone.entity.JakartaQueryDeleteTests;
import ee.jakarta.tck.data.standalone.entity._VegetableRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.spi.CDI;
import jakarta.persistence.EntityManagerFactory;
import org.jboss.weld.junit5.WeldInitiator;
import org.jboss.weld.junit5.WeldJunit5Extension;
import org.jboss.weld.junit5.WeldSetup;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Map;

@ExtendWith(WeldJunit5Extension.class)
public class StandaloneJakartaQueryDeleteTests extends JakartaQueryDeleteTests {
    @WeldSetup
    public WeldInitiator weld = WeldInitiator.from(JakartaQueryDeleteTests.class,
                    _VegetableRepository.class,
                    EntityManagerFactoryProducer.class)
            .activate(RequestScoped.class)
            .inject(this)
            .setPersistenceUnitFactory(ip -> CDI.current().select(EntityManagerFactory.class).get())
            .setPersistenceContextFactory(ip -> CDI.current().select(EntityManagerFactory.class).get().createEntityManager())
            .setPersistenceAgentFactory(ip -> CDI.current().select(EntityManagerFactory.class).get().createEntityAgent(Map.of()))
            .build();

}
