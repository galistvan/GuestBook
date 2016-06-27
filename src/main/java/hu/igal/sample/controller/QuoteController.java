package hu.igal.sample.controller;

import hu.igal.sample.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hu.igal.sample.Quote;

import java.util.List;

@Controller
public class QuoteController {

	@Autowired
	private QuoteService quoteService;

	@RequestMapping(value = "/quotes", method = RequestMethod.GET)
	public String showQuoteForm(Model model) {
		model.addAttribute("quote", new Quote());
		List<Quote> quotes = quoteService.getQuotes();
		model.addAttribute("quotes", quotes);
		return "quotes";
	}

	@RequestMapping(value = "/quote", method = RequestMethod.POST)
	public String processQuote(Model model, @ModelAttribute(value = "quote") Quote quote) {
		quoteService.addQuote(quote);
		return "redirect:/quotes";
	}
}
