package dev.lankydan.people.data

import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity(name = "relationships")
data class Relationship(
  @Id
  val id: UUID,
  @Column(name = "person_id")
  val personId: UUID,
  @Column(name = "related_person_id")
  val relatedPersonId: UUID,
  @Column(name = "relation")
  val relation: String
)