package com.example.contactslistapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable // Use Serializable for easy argument passing with Safe Args

@Entity(tableName = "contacts_table")
data class Contact(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val phone: String
) : Serializable // Implement Serializable