package dev.lankydan.people.graphql.fetchers

import dev.lankydan.people.data.PersonRepository
import dev.lankydan.people.graphql.TypedDataFetcher
import dev.lankydan.people.graphql.schema.dtos.PersonDTO
import graphql.schema.DataFetchingEnvironment
import org.springframework.stereotype.Component

@Component
class PeopleByFirstNameDataFetcher(private val personRepository: PersonRepository) : TypedDataFetcher<List<PersonDTO>> {

  override val typeName = "Query"
  override val fieldName = "peopleByFirstName"

  override fun get(environment: DataFetchingEnvironment): List<PersonDTO> {
    return personRepository.findAllByFirstName(environment.getArgument("firstName"))
      .map { PersonDTO(it.id, it.firstName, it.lastName, emptyList()) }
  }
}