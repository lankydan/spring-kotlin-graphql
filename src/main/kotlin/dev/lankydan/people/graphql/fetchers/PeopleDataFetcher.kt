package dev.lankydan.people.graphql.fetchers

import dev.lankydan.people.data.Person
import dev.lankydan.people.data.PersonRepository
import dev.lankydan.people.graphql.TypedDataFetcher
import graphql.schema.DataFetchingEnvironment
import org.springframework.stereotype.Component

@Component
class PeopleDataFetcher(private val personRepository: PersonRepository) : TypedDataFetcher<List<Person>> {

  override val typeName = "Query"
  override val fieldName = "people"

  override fun get(environment: DataFetchingEnvironment): List<Person> {
    return personRepository.findAll().toList()
  }
}