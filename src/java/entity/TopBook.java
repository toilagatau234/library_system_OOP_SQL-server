package entity;

/**
 *
 * @author Quoc_anh
 */
public class TopBook {

    String name;
    int total;

    public TopBook() {
    }

    public TopBook(String name, int total) {
        this.name = name;
        this.total = total;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    
}
