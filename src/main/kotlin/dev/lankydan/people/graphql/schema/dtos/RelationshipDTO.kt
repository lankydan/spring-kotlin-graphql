package dev.lankydan.people.graphql.schema.dtos

data class RelationshipDTO(
  val relation: PersonDTO,
  val relationship: String
)