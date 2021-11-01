package com.example.imdb_app.data

import java.io.Serializable

enum class MoviesPriority:Serializable {
    ALL {
        override fun priorityRange(): String = "ALL"

    },
    LOW {
        override fun priorityRange(): String = "1-4"
    },
    MEDIUM {
        override fun priorityRange(): String = "5-7"
    },
    HIGH {
        override fun priorityRange(): String = "8-10"
    };

    abstract fun priorityRange(): String
}