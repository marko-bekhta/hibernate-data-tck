package org.hibernate.ee.jakarta.data.tck;

import ee.jakarta.tck.data.framework.read.only._AsciiCharacters;
import ee.jakarta.tck.data.framework.read.only._NaturalNumbers;
import ee.jakarta.tck.data.framework.read.only.qbmn._AsciiCharactersByName;
import ee.jakarta.tck.data.framework.read.only.qbmn._CustomRepository;
import ee.jakarta.tck.data.framework.read.only.qbmn._NaturalNumbersByName;
import ee.jakarta.tck.data.framework.read.only.qbmn._PositiveIntegersByName;
import ee.jakarta.tck.data.standalone.entity.qbmn.EntityQueryByMethodNameTests;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.spi.CDI;
import jakarta.persistence.EntityManagerFactory;
import org.jboss.weld.junit5.WeldInitiator;
import org.jboss.weld.junit5.WeldJunit5Extension;
import org.jboss.weld.junit5.WeldSetup;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Map;

@ExtendWith(WeldJunit5Extension.class)
public class StandaloneEntityQueryByMethodNameTests extends EntityQueryByMethodNameTests {
    @WeldSetup
    public WeldInitiator weld = WeldInitiator.from(EntityQueryByMethodNameTests.class,
                    _AsciiCharactersByName.class,
                    _NaturalNumbersByName.class,
                    _PositiveIntegersByName.class,
                    _AsciiCharacters.class,
                    _NaturalNumbers.class,
                    _CustomRepository.class,
                    EntityManagerFactoryProducer.class)
            .activate(RequestScoped.class)
            .inject(this)
            .setPersistenceUnitFactory(ip -> CDI.current().select(EntityManagerFactory.class).get())
            .setPersistenceContextFactory(ip -> CDI.current().select(EntityManagerFactory.class).get().createEntityManager())
            .setPersistenceAgentFactory(ip -> CDI.current().select(EntityManagerFactory.class).get().createEntityAgent(Map.of()))
            .build();
}
