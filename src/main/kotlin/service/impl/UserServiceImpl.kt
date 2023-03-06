package com.dam.api.service.impl

import com.dam.api.commons.impl.GenericServiceImpl
import com.dam.api.model.User
import com.dam.api.repository.UserRepository
import com.dam.api.service.api.UserServiceAPI
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Service

@Service
class UserServiceImpl : UserServiceAPI, GenericServiceImpl<User, Long>() {
    @Autowired
    lateinit var repository: UserRepository
    override val dao: CrudRepository<User, Long>
        get() = repository

    override fun getUserByNick(nick: String): User? {
        return if (!repository.findUsersByNick(nick).isNullOrEmpty()) repository.findUsersByNick(nick)?.first()
        else null
    }
    override fun getUsersByNick(nick: String): List<User>? = repository.findUsersByNick(nick)
    override fun deleteUsersByNick(nick: String):Boolean {
        val user = getUserByNick(nick)
        return if (user!= null) {
            deleteOne(user.id!!)
            true
        } else false

    }
    override fun insertUser(user: User){ insertOne(User(user.nick,user.email,user.profilePicture,null)) }
    override fun updateUser(nick: String,user: User):Boolean{
        val updatedUser = getUserByNick(nick)
        return if (updatedUser!=null){
            updatedUser.nick = user.nick
            updatedUser.email = user.email
            updatedUser.profilePicture = user.profilePicture
            insertOne(updatedUser)
            true
        } else false
    }

}
