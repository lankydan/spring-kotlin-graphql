package dev.lankydan.people.data

import org.springframework.data.repository.CrudRepository
import java.util.*

interface PersonRepository : CrudRepository<Person, UUID> {

  fun findAllByFirstName(firstName: String): List<Person>
}