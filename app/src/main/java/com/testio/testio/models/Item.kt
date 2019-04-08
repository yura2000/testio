package com.testio.testio.models

class Item {
    var title: String = ""
    var id: Int = 0
    var image: String = ""

    constructor() {
        //empty constructor needed
    }

    constructor(title: String, id: Int, image: String) {
        this.title = title
        this.id = id
        this.image = image
    }
}