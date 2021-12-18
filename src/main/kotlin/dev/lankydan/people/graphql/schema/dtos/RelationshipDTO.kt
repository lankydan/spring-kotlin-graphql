package dev.lankydan.people.graphql.schema.dtos

import dev.lankydan.people.data.Person

data class RelationshipDTO(
  val relation: Person,
  val relationship: String
)