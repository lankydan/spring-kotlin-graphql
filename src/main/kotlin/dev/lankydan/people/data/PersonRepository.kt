package dev.lankydan.people.data

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import java.util.*

interface PersonRepository : CrudRepository<Person, UUID> {

  fun findAllByFirstName(firstName: String): List<Person>

  @Query("SELECT p FROM Person p LEFT JOIN FETCH p.relationships r LEFT JOIN FETCH r.relatedPerson rp WHERE p.id = :id")
  fun findByIdEagerly(id: UUID): Optional<Person>
}