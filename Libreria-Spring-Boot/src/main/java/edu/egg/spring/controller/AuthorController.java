package edu.egg.spring.controller;
import edu.egg.spring.entity.Author;
import edu.egg.spring.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;



@Controller
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping
    public ModelAndView getAuthors(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("author-table");
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);

        if (inputFlashMap != null) mav.addObject("success", inputFlashMap.get("success"));

        mav.addObject("authors", authorService.getAll());
        return mav;
    }

    @GetMapping("/author-form")
    public ModelAndView getForm() {
        ModelAndView mav = new ModelAndView("author-form");
        mav.addObject("author", new Author());
        mav.addObject("action", "create");
        return mav;
    }

    @GetMapping("/author-form/{id}")
    public ModelAndView getForm(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("author-form");
        mav.addObject("author", authorService.getById(id));
        mav.addObject("action", "update");
        return mav;
    }

    @PostMapping("/create")
    public RedirectView create(Author author, RedirectAttributes attributes) {
        RedirectView redirect = new RedirectView("/authors");
        authorService.create(author);
        attributes.addFlashAttribute("success", "The operation has been carried out successfully");
        return redirect;
    }

    @PostMapping("/update")
    public RedirectView update(Author author, RedirectAttributes attributes) {
        RedirectView redirect = new RedirectView("/authors");
        authorService.update(author);
        attributes.addFlashAttribute("success", "The operation has been carried out successfully");
        return redirect;
    }

    @PostMapping("/delete/{id}")
    public RedirectView delete(@PathVariable Long id) {
        RedirectView redirect = new RedirectView("/authors");
        authorService.deleteById(id);
        return redirect;
    }
}





