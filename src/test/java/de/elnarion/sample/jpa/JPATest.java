package de.elnarion.sample.jpa;

import de.elnarion.sample.jpa.domain.bidirectional.Book;
import de.elnarion.sample.jpa.domain.bidirectional.Review;
import de.elnarion.sample.jpa.domain.unidirectional.Audience;
import de.elnarion.sample.jpa.domain.unidirectional.OrderItem;
import de.elnarion.sample.jpa.domain.unidirectional.Speaker;
import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class JPATest {

    static EntityManagerFactory factory;
    EntityManager manager;

    @BeforeAll
    static void initEntityManagerFactory() {
        factory = Persistence.createEntityManagerFactory("jpa-test");
    }

    private Audience createAudienceWithSpeakers() {
        Speaker speaker1 = new Speaker();
        speaker1.setCategory("category1");
        speaker1.setName("speaker name 1");
        Speaker speaker2 = new Speaker();
        speaker2.setCategory("category1");
        speaker2.setName("speaker name 1");
        Audience audience = new Audience();
        audience.setName("Super audience");
        audience.addSpeaker(speaker1);
        audience.addSpeaker(speaker2);
        return audience;
    }

    @BeforeEach
    void initEntityManager() {
        manager = factory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
    }

    @AfterEach
    void tearDownEntityManager() {
        manager.getTransaction().commit();
    }

    @Test
    @Order(1)
    void testSaveBook() {
        // ARRANGE
        Book bookWithReviews = createBookWithReviews();

        // ACT
        manager.persist(bookWithReviews);
        manager.flush();

        // ASSERT
        assertNotNull(bookWithReviews.getId());
        assertNotNull(bookWithReviews.getReviews().get(0).getId());

    }

    @Test
    @Order(2)
    void testSaveAudience() {
        // ARRANGE
        Audience audience = createAudienceWithSpeakers();


        // ACT
        manager.persist(audience);
        manager.flush();

        // ASSERT
        assertNotNull(audience.getId());
        assertNotNull(audience.getSpeakers().get(0).getId());

    }

    @Test
    @Order(3)
    void testSaveOrder() {
        // ARRANGE
        de.elnarion.sample.jpa.domain.unidirectional.Order order = new de.elnarion.sample.jpa.domain.unidirectional.Order();
        order.setOrderName("testorder");
        OrderItem orderItem = new OrderItem();
        orderItem.setItemName("item2");
        orderItem.setOrder(order);

        // ACT
        manager.persist(orderItem);

        // ASSERT
        assertNotNull(orderItem.getId());
        assertNotNull(orderItem.getOrder().getId());
    }

    private Book createBookWithReviews() {
        Review review1 = new Review();
        review1.setAuthor("ReviewAuthor");
        review1.setComment("ReviewComment");

        Review review2 = new Review();
        review2.setAuthor("ReviewAuthor");
        review2.setComment("ReviewComment");

        Book book = new Book();
        book.setIsbn("asdf");
        book.setPublishingDate(LocalDate.now());
        book.setTitle("Super title");

        book.addReview(review1);
        book.addReview(review2);
        return book;
    }

}
