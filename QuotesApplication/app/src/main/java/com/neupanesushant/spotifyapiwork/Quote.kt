package com.neupanesushant.spotifyapiwork

data class Quote(
    val author: String,
    val author_permalink: String,
    val body: String,
    val dialogue: Boolean,
    val downvotes_count: Int,
    val favorites_count: Int,
    val id: Int,
    val `private`: Boolean,
    val tags: List<String>,
    val upvotes_count: Int,
    val url: String
){
    override fun toString(): String {
        return "Quote(author='$author', author_permalink='$author_permalink', body='$body', dialogue=$dialogue, downvotes_count=$downvotes_count, favorites_count=$favorites_count, id=$id, `private`=$`private`, tags=$tags, upvotes_count=$upvotes_count, url='$url')"
    }
}