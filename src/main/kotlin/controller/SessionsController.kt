package com.dam.api.controller

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
    @Autowired
    lateinit var sessionService: SesionServiceAPI;

    @GetMapping()
    fun getAllSessions(): ResponseEntity<List<Session>> {
        return ResponseEntity.ok(sessionService.getAllSessions(sessionService.all))
    }

    @GetMapping("/future")
    fun getAllSessionsSinceToday(): ResponseEntity<List<Session>> {
        val sessions = sessionService.getSessionsSinceToday(sessionService.all)
        return if (sessions.isNotEmpty()) {
            ResponseEntity.ok(sessions)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @GetMapping("/today")
    fun getAllSessionsFromToday(): ResponseEntity<List<Session>> {
        val sessions = sessionService.getTodaySessions(sessionService.all)
        return if (sessions.isNotEmpty()) {
            ResponseEntity.ok(sessions)
        } else {
            ResponseEntity.notFound().build()
        }
    }


}