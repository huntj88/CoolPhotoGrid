package me.jameshunt.coolphotogrid.repo.network.photo

import com.google.gson.annotations.Expose

data class User(
        @Expose val name: String = "",
        @Expose val links: Links? = null
)