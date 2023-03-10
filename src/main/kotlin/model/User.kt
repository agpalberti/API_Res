package com.dam.api.model

import jakarta.persistence.*
import org.springframework.data.jpa.domain.support.AuditingEntityListener

@Entity
@Table(name="user")
@EntityListeners(AuditingEntityListener::class)
class User(
    // Id autogenerado
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,
    @Column(name="nick")
    var nick: String,
    @Column(name="email")
    var email: String,
    @Column(name="profilePicture")
    var profilePicture: String
)