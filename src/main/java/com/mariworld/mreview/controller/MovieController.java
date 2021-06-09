package com.mariworld.mreview.controller;

import com.mariworld.mreview.dto.MovieDTO;
import com.mariworld.mreview.dto.PageRequestDTO;
import com.mariworld.mreview.dto.PageResultDTO;
import com.mariworld.mreview.service.MovieService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Slf4j
@RequestMapping("/movie")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/register")
    public void register() {}

    @PostMapping("/register")
    public String register(MovieDTO movieDTO , RedirectAttributes rttr){
        Long mno = movieService.register(movieDTO);
        rttr.addFlashAttribute("msg", mno);
        return "redirect:/movie/list";
    }

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model){

        log.info("movieService.getList(pageRequestDTO)" + movieService.getList(pageRequestDTO));
         model.addAttribute("result", movieService.getList(pageRequestDTO));
    }

    @GetMapping("/read")
    public void read(@ModelAttribute("requestDTO") PageRequestDTO pageRequestDTO,
                     Model model , Long mno){

        model.addAttribute("dto", movieService.getMovie(mno));
    }
}
