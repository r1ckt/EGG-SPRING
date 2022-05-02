package edu.egg.spring.controller;
import edu.egg.spring.entity.Publisher;
import edu.egg.spring.service.PublisherService;
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
@RequestMapping("/publishers")
@RequiredArgsConstructor
public class PublisherController {

    private final PublisherService publisherService;

    @GetMapping
    public ModelAndView getPublishers(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("publisher-table");
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);

        if (inputFlashMap != null) mav.addObject("success", inputFlashMap.get("success"));

        mav.addObject("publishers", publisherService.getAll());
        return mav;
    }

    @GetMapping("/publisher-form")
    public ModelAndView getForm() {
        ModelAndView mav = new ModelAndView("publisher-form");
        mav.addObject("publisher", new Publisher());
        mav.addObject("action", "create");
        return mav;
    }

    @GetMapping("/publisher-form/{id}")
    public ModelAndView getForm(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("publisher-form");
        mav.addObject("publisher", publisherService.getById(id));
        mav.addObject("action", "update");
        return mav;
    }

    @PostMapping("/create")
    public RedirectView create(Publisher publisher, RedirectAttributes attributes) {
        RedirectView redirect = new RedirectView("/publishers");
        publisherService.create(publisher);
        attributes.addFlashAttribute("success", "The operation has been carried out successfully");
        return redirect;
    }

    @PostMapping("/update")
    public RedirectView update(Publisher publisher, RedirectAttributes attributes) {
        RedirectView redirect = new RedirectView("/publishers");
        publisherService.update(publisher);
        attributes.addFlashAttribute("success", "The operation has been carried out successfully");
        return redirect;
    }

    @PostMapping("/delete/{id}")
    public RedirectView delete(@PathVariable Long id) {
        RedirectView redirect = new RedirectView("/publishers");
        publisherService.deleteById(id);
        return redirect;
    }
}


