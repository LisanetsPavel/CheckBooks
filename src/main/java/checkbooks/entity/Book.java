package checkbooks.entity;

import org.apache.log4j.Logger;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class Book {

    private int id;
    private String name;
    private String year;
    private String author;
    private String corState;
    private String description;
    private String pageCount;

    private static final Logger logger = Logger.getLogger(Book.class);

    private Set<String> pageSet = new TreeSet<>(new Comparator<String>() {
        @Override
        public int compare(String o1, String o2) {
            if (!o1.endsWith(".jpg")) {
                return 1;
            }
            if (!o2.endsWith(".jpg")) {
                return -1;
            }
            int str1 = 0;
            int str2 = 0;
            try {
                str1 = Integer.parseInt(o1.substring(0, o1.indexOf(".")));
                str2 = Integer.parseInt(o2.substring(0, o2.indexOf(".")));
            } catch (NumberFormatException e) {
                logger.error(e);
            }
            return Integer.compare(str1, str2);
        }
    });


    public Book(int id, String name, String year, String author, String corState, String description, String pageCount) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.author = author;
        this.corState = corState;
        this.description = description;
        this.pageCount = pageCount;
    }

    public String getPageCount() {
        return pageCount;
    }

    public void setPageCount(String pageCount) {
        this.pageCount = pageCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCorState() {
        return corState;
    }

    public void setCorState(String corState) {
        this.corState = corState;
    }

    public Set<String> getPageSet() {
        return pageSet;
    }

    public void setPageSet(Set<String> pageSet) {
        this.pageSet = pageSet;
    }

    public boolean addPage(String page) {
        return pageSet.add(page);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;

        Book book = (Book) o;

        if (getId() != book.getId()) return false;

        return true;

    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getYear() != null ? getYear().hashCode() : 0);
        result = 31 * result + (getAuthor() != null ? getAuthor().hashCode() : 0);
        result = 31 * result + (getCorState() != null ? getCorState().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getPageSet() != null ? getPageSet().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return
                "id=" + id +
                ", name='" + name + '\'' +
                ", year='" + year + '\'' +
                ", author='" + author + '\'' +
                ", corState='" + corState + '\'' ;

    }

    public String toStringForReport() {
        return
                 id +
                        "; " + name  +
                        "; " + year +
                        "; " + author  +
                        "; " + corState +
                        "; " + description;

    }


}
