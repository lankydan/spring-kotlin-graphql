package dev.lankydan.people.graphql.fetchers

import dev.lankydan.people.data.PersonRepository
import dev.lankydan.people.data.RelationshipRepository
import dev.lankydan.people.graphql.TypedDataFetcher
import dev.lankydan.people.graphql.schema.dtos.PersonDTO
import dev.lankydan.people.graphql.schema.dtos.RelationshipDTO
import graphql.schema.DataFetchingEnvironment
import org.springframework.stereotype.Component

@Component
class PersonRelationshipsDataFetcher(
  private val personRepository: PersonRepository,
  private val relationshipRepository: RelationshipRepository
) : TypedDataFetcher<List<RelationshipDTO>> {

  // The name of the schema type
  override val typeName = "Person"

  // The field that I want this data fetcher to retrieve data for
  override val fieldName = "relationships"

  override fun get(environment: DataFetchingEnvironment): List<RelationshipDTO> {
    val source = environment.getSource<PersonDTO>()
    return source.relationships.ifEmpty {
      relationshipRepository.findByPersonId(environment.getSource<PersonDTO>().id).map {
        RelationshipDTO(
          relation = personRepository.findById(it.relatedPerson.id)
            .map { person -> PersonDTO(person.id, person.firstName, person.lastName, emptyList()) }
            .get(),
          relationship = it.relation
        )
      }
    }
  }
}