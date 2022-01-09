package dev.lankydan.people.graphql

import graphql.schema.DataFetcher
import graphql.schema.idl.RuntimeWiring
import org.springframework.stereotype.Component

/**
 * [TypedDataFetcher] is an instance of a [DataFetcher] that specifies the schema type and field it processes.
 *
 * Instances of [TypedDataFetcher] are registered into an instance of [RuntimeWiring] after being picked up by Spring (the instances must be
 * annotated with @[Component] or a similar annotated to be injected).
 */
interface TypedDataFetcher<T> : DataFetcher<T> {

  /**
   * The type that the [TypedDataFetcher] handles.
   *
   * Use `Query` if the [TypedDataFetcher] responds to incoming queries.
   *
   * Use a schema type name if the [TypedDataFetcher] fetches data for a single field in the specified type.
   */
  val typeName: String

  /**
   * The field that the [TypedDataFetcher] should apply to.
   *
   * If the [typeName] is `Query`, then [fieldName] will be the name of the query the [TypedDataFetcher] handles.
   *
   * If the [typeName] is a schema type, then [fieldName] should be the name of a single field in [typeName].
   */
  val fieldName: String
}