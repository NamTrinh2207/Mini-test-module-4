package com.example.controller;

import com.example.model.Painting;
import com.example.service.IPaintingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/paintings")
public class PaintingController {
    @Autowired
    private IPaintingService paintingService;

    @GetMapping()
    public ModelAndView showAll() {
        List<Painting> paintingList;
        ModelAndView modelAndView = new ModelAndView("/showAll");
        paintingList = paintingService.showAll();
        modelAndView.addObject("paintings", paintingList);
        return modelAndView;
    }

    //add new painting
    @GetMapping("/save")
    public String save(Model model) {
        model.addAttribute("paintings", new Painting());
        return "/save";
    }

    @PostMapping("save")
    public ModelAndView save(Painting painting) {
        ModelAndView modelAndView = new ModelAndView("redirect:/paintings");
        painting.setId((int) (Math.random() * 10000));
        paintingService.save(painting);
        return modelAndView;
    }

    //delete
    @GetMapping("/delete")
    public ModelAndView delete(Painting painting) {
        ModelAndView modelAndView = new ModelAndView("redirect:/paintings");
        paintingService.delete(painting.getId());
        return modelAndView;
    }

    //update
    @GetMapping("/{id}/update")
    public ModelAndView update(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("/update");
        modelAndView.addObject("paintings", paintingService.findById(id));
        return modelAndView;
    }

    @PostMapping("/update")
    public String update(Painting painting) {
        paintingService.update(painting.getId(), painting);
        return "redirect:/paintings";
    }

    //view
    @GetMapping("/{id}/view")
    public ModelAndView view(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("/view");
        modelAndView.addObject("paintings", paintingService.findById(id));
        return modelAndView;
    }

    //search
    @GetMapping("/search")
    public ModelAndView search(String name) {
        ModelAndView modelAndView = new ModelAndView("/showAll");
        modelAndView.addObject("paintings", paintingService.search(name));
        return modelAndView;
    }
}
