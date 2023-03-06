package com.dam.api.controller

import com.dam.api.model.Movie
import com.dam.api.service.impl.MovieServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * ### Controlador de Movies
 * Controlador de la API para el recurso de movies.
 * - ENTRYPOINT: /api/v1/movies
 * @author Alejandro González Parra
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/movies")
class MoviesController {

    /**
     * Inyección de dependencia del servicio para [Movie]
     * @see [MovieServiceAPI]
     */
    @Autowired
    lateinit var moviesService: MovieServiceImpl;


    /**
     * ### GET ALL MOVIES
     * Función para obtener una lista de [Movie]
     * - HTTP method: GET
     * - ENDPOINT: /api/v1/movies
     * @return [ResponseEntity] con una [List] de [Movie]
     */
    @GetMapping()
    fun getAllMovies(): ResponseEntity<List<Movie>?> {
        var movies = moviesService.all
        return ResponseEntity.ok(movies)
    }

    /**
     * ### GET ONE MOVIE
     * Función para obtener una [Movie] del sistema.
     *
     * - HTTP method: GET
     * - ENDPOINT: /api/v1/movies/{id}
     *
     * @param id: [Long]
     * @return [ResponseEntity] con una [Movie]
     */
    @GetMapping("/{id}")
    fun getMovieById(@PathVariable id: Long): ResponseEntity<Movie> {
        val movie = moviesService.dao.findById(id)
        return if (movie.isPresent) {
            ResponseEntity.ok(movie.get())
        } else {
            ResponseEntity.notFound().build()
        }
    }
}