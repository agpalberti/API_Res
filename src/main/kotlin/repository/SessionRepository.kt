package com.dam.api.repositories

import com.dam.api.model.Session
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface SessionRepository : CrudRepository<Session, Long>