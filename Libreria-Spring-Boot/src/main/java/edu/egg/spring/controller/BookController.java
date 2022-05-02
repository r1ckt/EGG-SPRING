package edu.egg.spring.controller;

import edu.egg.spring.entity.Book;
import edu.egg.spring.service.AuthorService;
import edu.egg.spring.service.PublisherService;
import edu.egg.spring.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final PublisherService publisherService;

    @GetMapping
    public ModelAndView getBooks(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("book-table");
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);

        if (inputFlashMap != null) mav.addObject("success", inputFlashMap.get("success"));

        mav.addObject("books", bookService.getAll());
        return mav;
    }

    @GetMapping("/book-form")
    public ModelAndView getForm() {
        ModelAndView mav = new ModelAndView("book-form");
        mav.addObject("book", new Book());
        mav.addObject("author", authorService.getAll());
        mav.addObject("publisher", publisherService.getAll());
        mav.addObject("action", "create");
        return mav;
    }

    @GetMapping("/book-form/{id}")
    public ModelAndView getForm(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("book-form");
        mav.addObject("book", bookService.getById(id));
        mav.addObject("author", authorService.getAll());
        mav.addObject("publisher", publisherService.getAll());
        mav.addObject("action", "update");
        return mav;
    }

    @PostMapping("/create")
    public RedirectView create(Book book, RedirectAttributes attributes) {
        RedirectView redirect = new RedirectView("/books");
        bookService.create(book);
        attributes.addFlashAttribute("success", "The operation has been carried out successfully");
        return redirect;
    }

    @PostMapping("/update")
    public RedirectView update(Book book, RedirectAttributes attributes) {
        RedirectView redirect = new RedirectView("/books");
        bookService.update(book);
        attributes.addFlashAttribute("success", "The operation has been carried out successfully");
        return redirect;
    }

    @PostMapping("/delete/{id}")
    public RedirectView delete(@PathVariable Long id) {
        RedirectView redirect = new RedirectView("/books");
        bookService.deleteById(id);
        return redirect;
    }
}
