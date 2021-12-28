package dev.lankydan.people.graphql.fetchers

import dev.lankydan.people.data.PersonRepository
import dev.lankydan.people.graphql.TypedDataFetcher
import dev.lankydan.people.graphql.schema.dtos.PersonDTO
import dev.lankydan.people.graphql.schema.dtos.toDTO
import graphql.schema.DataFetchingEnvironment
import org.springframework.stereotype.Component

@Component
class PeopleDataFetcher(private val personRepository: PersonRepository) : TypedDataFetcher<List<PersonDTO>> {

  override val typeName = "Query"
  override val fieldName = "people"

  override fun get(environment: DataFetchingEnvironment): List<PersonDTO> {
    return personRepository.findAll().map { it.toDTO(mapRelationships = false) }
  }
}