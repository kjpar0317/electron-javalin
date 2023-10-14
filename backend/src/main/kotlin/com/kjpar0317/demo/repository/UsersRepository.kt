package com.kjpar0317.demo.repository

import com.kjpar0317.demo.model.Users
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.SqlExpressionBuilder.inList
import org.jetbrains.exposed.sql.transactions.transaction

private object Users : Table() {
    val id: Column<String> = varchar("id", 255).primaryKey()
    val name: Column<String> = varchar("name", 255)
    val password: Column<String> = varchar("password", 255)
    val role: Column<String> = varchar("role", 255)
//    val createdAt: Column<DateTime> = date("created_at")
//    val updatedAt: Column<DateTime> = date("updated_at")

    fun toDomain(row: ResultRow): Users {
        return Users(
            id = row[id],
            name = row[name],
            password = row[password],
            role = row[role],
    //            createdAt = row[createdAt].toDate(),
    //            updatedAt = row[updatedAt].toDate(),
        )
    }
}
//fun findByLogin(id: String, password: String): Users {
//    val slugs = transaction(Database.connect(dataSource)) {
//        Favorites.join(Users, JoinType.INNER, additionalConstraint = { Favorites.user eq Users.id })
//            .slice(Favorites.slug)
//            .select { Users.username eq favorited }
//            .map { it[Favorites.slug] }
//    }
//    return findWithConditional((Articles.slug inList slugs), limit, offset)
//}