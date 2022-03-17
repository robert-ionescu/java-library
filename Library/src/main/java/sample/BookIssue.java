package sample;


import java.sql.Date;

public class BookIssue {
    private int IssueID;
    private Date ReturnDate;
    private Date IssueDate;
    private String Title;
    private String Author;

    public BookIssue(int issueID, Date returnDate, Date issueDate, String title, String author) {
        IssueID=issueID;
        ReturnDate = returnDate;
        IssueDate = issueDate;

        Title = title;
        Author = author;
    }

    public String getTitle() {
        return Title;
    }

    public String getAuthor() {
        return Author;
    }

    public int getIssueID() {
        return IssueID;
    }

    public Date getReturnDate() {
        return ReturnDate;
    }

    public Date getIssueDate() {
        return IssueDate;
    }


}
