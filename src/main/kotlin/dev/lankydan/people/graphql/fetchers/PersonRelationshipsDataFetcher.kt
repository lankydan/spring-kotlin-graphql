package dev.lankydan.people.graphql.fetchers

import dev.lankydan.people.data.RelationshipRepository
import dev.lankydan.people.graphql.TypedDataFetcher
import dev.lankydan.people.graphql.schema.dtos.PersonDTO
import dev.lankydan.people.graphql.schema.dtos.RelationshipDTO
import dev.lankydan.people.graphql.schema.dtos.toDTO
import graphql.schema.DataFetchingEnvironment
import org.springframework.stereotype.Component

/**
 * [TypedDataFetcher] that maps the "relationships" field in the "Person" schema type.
 *
 * Example query:
 *
 * ```graphql
 * query {
 *   people {
 *     firstName
 *     lastName
 *     id
 *     relationships {
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
class PersonRelationshipsDataFetcher(
  private val relationshipRepository: RelationshipRepository
) : TypedDataFetcher<List<RelationshipDTO>> {

  override val typeName = "Person"
  override val fieldName = "relationships"

  override fun get(environment: DataFetchingEnvironment): List<RelationshipDTO> {
    val source = environment.getSource<PersonDTO>()
    return source.relationships.ifEmpty {
      relationshipRepository.findAllByPersonId(source.id).map { it.toDTO() }
    }
  }
}