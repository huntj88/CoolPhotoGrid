package me.jameshunt.coolphotogrid.repo.network.unsplash.photo

import com.google.gson.annotations.Expose

data class Urls(
        @Expose val raw: String = "",
        @Expose val full: String = "",
        @Expose val regular: String = "",
        @Expose val small: String = "",
        @Expose val thumb: String = ""
)