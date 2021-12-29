package dev.lankydan.people.graphql

import graphql.GraphQL
import graphql.schema.GraphQLSchema
import graphql.schema.idl.RuntimeWiring
import graphql.schema.idl.SchemaGenerator
import graphql.schema.idl.SchemaParser
import graphql.schema.idl.TypeRuntimeWiring.newTypeWiring
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.io.File

/**
 * Configures an instance of [GraphQL] by reading the `schema.graphqls` resources file and registering all [TypedDataFetcher] instances.
 */
@Configuration
class GraphQLConfiguration(private val dataFetchers: List<TypedDataFetcher<*>>) {

  @Bean
  fun graphQL(): GraphQL {
    val typeRegistry = SchemaParser().parse(readSchema())
    val runtimeWiring = buildWiring()
    val schemaGenerator = SchemaGenerator()
    val graphQLSchema: GraphQLSchema = schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring)
    return GraphQL.newGraphQL(graphQLSchema).build()
  }

  private fun readSchema(): String {
    return this::class.java.classLoader.getResource("schema.graphqls")
      ?.let { url -> File(url.toURI()).readText() }
      ?: throw IllegalStateException("The resource does not exist")
  }

  /**
   * Loops through all injected [TypedDataFetcher] instances and includes them in the output [RuntimeWiring] instance.
   */
  private fun buildWiring(): RuntimeWiring {
    val wiring = RuntimeWiring.newRuntimeWiring()
    for (dataFetcher in dataFetchers) {
      wiring.type(newTypeWiring(dataFetcher.typeName).dataFetcher(dataFetcher.fieldName, dataFetcher))
    }
    return wiring.build()
  }
}