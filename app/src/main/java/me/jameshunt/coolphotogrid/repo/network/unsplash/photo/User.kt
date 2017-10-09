package me.jameshunt.coolphotogrid.repo.network.unsplash.photo

import com.google.gson.annotations.Expose

data class User(
        @Expose val name: String = "",
        @Expose val links: Links? = null
)