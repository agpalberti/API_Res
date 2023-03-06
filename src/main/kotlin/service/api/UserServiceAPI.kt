package com.dam.api.service.api

import com.dam.api.commons.api.GenericServiceAPI
import com.dam.api.model.User

interface UserServiceAPI : GenericServiceAPI<User, Long> {
   fun getUserByNick(nick: String): User?
   fun getUsersByNick(nick: String): List<User>?
   fun deleteUsersByNick(nick: String):Boolean
   fun insertUser(user: User)
   fun updateUser(nick: String,user: User):Boolean
}