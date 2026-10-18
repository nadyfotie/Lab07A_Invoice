import java.util.ArrayList;
public class Invoice {
    private String title;
    private String address;
    private ArrayList<LineItem> items = new ArrayList<>();
    public Invoice(String title, String address) {
        this.title = title;
        this.address = address;
        }

    public String getTitle() {
        return title;
    }

    public String getAddress() {
        return address;
    }
    public ArrayList<LineItem> getItems() {   // returns the list
        return items;
    }

    public void addItem(LineItem item)
    {
        items.add(item);
    }
    public double getAmountDue(){
        double result = 0;
        for (LineItem item : items){
            result += item.getTotal();
        }
        return result;
    }

    }
