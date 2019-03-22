package com.testio.testio.data

import com.google.firebase.firestore.ServerTimestamp
import java.util.*

class Item {
    var title: String = ""
    var id: String = ""

    constructor() {
        //empty constructor needed
    }

    constructor(title: String, id: String) {
        this.title = title
        this.id = id
    }
}