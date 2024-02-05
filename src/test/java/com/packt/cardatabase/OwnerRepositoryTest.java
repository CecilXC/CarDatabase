package com.packt.cardatabase;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.packt.cardatabase.domain.Owner;
import com.packt.cardatabase.domain.OwnerRepository;

@DataJpaTest
/*
 * JUnit5 test classes and methods should have default package visibility
 * (java:S5786)
 * 
 * JUnit5 is more tolerant regarding the visibility of test classes and methods
 * than JUnit4, which required everything to be public. Test classes and methods
 * can have any visibility except private. It is however recommended to use the
 * default package visibility to improve readability.
 * 
 * Test classes, test methods, and lifecycle methods are not required to be
 * public, but they must not be private.
 * 
 * It is generally recommended to omit the public modifier for test classes,
 * test methods, and lifecycle methods unless there is a technical reason for
 * doing so – for example, when a test class is extended by a test class in
 * another package. Another technical reason for making classes and methods
 * public is to simplify testing on the module path when using the Java Module
 * System.
 * 
 * — JUnit5 User Guide
 * 
 * What is the potential impact?
 * The code will be non-conventional and readability can be slightly affected.
 * 
 * Exceptions
 * This rule does not raise an issue when the visibility is set to private,
 * because private test methods and classes are systematically ignored by
 * JUnit5, without a proper warning. In this case, there is also an impact on
 * reliability and so it is handled by the rule {rule:java:S5810}.
 */
// public class OwnerRepositoryTest {
class OwnerRepositoryTest {
    @Autowired
    private OwnerRepository repository;

    @Test
    void saveOwner() {
        repository.save(new Owner("Lucy", "Smith"));
        /*
         * Chained AssertJ assertions should be simplified to the corresponding
         * dedicated assertion (java:S5838)
         */
        /*
         * assertThat(
         * repository.findByFirstname("Lucy").isPresent()
         * ).isTrue();
         */
        assertThat(repository.findByFirstname("Lucy")).isPresent();
    }

    @Test
    void deleteOweners() {
        repository.save(new Owner("Lisa", "Morrison"));
        repository.deleteAll();
        /**
         * Chained AssertJ assertions should be simplified to the corresponding
         * dedicated assertion (java:S5838)
         */
        // assertThat(repository.count()).isEqualTo(0);
        assertThat(repository.count()).isZero();
    }
}
