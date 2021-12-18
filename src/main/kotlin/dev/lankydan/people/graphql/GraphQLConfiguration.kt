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
import java.net.URL

@Configuration
class GraphQLConfiguration(private val dataFetchers: List<TypedDataFetcher<*>>) {

  @Bean
  fun graphQL(): GraphQL {
    val url: URL = this::class.java.classLoader.getResource("schema.graphqls") ?: throw IllegalStateException("The resource does not exist")
    val sdl: String = File(url.toURI()).readText()
    val graphQLSchema: GraphQLSchema = buildSchema(sdl)
    return GraphQL.newGraphQL(graphQLSchema).build()
  }

  private fun buildSchema(sdl: String): GraphQLSchema {
    val typeRegistry = SchemaParser().parse(sdl)
    val runtimeWiring = buildWiring()
    val schemaGenerator = SchemaGenerator()
    return schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring)
  }

  private fun buildWiring(): RuntimeWiring {
    val wiring = RuntimeWiring.newRuntimeWiring()
    for (dataFetcher in dataFetchers) {
      wiring.type(newTypeWiring(dataFetcher.typeName).dataFetcher(dataFetcher.fieldName, dataFetcher))
    }
    return wiring.build()
  }
}