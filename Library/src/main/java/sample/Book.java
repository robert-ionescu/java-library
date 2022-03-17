package sample;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.TextField;

public class Book {
    private int BookId;
    private String Title;
    private String Author;
    private String Language;

    public Book(int bookId, String title, String author, String language) {
        BookId = bookId;
        Title = title;
        Author = author;
        Language = language;
    }

    public int getBookId() {
        return BookId;
    }

    public String getTitle() {
        return Title;
    }

    public String getAuthor() {
        return Author;
    }

    public String getLanguage() {
        return Language;
    }

    public static SortedList<Book> searchSetting(ObservableList<Book> books, TextField tf_search) {
        FilteredList<Book> filteredBooks = new FilteredList<Book>(books, b -> true);
        tf_search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredBooks.setPredicate(book -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();
                return book.getAuthor().toLowerCase().contains(lowerCaseFilter) ||
                        book.getTitle().toLowerCase().contains(lowerCaseFilter) ||
                        book.getLanguage().toLowerCase().contains(lowerCaseFilter);

            });
        });

        return new SortedList<>(filteredBooks);
    }
}
