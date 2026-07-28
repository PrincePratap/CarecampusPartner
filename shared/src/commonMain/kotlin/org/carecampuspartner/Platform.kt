package org.carecampuspartner

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform