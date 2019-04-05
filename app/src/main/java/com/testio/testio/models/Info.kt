package com.testio.testio.models

class Info {
    var title: String = ""
    var id: String = ""
    var imageBackground: String = ""

    constructor() {
        //empty constructor needed
    }

    constructor(title: String, id: String, imageBackground: String) {
        this.title = title
        this.id = id
        this.imageBackground = imageBackground
    }
}