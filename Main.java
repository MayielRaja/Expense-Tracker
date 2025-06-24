//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.*;
public class Main {
    private static final Scanner sc=new Scanner(System.in);
    private static final List<Expense> expenses=new ArrayList<>();
    public static void main(String[] args) {
        while(true){
            System.out.println("\n-----Expense Tracker Menu-----");
            System.out.println("1. Add Expense \n2. View All Expenses\n3. Search by Category \n4. Delete Expense \n5. Exit");
            System.out.println("Select an option");
            int choice=sc.nextInt();
            sc.nextLine();
            switch (choice){
                case 1:
                    addExpense();
                    break;
                case 2:
                    viewExpense();
                    break;
                case 3:
                    searchByCategory();
                    break;
                case 4:
                    deleteExpense();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:System.out.println("Invalid Choice Try again");
            }
        }
    }
        private static void addExpense(){
            System.out.println("Enter Title: ");
            String title=sc.nextLine();
            System.out.println("Enter Category: ");
            String category=sc.nextLine();
            System.out.println("Enter amount: $");
            double amount= sc.nextDouble();
            expenses.add(new Expense(title,category,amount));
            System.out.println("Expenses added Successfully!");
        }
        private static void viewExpense(){
            if(expenses.isEmpty()){
                System.out.println("No expenses found");
                return;
            }
            System.out.println("\n---All Expenses---");
            for(int i=0;i<expenses.size();i++){
                System.out.println((i+1)+". "+expenses.get(i));
            }
        }
        private static void searchByCategory(){
            System.out.println("Enter Category to search: ");
            String searchCategory=sc.nextLine();
            boolean found=false;
            for(Expense exp:expenses){
                if(exp.getCategory().equalsIgnoreCase(searchCategory)){
                    System.out.println(exp);
                    found=true;
                }
            }
            if(!found) System.out.println("No expenses found in this category!");
        }
        private static void deleteExpense(){
            viewExpense();
            if(expenses.isEmpty()) return;
            System.out.println("Enter expense number to delete: ");
            int index=sc.nextInt()-1;
            if(index>=0&&index< expenses.size()){
                expenses.remove(index);
                System.out.println("Expense deleted");
            }
            else{
                System.out.println("Invalid expense number. ");
            }
        }
}