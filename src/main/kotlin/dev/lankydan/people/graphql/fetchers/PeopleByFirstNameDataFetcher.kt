package dev.lankydan.people.graphql.fetchers

import dev.lankydan.people.data.PersonRepository
import dev.lankydan.people.graphql.TypedDataFetcher
import dev.lankydan.people.graphql.schema.dtos.PersonDTO
import dev.lankydan.people.graphql.schema.dtos.toDTO
import graphql.schema.DataFetchingEnvironment
import org.springframework.stereotype.Component

/**
 * [TypedDataFetcher] that handles incoming "peopleByFirstName" queries.
 *
 * Example query:
 *
 * ```graphql
 * query {
 *   peopleByFirstName(firstName: "Dan") {
 *     firstName
 *     lastName
 *     id
 *   }
 * }
 * ```
 */
@Component
class PeopleByFirstNameDataFetcher(private val personRepository: PersonRepository) : TypedDataFetcher<List<PersonDTO>> {

  override val typeName = "Query"
  override val fieldName = "peopleByFirstName"

  override fun get(environment: DataFetchingEnvironment): List<PersonDTO> {
    return personRepository.findAllByFirstName(environment.getArgument("firstName"))
      .map { it.toDTO(mapRelationships = false) }
  }
}