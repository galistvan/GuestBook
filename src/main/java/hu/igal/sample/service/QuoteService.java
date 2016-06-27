package hu.igal.sample.service;

import hu.igal.sample.Quote;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class QuoteService {
    LinkedList<Quote> quotes = new LinkedList<Quote>();

    public void addQuote(Quote quote) {
        quotes.add(quote);
    }

    public List<Quote> getQuotes(){
        return (List<Quote>) quotes.clone();
    }
}
