package dev.lankydan.people.graphql.fetchers

import dev.lankydan.people.data.PersonRepository
import dev.lankydan.people.graphql.TypedDataFetcher
import dev.lankydan.people.graphql.schema.dtos.PersonDTO
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
//    return if (selectionSet.contains("relationships/*")) {
//
//    } else {
//      personRepository.findById(UUID.fromString(environment.getArgument("id")))
//    }
    return personRepository.findById(UUID.fromString(environment.getArgument("id")))
      .map { PersonDTO(it.id, it.firstName, it.lastName) }
  }
}