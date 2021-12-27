package dev.lankydan.people.data

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import java.util.*

interface RelationshipRepository : CrudRepository<Relationship, UUID> {

  @Query("SELECT r FROM Relationship r LEFT JOIN FETCH r.relatedPerson p WHERE r.personId = :personId")
  fun findAllByPersonId(personId: UUID): List<Relationship>
}