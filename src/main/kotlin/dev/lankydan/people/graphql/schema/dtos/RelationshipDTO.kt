package dev.lankydan.people.graphql.schema.dtos

import dev.lankydan.people.data.Relationship

/**
 * DTO representation of [Relationship] to be return from GraphQL queries.
 */
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