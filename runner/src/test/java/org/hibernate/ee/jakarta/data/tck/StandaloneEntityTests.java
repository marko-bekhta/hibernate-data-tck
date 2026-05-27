package org.hibernate.ee.jakarta.data.tck;

import ee.jakarta.tck.data.framework.read.only._AsciiCharacters;
import ee.jakarta.tck.data.framework.read.only._CustomRepository;
import ee.jakarta.tck.data.framework.read.only._NaturalNumbers;
import ee.jakarta.tck.data.framework.read.only._PositiveIntegers;
import ee.jakarta.tck.data.standalone.entity.EntityTests;
import ee.jakarta.tck.data.standalone.entity._Boxes;
import ee.jakarta.tck.data.standalone.entity._MultipleEntityRepo;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.spi.CDI;
import jakarta.persistence.EntityManagerFactory;
import org.jboss.weld.junit5.WeldInitiator;
import org.jboss.weld.junit5.WeldJunit5Extension;
import org.jboss.weld.junit5.WeldSetup;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * Configure the {@link EntityTests} to run in a CDI environment using Weld
 */
@ExtendWith(WeldJunit5Extension.class)
public class StandaloneEntityTests extends EntityTests {

    @WeldSetup
    public WeldInitiator weld = WeldInitiator.from(EntityTests.class,
                    _Boxes.class,
                    _MultipleEntityRepo.class,
                    _AsciiCharacters.class,
                    _NaturalNumbers.class,
                    _PositiveIntegers.class,
                    _CustomRepository.class,
                    EntityManagerFactoryProducer.class)
            .activate(RequestScoped.class)
            .inject(this)
            .setPersistenceUnitFactory(ip -> CDI.current().select(EntityManagerFactory.class).get())
            .build()
            ;

}
