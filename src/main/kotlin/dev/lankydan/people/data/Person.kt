package dev.lankydan.people.data

import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity(name = "people")
data class Person(
  @Id
  val id: UUID,
  @Column(name = "first_name")
  val firstName: String,
  @Column(name = "last_name")
  val lastName: String
)