package com.dam.api.service.impl

import com.dam.api.commons.impl.GenericServiceImpl
import com.dam.api.model.Session
import com.dam.api.repository.SessionRepository
import com.dam.api.service.api.SesionServiceAPI
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class SessionServiceImpl : SesionServiceAPI, GenericServiceImpl<Session, Long>() {

    @Autowired
    lateinit var session: SessionRepository
    override val dao: CrudRepository<Session, Long>
        get() = session

    override fun getFutureSessions(sessions: List<Session>?): List<Session> =
        sessions?.filter { it.date.isAfter(LocalDate.now()) } ?: emptyList()

    override fun getTodaySessions(sessions: List<Session>?): List<Session> =
        sessions?.filter { it.date.isEqual(LocalDate.now()) } ?: emptyList()

    override fun getAllSessions(sessions: List<Session>?): List<Session> = sessions ?: emptyList()
}