package com.example.day5

open class LibraryItem(
    val title: String,
    private val isbn: String,
    private val publication: String,
    private val numberOfPages: Int
) {
    var isAvailable: Boolean = true

    override fun toString(): String {
        return "Title: $title, ISBN: $isbn, Publication: $publication, Pages: $numberOfPages, Available: $isAvailable"
    }
}

class Book(title: String, isbn: String, publication: String, numberOfPages: Int) :
    LibraryItem(title, isbn, publication, numberOfPages)

class Magazine(title: String, isbn: String, publication: String, numberOfPages: Int) :
    LibraryItem(title, isbn, publication, numberOfPages)

class Journal(title: String, isbn: String, publication: String, numberOfPages: Int) :
    LibraryItem(title, isbn, publication, numberOfPages)

open class Person(val name: String, val id: Int)

class User(name: String, id: Int, val job: String) : Person(name, id)

class Librarian(name: String, id: Int, private val password: String) : Person(name, id) {
    fun authenticate(inputPassword: String): Boolean = password == inputPassword
}

class LibraryDatabase(
    private val librarian: Librarian,
    listOfAvailableBooks: List<Book>,
    mapOfBorrowedBooks: Map<Book, User>
) {
    private val items: MutableList<LibraryItem> = mutableListOf()
    private val borrowedItems: MutableMap<LibraryItem, User> = mutableMapOf()

    init {
        items.addAll(listOfAvailableBooks)

        mapOfBorrowedBooks.forEach { (book, user) ->
            book.isAvailable = false
            items.add(book)
            borrowedItems[book] = user
        }
    }

    // Add a new book to the system
    fun addBook(item: LibraryItem) {
        items.add(item)
        println("Added: ${item.title}")
    }

    fun viewAvailableBooks() {
        println("Available Items:")
        items.filter { it.isAvailable }.forEach { println(it) }
    }

    fun searchForABook(title: String): LibraryItem? {
        val item = items.find { it.title == title }
        if (item != null) {
            println("Found: $item")
        } else {
            println("Item not found: $title")
        }
        return item
    }

    fun lendBook(title: String, user: User, librarianPassword: String) {
        if (!librarian.authenticate(librarianPassword)) {
            println("Librarian authentication failed.")
            return
        }
        val item = searchForABook(title)
        if (item != null && item.isAvailable) {
            item.isAvailable = false
            borrowedItems[item] = user
            println("${user.name} borrowed $title")
        } else {
            println("Cannot lend: $title is not available.")
        }
    }

    fun receiveBookFromBorrower(title: String, librarianPassword: String) {
        if (!librarian.authenticate(librarianPassword)) {
            println("Librarian authentication failed.")
            return
        }
        val item = searchForABook(title)
        if (item != null && !item.isAvailable) {
            val borrower = borrowedItems[item]
            item.isAvailable = true
            borrowedItems.remove(item)
            println("$title returned by ${borrower?.name}")
        } else {
            println("Cannot return: $title is not borrowed.")
        }
    }

    fun viewBorrowedBooks() {
        println("Borrowed Items:")
        borrowedItems.forEach { (item, user) ->
            println("$item - Borrowed by: ${user.name}")
        }
    }
}

fun main() {
    val librarian = Librarian("John", 1, "pass123")
    val initialBooks = listOf(
        Book("The Great Gatsby", "123456789", "Scribner", 180),
        Book("1984", "987654321", "Penguin", 328)
    )
    val initialBorrowed = mapOf(
        Book("To Kill a Mockingbird", "456789123", "Harper", 281) to User("Alice", 1, "Student")
    )

    val library = LibraryDatabase(librarian, initialBooks, initialBorrowed)

    library.addBook(Magazine("National Geographic", "112233445", "NG Media", 100))
    library.addBook(Journal("Nature", "556677889", "Nature Publishing", 50))
    library.addBook(Book("Brave New World", "223344556", "HarperCollins", 268))

    library.viewAvailableBooks()

    library.searchForABook("The Great Gatsby")

    val user2 = User("Bob", 2, "Teacher")

    library.lendBook("The Great Gatsby", user2, "pass123")

    library.viewBorrowedBooks()

    library.receiveBookFromBorrower("The Great Gatsby", "pass123")

    library.viewAvailableBooks()
}