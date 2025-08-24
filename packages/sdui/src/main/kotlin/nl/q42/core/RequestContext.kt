package nl.q42.core

class RequestContext(
    var locale: Locale?,
    var appVersion: Int,
    var revalidateRequest: Boolean
)
