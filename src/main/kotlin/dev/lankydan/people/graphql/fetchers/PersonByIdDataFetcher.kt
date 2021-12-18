package dev.lankydan.people.graphql.fetchers

import dev.lankydan.people.data.Person
import dev.lankydan.people.data.PersonRepository
import dev.lankydan.people.graphql.TypedDataFetcher
import graphql.schema.DataFetchingEnvironment
import org.springframework.stereotype.Component
import java.util.*

@Component
class PersonByIdDataFetcher(private val personRepository: PersonRepository) : TypedDataFetcher<Optional<Person>> {

  override val typeName = "Query"
  override val fieldName = "personById"

  override fun get(environment: DataFetchingEnvironment): Optional<Person> {
    return personRepository.findById(UUID.fromString(environment.getArgument("id")))
  }
}