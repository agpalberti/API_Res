package com.dam.api.controller

import com.dam.api.dto.UserDTO
import com.dam.api.model.User
import com.dam.api.service.api.UserServiceAPI
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * ### Controlador de Users
 * Controlador de la API para el recurso de users.
 * - ENTRYPOINT: /api/v1/users
 * @author Alejandro González Parra
 */
@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin("*")
class UsersController {

    @Autowired
    lateinit var userService: UserServiceAPI;

    @GetMapping
    fun getAllUsers(): ResponseEntity<List<User>> = ResponseEntity.ok(userService.all)

    @GetMapping("/{nick}")
    fun getUserByNick(@PathVariable nick: String): ResponseEntity<List<User>?>  = ResponseEntity.ok(userService.getUsersByNick(nick))

    @PostMapping
    fun createUser(@RequestBody request: UserDTO): ResponseEntity<Unit?>? {
        userService.insertUser(User(request.nick,request.email,request.profilePicture,null))
        return ResponseEntity(HttpStatus.CREATED)
    }

    @PutMapping("/{nick}")
    fun updateUser(@PathVariable("nick") nick: String, @RequestBody request: UserDTO): ResponseEntity<Unit?>? {
        return if (userService.updateUser(nick, User(request.nick,request.email,request.profilePicture,null))) {
            ResponseEntity(HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @DeleteMapping("/{nick}")
    fun deleteUserByNick(@PathVariable nick: String): ResponseEntity<Unit> {
            return if(userService.deleteUsersByNick(nick)){
            ResponseEntity.ok().build()
        } else
            ResponseEntity(HttpStatus.NOT_FOUND)
    }


}
