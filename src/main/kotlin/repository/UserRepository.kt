package com.dam.api.repository

import com.dam.api.model.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : CrudRepository<User, Long> {
    fun findUsersByNick(nick: String): List<User>? = findAll().filter { it.nick == nick }
}