package com.dam.api.service.api

import com.dam.api.commons.api.GenericServiceAPI
import com.dam.api.model.Session

interface SesionServiceAPI : GenericServiceAPI<Session, Long> {
     fun getFutureSessions(sessions: List<Session>?): List<Session>
     fun getTodaySessions(sessions: List<Session>?): List<Session>
     fun getAllSessions(sessions: List<Session>?): List<Session>
}