package com.mkyong.reactive.controller;

import com.mkyong.reactive.Cinema;
import com.mkyong.reactive.MonoListOfMovies;
import com.mkyong.reactive.Movie;
import com.mkyong.reactive.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.spring5.context.webflux.IReactiveDataDriverContextVariable;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    @RequestMapping("/many")
    public String index(final Model model) {

        // loads 1 and display 1, stream data, data driven mode.
        IReactiveDataDriverContextVariable reactiveDataDrivenMode =
                new ReactiveDataDriverContextVariable(movieRepository.findAll(), 1);

        model.addAttribute("movies", reactiveDataDrivenMode);

        // classic, wait repository loaded all and display it.
        //model.addAttribute("movies", movieRepository.findAll());

        return "many";

    }

    @RequestMapping("/one")
    public String index2(final Model model) {
        model.addAttribute("movie", movieRepository.findOne());
        return "index";
    }

    @RequestMapping("/findListOfMoviesAsMono")
    public String index3(final Model model) {
        model.addAttribute("movies",
                movieRepository.findListOfMoviesAsMono()
                        .flatMapMany(Flux::fromIterable)
        );
        return "many";
    }

    @GetMapping("/flux")
    public String index4(final Model model) {
        Flux<Movie> flux = movieRepository.findMonoListOfMoviesAsMono()
                .map(MonoListOfMovies::getMovieList)
                .flatMapMany(Flux::fromIterable)
                .log();
        model.addAttribute("movies", flux);
        return "many";
    }

    //    @RequestMapping("/") //Exception evaluating SpringEL expression: "movie.name" (template: "many" - line 28, col 21)
//	at org.thymeleaf.spring5.expression.SPELVariableExpressionEvaluator.evaluate(SPELVariableExpressionEvaluator.java:290) ~[thymeleaf-spring5-3.0.11.RELEASE.jar:3.0.11.RELEASE]
//    public String index5(final Model model) {
//        Cinema cinema = new Cinema("cinema name", movieRepository.findMonoListOfMoviesAsMono());
//        model.addAttribute("movies", cinema.getListOfMoviesMono());
//        return "many";
//    }
    @GetMapping("/")
    public String index6(final Model model) {
        Mono<MonoListOfMovies> monoListOfMoviesMono = movieRepository.findMonoListOfMoviesAsMono();
        model.addAttribute("movies", monoListOfMoviesMono);
        return "monolist";
    }
}