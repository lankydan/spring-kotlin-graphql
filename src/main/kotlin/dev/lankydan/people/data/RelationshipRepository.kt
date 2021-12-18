package dev.lankydan.people.data

import org.springframework.data.repository.CrudRepository
import java.util.*

interface RelationshipRepository : CrudRepository<Relationship, UUID> {

  fun findByPersonId(personId: UUID): List<Relationship>
}