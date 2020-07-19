package com.byjus.headlines.di.pojo

class News {
    var totalResults: String? = null
    lateinit var articles: Array<Articles>
    var status: String? = null

    override fun toString(): String {
        return "ClassPojo [totalResults = $totalResults, articles = $articles, status = $status]"
    }
}

class Articles {
    var publishedAt: String? = null
    var author: String? = null
    var urlToImage: String? = null
    var description: String? = null
    var source: Source? = null
    var title: String? = null
    var url: String? = null
    var content: String? = null

    override fun toString(): String {
        return "ClassPojo [publishedAt = $publishedAt, author = $author, urlToImage = $urlToImage, description = $description, source = $source, title = $title, url = $url, content = $content]"
    }
}

class Source {
    var name: String? = null
    var id: String? = null

    override fun toString(): String {
        return "ClassPojo [name = $name, id = $id]"
    }
}