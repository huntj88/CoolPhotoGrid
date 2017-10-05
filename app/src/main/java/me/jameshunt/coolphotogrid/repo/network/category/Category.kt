package me.jameshunt.coolphotogrid.repo.network.category

import com.google.gson.annotations.Expose

data class Category(
        @Expose val id: Int = 0,
        @Expose val title: String = ""
)