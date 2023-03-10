package com.dam.api.model

import jakarta.persistence.*
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDate

@Entity
@Table(name="session")
@EntityListeners(AuditingEntityListener::class)
class Session(
    // Id autogenerado
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,
    @Column(name="room_id")
    var room_id: Long,
    @Column(name="date")
    var date: LocalDate
)