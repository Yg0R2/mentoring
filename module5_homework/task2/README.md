# Task:

* Create the following classes:
  * Author with fields: 
    * String name
    * short age
    * List<Book> books
  * Book with fields
    * String title
    * List<Author> authors
    * int numberOfPages
* Create a stream of books and then:
* Check if some/all book(s) have more than 200 pages;
* Find the books with max and min number of pages;
* Filter books with only single author; sort the books by number of pages/title; get list of all titles;
* Print them using forEach method
* Get distinct list of all authors
* Use peek method for debugging intermediate streams during execution the previous task.
* Use parallel stream with task #3.
* Use the Optional type for determining the title of the biggest book of some author. 