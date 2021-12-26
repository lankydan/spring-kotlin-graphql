package dev.lankydan.people.graphql.schema.dtos

import java.util.*

data class PersonDTO(
  val id: UUID,
  val firstName: String,
  val lastName: String,
  val relationships: List<RelationshipDTO>
)