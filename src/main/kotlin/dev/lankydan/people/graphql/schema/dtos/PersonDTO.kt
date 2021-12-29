package dev.lankydan.people.graphql.schema.dtos

import dev.lankydan.people.data.Person
import java.util.*

/**
 * DTO representation of [Person] to be return from GraphQL queries.
 */
data class PersonDTO(
  val id: UUID,
  val firstName: String,
  val lastName: String,
  val relationships: List<RelationshipDTO>
)

fun Person.toDTO(mapRelationships: Boolean): PersonDTO {
  return PersonDTO(
    id,
    firstName,
    lastName,
    relationships = if (mapRelationships) {
      relationships.map { it.toDTO() }
    } else {
      emptyList()
    }
  )
}