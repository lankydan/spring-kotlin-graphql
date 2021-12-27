package dev.lankydan.people.graphql.fetchers

import dev.lankydan.people.data.RelationshipRepository
import dev.lankydan.people.graphql.TypedDataFetcher
import dev.lankydan.people.graphql.schema.dtos.PersonDTO
import dev.lankydan.people.graphql.schema.dtos.RelationshipDTO
import graphql.schema.DataFetchingEnvironment
import org.springframework.stereotype.Component

@Component
class PersonRelationshipsDataFetcher(
  private val relationshipRepository: RelationshipRepository
) : TypedDataFetcher<List<RelationshipDTO>> {

  // The name of the schema type
  override val typeName = "Person"

  // The field that I want this data fetcher to retrieve data for
  override val fieldName = "relationships"

  override fun get(environment: DataFetchingEnvironment): List<RelationshipDTO> {
    val source = environment.getSource<PersonDTO>()
    return source.relationships.ifEmpty {
      relationshipRepository.findAllByPersonId(source.id).map { relationship ->
        RelationshipDTO(
          relation = PersonDTO(
            id = relationship.relatedPerson.id,
            firstName = relationship.relatedPerson.firstName,
            lastName = relationship.relatedPerson.lastName,
            emptyList()
          ),
          relationship = relationship.relation
        )
      }
    }
  }
}