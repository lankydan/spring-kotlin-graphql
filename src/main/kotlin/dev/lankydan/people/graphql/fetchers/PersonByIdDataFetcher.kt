package dev.lankydan.people.graphql.fetchers

import dev.lankydan.people.data.PersonRepository
import dev.lankydan.people.graphql.TypedDataFetcher
import dev.lankydan.people.graphql.schema.dtos.PersonDTO
import dev.lankydan.people.graphql.schema.dtos.RelationshipDTO
import graphql.schema.DataFetchingEnvironment
import graphql.schema.DataFetchingFieldSelectionSet
import org.springframework.stereotype.Component
import java.util.*

@Component
class PersonByIdDataFetcher(private val personRepository: PersonRepository) : TypedDataFetcher<Optional<PersonDTO>> {

  override val typeName = "Query"
  override val fieldName = "personById"

  override fun get(environment: DataFetchingEnvironment): Optional<PersonDTO> {
    val selectionSet: DataFetchingFieldSelectionSet = environment.selectionSet
    val person = if (selectionSet.contains("relationships/*")) {
      personRepository.findByIdEagerly(UUID.fromString(environment.getArgument("id")))
    } else {
      personRepository.findById(UUID.fromString(environment.getArgument("id")))
    }
    return person.map { person ->
      PersonDTO(
        id = person.id,
        firstName = person.firstName,
        lastName = person.lastName,
        relationships = person.relationships.map { relationship ->
          RelationshipDTO(
            relation = PersonDTO(
              id = relationship.relatedPerson.id,
              firstName = relationship.relatedPerson.firstName,
              lastName = relationship.relatedPerson.lastName,
              relationships = emptyList()
            ),
            relationship = relationship.relation
          )
        }
      )
    }
  }
}