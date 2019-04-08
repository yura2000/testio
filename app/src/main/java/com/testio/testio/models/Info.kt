package com.testio.testio.models

class Info {
    var title: String = ""
    var id: Int = 0
    var imageBackground: String = ""

    constructor() {
        //empty constructor needed
    }

    constructor(title: String, id: Int, imageBackground: String) {
        this.title = title
        this.id = id
        this.imageBackground = imageBackground
    }
}