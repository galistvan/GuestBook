package hu.igal.sample.persistence;

import hu.igal.sample.Application;
import hu.igal.sample.config.PersistenceConfig;
import hu.igal.sample.persistence.entity.Quote;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class QuoteRepositoryTests {

    @Inject
    private QuoteRepository quoteRepository;

    @Test
    public void init() {
        Quote q = new Quote();
        q.setQuoteText("hello");
        quoteRepository.addQuote(q);

        Assert.assertEquals(1, quoteRepository.findAll().size());
    }

}
