import console.OrderConsole;
import console.ProductConsole;

void main() {
    Scanner sc= new Scanner(System.in);
    OrderConsole orderConsole= new OrderConsole();
    ProductConsole productConsole= new ProductConsole();
    while(true){
        System.out.println("=====Choose a role=====");
        System.out.println("1.Customer");
        System.out.println("2.Admin");
        System.out.println("3.Exit");
        int choice= sc.nextInt();
        switch (choice){
            case 1: orderConsole.start();
                    break;
            case 2: productConsole.start();
                    break;
            case 3:
                System.out.println("Exiting ..... ");
                return;
            default:
                System.out.println("Invalid number");
                break;
        }

    }
}
