package dev.lankydan.people.data

import java.util.*
import javax.persistence.*

@Entity(name = "Relationship")
@Table(name = "relationships")
data class Relationship(
  @Id
  val id: UUID,
  @Column(name = "person_id")
  val personId: UUID,
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "related_person_id")
  val relatedPerson: Person,
  @Column(name = "relationship")
  val relationship: String
)