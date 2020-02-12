package com.ara.mvc.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Person(var name: String,
                  val age: Int,
                  @Id @GeneratedValue var id: Long? = null)