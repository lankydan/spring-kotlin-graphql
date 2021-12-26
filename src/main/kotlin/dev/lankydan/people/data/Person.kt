package dev.lankydan.people.data

import java.util.*
import javax.persistence.*

@Entity(name = "Person")
@Table(name = "people")
data class Person(
  @Id
  val id: UUID,
  @Column(name = "first_name")
  val firstName: String,
  @Column(name = "last_name")
  val lastName: String,
  @OneToMany(mappedBy = "personId", fetch = FetchType.LAZY)
  val relationships: Set<Relationship>
)