package dev.lankydan.people.graphql

import graphql.schema.DataFetcher

interface TypedDataFetcher<T> : DataFetcher<T> {

  val typeName: String

  val fieldName: String
}