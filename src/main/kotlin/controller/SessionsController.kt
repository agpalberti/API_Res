package com.dam.api.controller

import com.dam.api.model.Movie
import com.dam.api.model.Session
import com.dam.api.service.api.SesionServiceAPI
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * ### Controlador de Sessions
 * Controlador de la API para el recurso de sessions.
 * - ENTRYPOINT: /api/v1/sessions
 * @author Alejandro González Parra
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/sessions")
class SessionsController {

    /**
     * Inyección de dependencia del servicio para [Session]
     * @see [SesionServiceAPI]
     */
    @Autowired
    lateinit var sessionService: SesionServiceAPI;

    /**
     * ### GET ALL SESSIONS
     * Función para obtener una lista de [Session]
     * - HTTP method: GET
     * - ENDPOINT: /api/v1/sessions
     * @return [ResponseEntity] con una [List] de [Session]
     */
    @GetMapping()
    fun getAllSessions(): ResponseEntity<List<Session>> {
        return ResponseEntity.ok(sessionService.getAllSessions(sessionService.all))
    }

    /**
     * ### GET FUTURE SESSIONS
     * Función para obtener una lista de [Session]
     * - HTTP method: GET
     * - ENDPOINT: /api/v1/sessions/future
     * @return [ResponseEntity] con una [List] de [Session]
     */
    @GetMapping("/future")
    fun getFutureSessions(): ResponseEntity<List<Session>> {
        val sessions = sessionService.getFutureSessions(sessionService.all)
        return if (sessions.isNotEmpty()) {
            ResponseEntity.ok(sessions)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    /**
     * ### GET TODAY SESSIONS
     * Función para obtener una lista de [Session] de hoy
     * - HTTP method: GET
     * - ENDPOINT: /api/v1/sessions/today
     * @return [ResponseEntity] con una [List] de [Session]
     */
    @GetMapping("/today")
    fun getTodaySessions(): ResponseEntity<List<Session>> {
        val sessions = sessionService.getTodaySessions(sessionService.all)
        return if (sessions.isNotEmpty()) {
            ResponseEntity.ok(sessions)
        } else {
            ResponseEntity.notFound().build()
        }
    }


}