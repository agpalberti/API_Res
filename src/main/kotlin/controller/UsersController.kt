package com.dam.api.controller

import com.dam.api.dto.UserDTO
import com.dam.api.model.Movie
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

    /**
     * Inyección de dependencia del servicio para users
     * @see UserServiceAPI
     */
    @Autowired
    lateinit var userService: UserServiceAPI;

    /**
     * ### GET ALL USERS
     * Función para obtener una lista de [User]
     * - HTTP method: GET
     * - ENDPOINT: /api/v1/users
     * @return [ResponseEntity] con una [List] de [User]
     */
    @GetMapping
    fun getAllUsers(): ResponseEntity<List<User>> = ResponseEntity.ok(userService.all)

    /**
     * ### GET ONE USER
     * Función para obtener una [Movie] del sistema.
     *
     * - HTTP method: GET
     * - ENDPOINT: /api/v1/users/{nick}
     *
     * @param nick: [String]
     * @return [ResponseEntity] con una [Movie]
     */
    @GetMapping("/{nick}")
    fun getUserByNick(@PathVariable nick: String): ResponseEntity<List<User>?>  = ResponseEntity.ok(userService.getUsersByNick(nick))

    /**
     * ### INSERT USER
     * Función para insertar un nuevo [User] en el sistema.
     * Recibe los datos del nuevo usuario en el cuerpo de la petición.
     *
     * - HTTP method: POST
     * - ENDPOINT: /api/v1/users
     *
     * @param user: [UserDTO]
     * @return nothing
     */
    @PostMapping
    fun createUser(@RequestBody user: UserDTO){
        userService.insertUser(User(user.nick,user.email,user.profilePicture,null))
        ResponseEntity<Unit>(HttpStatus.CREATED)
    }

    /**
     * ### UPDATE USER
     * Función para actualizar un [User] del sistema.
     * Recibe el nick del usuario a actualizar y los nuevos datos del usuario en el cuerpo de la petición.
     *
     * - HTTP method: PUT
     * - ENDPOINT: /api/v1/users/{nick}
     *
     * @param nick: [String]
     * @param user: [UserDTO]
     * @return nothing
     */
    @PutMapping("/{nick}")
    fun updateUser(@PathVariable("nick") nick: String, @RequestBody user: UserDTO){
        if (userService.updateUser(nick, User(user.nick,user.email,user.profilePicture,null))) {
            ResponseEntity<Unit>(HttpStatus.OK)
        } else {
            ResponseEntity<Unit>(HttpStatus.NOT_FOUND)
        }
    }

    /**
     * ### DELETE USER
     * Función para eliminar un [User] del sistema.
     * Recibe el nick del usuario a actualizar en la ruta de la petición
     *
     * - HTTP method: DELETE
     * - ENDPOINT: /api/v1/users/{nick}
     *
     * @param nick: [String]
     * @return nothing
     */
    @DeleteMapping("/{nick}")
    fun deleteUserByNick(@PathVariable nick: String){
        if(userService.deleteUsersByNick(nick)){
            ResponseEntity.ok().build()
        } else
            ResponseEntity<Unit>(HttpStatus.NOT_FOUND)
    }

}
