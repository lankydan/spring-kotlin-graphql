package dev.lankydan.people.graphql.schema.dtos

import dev.lankydan.people.data.Relationship

data class RelationshipDTO(
  val relation: PersonDTO,
  val relationship: String
)

fun Relationship.toDTO(): RelationshipDTO {
  return RelationshipDTO(
    relation = relatedPerson.toDTO(mapRelationships = false),
    relationship
  )
}