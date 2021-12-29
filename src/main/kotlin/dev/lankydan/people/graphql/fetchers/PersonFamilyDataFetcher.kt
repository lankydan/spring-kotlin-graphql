package dev.lankydan.people.graphql.fetchers

import dev.lankydan.people.data.RelationshipRepository
import dev.lankydan.people.graphql.TypedDataFetcher
import dev.lankydan.people.graphql.schema.dtos.PersonDTO
import dev.lankydan.people.graphql.schema.dtos.RelationshipDTO
import dev.lankydan.people.graphql.schema.dtos.toDTO
import graphql.schema.DataFetchingEnvironment
import org.springframework.stereotype.Component

/**
 * [TypedDataFetcher] that maps the "family" field in the "Person" schema type.
 *
 * Example query:
 *
 * ```graphql
 * query {
 *   people {
 *     firstName
 *     lastName
 *     id
 *     family {
 *       relation {
 *         firstName
 *         lastName
 *         id
 *       }
 *       relationship
 *     }
 *   }
 * }
 * ```
 */
@Component
class PersonFamilyDataFetcher(
  private val relationshipRepository: RelationshipRepository
) : TypedDataFetcher<List<RelationshipDTO>> {

  private companion object {
    val family = setOf("Husband", "Wife", "Brother", "Sister", "Father", "Mother")
  }

  override val typeName = "Person"
  override val fieldName = "family"

  override fun get(environment: DataFetchingEnvironment): List<RelationshipDTO> {
    val source = environment.getSource<PersonDTO>()
    return source.relationships.ifEmpty {
      relationshipRepository.findAllByPersonIdAndInRelationships(source.id, family).map { it.toDTO() }
    }
  }
}