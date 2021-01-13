package utcn.is.assignment2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import utcn.is.assignment2.data.EventCategoryRepository;
import utcn.is.assignment2.models.EventCategory;
import javax.validation.Valid;


@Controller
@RequestMapping("eventCategories")
public class EventCategoryController {

    @Autowired
    EventCategoryRepository eventCategoryRepository;

    @GetMapping
    public String displayCategories(Model model) {
        model.addAttribute("title", "All categories");
        model.addAttribute("categories", eventCategoryRepository.findAll());
        return "eventCategories/index";
    }

    @GetMapping("create")
    public String displayCreateForm(Model model) {
        model.addAttribute("title", "Create Category");
        model.addAttribute(new EventCategory());
        return "eventCategories/create";

    }

    @PostMapping("create")
    public String processCreateForm(@ModelAttribute @Valid EventCategory eventCategory, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Create category");
            model.addAttribute(eventCategory);
            return "eventCategories/create";
        } else {
            eventCategoryRepository.save(eventCategory);
            return "redirect:";
        }
    }
}